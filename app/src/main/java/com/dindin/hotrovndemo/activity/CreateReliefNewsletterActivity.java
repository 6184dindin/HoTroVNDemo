package com.dindin.hotrovndemo.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.api.APIClient;
import com.dindin.hotrovndemo.api.APIService;
import com.dindin.hotrovndemo.api.param.base.ResponseBase;
import com.dindin.hotrovndemo.api.param.constant.SecCodeConstant;
import com.dindin.hotrovndemo.api.param.constant.URLConstant;
import com.dindin.hotrovndemo.api.param.request.CreateNewsRequest;
import com.dindin.hotrovndemo.api.param.request.UploadImageNewsRequest;
import com.dindin.hotrovndemo.api.param.response.UploadImageNewsResponse;
import com.dindin.hotrovndemo.databinding.ActivityCreateReliefNewsletterBinding;
import com.dindin.hotrovndemo.utils.City;
import com.dindin.hotrovndemo.utils.District;
import com.dindin.hotrovndemo.utils.GenericBody;
import com.dindin.hotrovndemo.utils.Helper;
import com.dindin.hotrovndemo.utils.InfoAddress;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.graphics.Color.TRANSPARENT;

public class CreateReliefNewsletterActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 101;
    private static final int GPS_REQUEST_CODE = 102;
    private static final int CAMERA_REQUEST_CODE = 103;
    private static final int SELECT_IMAGE_REQUEST_CODE = 104;
    private static final int GET_LOCATION_REQUEST_CODE = 105;

    ActivityCreateReliefNewsletterBinding binding;
    Dialog dialog;

    List<InfoAddress> provinces;
    List<City> cities;
    List<District> districts;

    List<Bitmap> bitmapList;

    Intent intent;
    int key;
    String phoneNumber;
    int field;

    double latitude = 0.0, longitude = 0.0;

    private boolean flagPermission = false;
    private boolean flagGPS = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_relief_newsletter);

        if (!flagPermission) {
            checkPermission();
        }

        intent = getIntent();
        key = intent.getIntExtra("key", 0);
        phoneNumber = intent.getStringExtra("phone");
        field = intent.getIntExtra("field", 0);
        binding.edtPhoneContact.setText(phoneNumber);

        dialog = new Dialog(this);
        bitmapList = new ArrayList<>();

        provinces = Helper.getProvinces(this);
        cities = Helper.getCities(this);
        districts = Helper.getDistricts(this);

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDataReliefNewsletter();

            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        selectedAddress();
        binding.btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flagGPS) {
                    checkGPSStatus();
                } else {
                    Intent intent = new Intent(CreateReliefNewsletterActivity.this, GetYourLocationActivity.class);
                    startActivityForResult(intent, GET_LOCATION_REQUEST_CODE);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }
        });

        binding.btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmapList.size() >= 5) {
                    Toast.makeText(getBaseContext(),
                            "Bạn đã chọn đủ 5 hình ảnh" +
                                    "\nNếu muốn thay đổi vui lòng xóa những lựa chọn trước đó",
                            Toast.LENGTH_LONG).show();
                } else {
                    openDialogAddImage();
                }
            }
        });
        handleRemoveImage();
    }

    InfoAddress infoAddress;
    Integer posProvince = 0;
    Integer posCity = 0;
    Integer posDistrict = 0;
    District district = null;
    City city = null;

    private void selectedAddress() {
        binding.btnSelectedProvince.setOnClickListener(v -> {
            binding.layoutSelectedPicker.setVisibility(View.VISIBLE);
            infoAddress = provinces.get(0);
            binding.tvSelected.setText(getResources().getString(R.string.selected_province));
            String[] strings = Helper.getNameInfoAddress(provinces);
            binding.numberPicker.setMinValue(1);
            binding.numberPicker.setMaxValue(strings.length);
            binding.numberPicker.setDisplayedValues(strings);
            binding.numberPicker.setWrapSelectorWheel(false);
            binding.numberPicker.setValue(1);
            binding.numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
                infoAddress = provinces.get(newVal - 1);
            });
            binding.btnSelected.setOnClickListener(v1 -> {
                binding.tvProvince.setText(infoAddress.getName());
                binding.tvCity.setText(getResources().getString(R.string.selected_city));
                binding.tvDistrict.setText(getResources().getString(R.string.selected_district));
                binding.layoutSelectedPicker.setVisibility(View.GONE);
                posProvince = infoAddress.getId();
                posCity = 0;
                posDistrict = 0;
                for (City c : cities) {
                    if (c.getId().equals(infoAddress.getId())) {
                        city = c;
                        break;
                    }
                }
            });
        });
        binding.btnSelectedCity.setOnClickListener(v -> {
            if (city == null) {
                return;
            }
            List<InfoAddress> infoAddresses = city.getInfoAddresses();
            binding.layoutSelectedPicker.setVisibility(View.VISIBLE);
            infoAddress = infoAddresses.get(0);
            binding.tvSelected.setText(getResources().getString(R.string.selected_city));
            String[] strings = Helper.getNameInfoAddress(infoAddresses);
            binding.numberPicker.setMinValue(1);
            binding.numberPicker.setMaxValue(strings.length);
            binding.numberPicker.setDisplayedValues(strings);
            binding.numberPicker.setWrapSelectorWheel(false);
            binding.numberPicker.setValue(1);
            binding.numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
                infoAddress = infoAddresses.get(newVal - 1);
            });
            binding.btnSelected.setOnClickListener(v1 -> {
                binding.tvCity.setText(infoAddress.getName());
                binding.tvDistrict.setText(getResources().getString(R.string.selected_district));
                binding.layoutSelectedPicker.setVisibility(View.GONE);
                posCity = infoAddress.getId();
                posDistrict = 0;
                for (District d : districts) {
                    if (d.getId().equals(infoAddress.getId())) {
                        district = d;
                        break;
                    }
                }
            });
        });
        binding.btnSelectedDistrict.setOnClickListener(v -> {
            if (district == null) {
                return;
            }
            List<InfoAddress> infoAddresses = district.getInfoAddresses();
            binding.layoutSelectedPicker.setVisibility(View.VISIBLE);
            infoAddress = infoAddresses.get(0);
            binding.tvSelected.setText(getResources().getString(R.string.selected_district));
            String[] strings = Helper.getNameInfoAddress(infoAddresses);
            binding.numberPicker.setMinValue(1);
            binding.numberPicker.setMaxValue(strings.length);
            binding.numberPicker.setDisplayedValues(strings);
            binding.numberPicker.setWrapSelectorWheel(false);
            binding.numberPicker.setValue(1);
            binding.numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
                infoAddress = infoAddresses.get(newVal - 1);
            });
            binding.btnSelected.setOnClickListener(v1 -> {
                binding.tvDistrict.setText(infoAddress.getName());
                binding.layoutSelectedPicker.setVisibility(View.GONE);
                posDistrict = infoAddress.getId();
            });
        });
    }

    private void createDataReliefNewsletter() {
        CreateNewsRequest request = new CreateNewsRequest();
        request.setPhoneCreated(phoneNumber);
        request.setFieldsId(field);
        request.setCountry(posProvince);
        request.setProvince(posProvince);
        request.setCity(posCity);
        request.setDistrict(posDistrict);
        request.setVillage(0);
        request.setLat(latitude);
        request.setLng(longitude);
        request.setAddress(binding.tvLocation.getText().toString().trim());
        request.setNotificationId("0");
        request.setAdminPost(binding.edtAdminPost.getText().toString().trim());
        request.setPhoneContact(binding.edtPhoneContact.getText().toString().trim());
        request.setRolePersonPost(binding.edtRolePersonPost.getText().toString().trim());
        request.setRequestSupport(binding.edtRequestSupport.getText().toString().trim());
        request.setDescriptions(binding.edtDescriptions.getText().toString().trim());
        Calendar calendar = Calendar.getInstance();
        Integer dateCreated = calendar.get(Calendar.YEAR) * 10000
                + calendar.get(Calendar.MONTH) * 100
                + calendar.get(Calendar.DAY_OF_MONTH);
        request.setDateCreated(dateCreated);
        request.setSecCode(SecCodeConstant.SCCreateNews);

        TypeToken<CreateNewsRequest> token = new TypeToken<CreateNewsRequest>() {};
        GenericBody<CreateNewsRequest> requestGenericBody = new GenericBody<>(request, token);
        APIService service = APIClient.getClient(this, URLConstant.URLBaseNews).create(APIService.class);
        service.postToServerAPI(URLConstant.URLCreateNews, requestGenericBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonElement>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull JsonElement jsonElement) {
                        GsonBuilder gson = new GsonBuilder();
                        Type collectionType = new TypeToken<ResponseBase<Integer>>() {
                        }.getType();
                        ResponseBase<Integer> data = new Gson().fromJson(jsonElement.getAsJsonObject().toString(), collectionType);
                        if (data.getResultCode().equals("001")) {
                            Integer newsId = data.getResultData();
                            uploadImageNews(newsId);
                            showDialogCreateSuccessful();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void uploadImageNews(Integer newsId) {
        UploadImageNewsRequest request = new UploadImageNewsRequest();
        TypeToken<UploadImageNewsRequest> token = new TypeToken<UploadImageNewsRequest>() {};
        APIService service = APIClient.getClient(this, URLConstant.URLBaseImage).create(APIService.class);
        for (int i = 0; i < bitmapList.size(); i++) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmapList.get(i).compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            String bitmapBase64String = Base64.encodeToString(b, Base64.DEFAULT);
            request.setNewsId(newsId);
            request.setImage(bitmapBase64String);
            request.setType(field);
            request.setOrderNum(i);
            request.setSecCode(SecCodeConstant.SCUploadImage);
            GenericBody<UploadImageNewsRequest> requestGenericBody = new GenericBody<>(request, token);
            service.postToServerAPI(URLConstant.URLUploadImage, requestGenericBody)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<JsonElement>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull JsonElement jsonElement) {
                            GsonBuilder gson = new GsonBuilder();
                            Type collectionType = new TypeToken<ResponseBase<UploadImageNewsResponse>>() {
                            }.getType();
                            ResponseBase<UploadImageNewsResponse> data = gson.create().fromJson(jsonElement.getAsJsonObject().toString(), collectionType);

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    private void showDialogCreateSuccessful() {
        dialog.setContentView(R.layout.dialog_notify_create_relief_newsletter_successfull);
        dialog.findViewById(R.id.btnDone).setOnClickListener(v -> {
            dialog.dismiss();
            finish();
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void checkPermission() {
        Dexter.withContext(this)
                .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            flagPermission = true;
                        }
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            flagPermission = false;
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.cancelPermissionRequest();
                        showSettingsDialog();
                    }
                }).check();
    }

    private void showSettingsDialog() {
        androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(this);
        alertDialog.setTitle("Cho phép " + getResources().getString(R.string.app_name));
        alertDialog.setMessage("Truy cập vào vị trí của thiết bị này\nChup ảnh và quay video\nTruy cập vào ảnh, phương tiện và tệp trên thiết bị của bạn");
        alertDialog.setPositiveButton("ĐẾN CÀI ĐẶT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSetting();
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    /**
     * Phương thức này để chuyển người dùng đến trình quản lý ứng dụng, trong trường hợp ngưới dùng
     * từ chối quền và tích vào ô không được hỏi lại.
     */
    // navigating user to app settings
    private void openSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", this.getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, PERMISSION_REQUEST_CODE);
    }

    private void checkGPSStatus() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        flagGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!flagGPS) {
            showGPSSettingDialog();
        }
    }

    private void showGPSSettingDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Bạn cần phải khởi động GPS");
        alertDialog.setPositiveButton("Đến cài đặt", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openGPSSetting();
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    /**
     * Phương thức này để chuyển người dùng đến trình quản lý ứng dụng, trong trường hợp GPS chưa
     * được enable trên ứng trên điện thoại
     */
    // navigating user to app settings
    private void openGPSSetting() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivityForResult(intent, GPS_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (!flagPermission) {
                    checkPermission();
                }
                break;
            case GPS_REQUEST_CODE:
                if (!flagGPS) {
                    checkGPSStatus();
                }
                break;
            case SELECT_IMAGE_REQUEST_CODE:
                getUriImage(resultCode, data);
                setListImage();
                break;
            case CAMERA_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    if (bitmapList.size() < 5) {
                        bitmapList.add(photo);
                        setListImage();
                    }
                }
                break;
            case GET_LOCATION_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    latitude = data.getDoubleExtra("lat", 0.0);
                    longitude = data.getDoubleExtra("long", 0.0);
                }
                break;
            default:
                break;
        }
    }

    private void handleRemoveImage() {
        binding.btnDeleteImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmapList.remove(0);
                setListImage();
            }
        });
        binding.btnDeleteImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmapList.remove(1);
                setListImage();
            }
        });
        binding.btnDeleteImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmapList.remove(2);
                setListImage();
            }
        });
        binding.btnDeleteImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmapList.remove(3);
                setListImage();
            }
        });
        binding.btnDeleteImage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmapList.remove(4);
                setListImage();
            }
        });
    }

    private void setListImage() {
        int length = bitmapList.size();
        switch (length) {
            case 0:
                binding.tvNumberImageSelect.setText("0/5");
                binding.layoutImage.setVisibility(View.GONE);
                binding.layoutImage1.setVisibility(View.GONE);
                binding.layoutImage2.setVisibility(View.GONE);
                binding.layoutImage3.setVisibility(View.GONE);
                binding.layoutImage4.setVisibility(View.GONE);
                binding.layoutImage5.setVisibility(View.GONE);
                break;
            case 1:
                binding.tvNumberImageSelect.setText("1/5");
                binding.layoutImage.setVisibility(View.VISIBLE);
                binding.layoutImage1.setVisibility(View.VISIBLE);
                Glide.with(this).load(bitmapList.get(0)).into(binding.img1);
                binding.layoutImage2.setVisibility(View.GONE);
                binding.layoutImage3.setVisibility(View.GONE);
                binding.layoutImage4.setVisibility(View.GONE);
                binding.layoutImage5.setVisibility(View.GONE);
                break;
            case 2:
                binding.tvNumberImageSelect.setText("2/5");
                binding.layoutImage.setVisibility(View.VISIBLE);
                binding.layoutImage1.setVisibility(View.VISIBLE);
                Glide.with(this).load(bitmapList.get(0)).into(binding.img1);
                binding.layoutImage2.setVisibility(View.VISIBLE);
                Glide.with(this).load(bitmapList.get(1)).into(binding.img2);
                binding.layoutImage3.setVisibility(View.GONE);
                binding.layoutImage4.setVisibility(View.GONE);
                binding.layoutImage5.setVisibility(View.GONE);
                break;
            case 3:
                binding.tvNumberImageSelect.setText("3/5");
                binding.layoutImage.setVisibility(View.VISIBLE);
                binding.layoutImage1.setVisibility(View.VISIBLE);
                Glide.with(this).load(bitmapList.get(0)).into(binding.img1);
                binding.layoutImage2.setVisibility(View.VISIBLE);
                Glide.with(this).load(bitmapList.get(1)).into(binding.img2);
                binding.layoutImage3.setVisibility(View.VISIBLE);
                Glide.with(this).load(bitmapList.get(2)).into(binding.img3);
                binding.layoutImage4.setVisibility(View.GONE);
                binding.layoutImage5.setVisibility(View.GONE);
                break;
            case 4:
                binding.tvNumberImageSelect.setText("4/5");
                binding.layoutImage.setVisibility(View.VISIBLE);
                binding.layoutImage1.setVisibility(View.VISIBLE);
                Glide.with(this).load(bitmapList.get(0)).into(binding.img1);
                binding.layoutImage2.setVisibility(View.VISIBLE);
                Glide.with(this).load(bitmapList.get(1)).into(binding.img2);
                binding.layoutImage3.setVisibility(View.VISIBLE);
                Glide.with(this).load(bitmapList.get(2)).into(binding.img3);
                binding.layoutImage4.setVisibility(View.VISIBLE);
                Glide.with(this).load(bitmapList.get(3)).into(binding.img4);
                binding.layoutImage5.setVisibility(View.GONE);
                break;
            case 5:
                binding.tvNumberImageSelect.setText("5/5");
                binding.layoutImage.setVisibility(View.VISIBLE);
                binding.layoutImage1.setVisibility(View.VISIBLE);
                Glide.with(this).load(bitmapList.get(0)).into(binding.img1);
                binding.layoutImage2.setVisibility(View.VISIBLE);
                Glide.with(this).load(bitmapList.get(1)).into(binding.img2);
                binding.layoutImage3.setVisibility(View.VISIBLE);
                Glide.with(this).load(bitmapList.get(2)).into(binding.img3);
                binding.layoutImage4.setVisibility(View.VISIBLE);
                Glide.with(this).load(bitmapList.get(3)).into(binding.img4);
                binding.layoutImage5.setVisibility(View.VISIBLE);
                Glide.with(this).load(bitmapList.get(4)).into(binding.img5);
                break;
        }
    }

    private void openDialogAddImage() {
        dialog.setContentView(R.layout.dialog_add_image);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.btnLibrary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.btnCamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeAPhoto();
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
        dialog.show();
    }

    private void takeAPhoto() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE_REQUEST_CODE);
    }

    private void getUriImage(int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            assert data != null;
            Bitmap bitmap = null;
            if (data.getClipData() != null) {
                ClipData mClipData = data.getClipData();

                for (int i = 0; i < mClipData.getItemCount(); i++) {
                    ClipData.Item item = mClipData.getItemAt(i);
                    Uri uri = item.getUri();
                    try {
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (bitmapList.size() < 5) {
                        bitmapList.add(bitmap);
                    } else {
                        break;
                    }
                }
            } else if (data.getData() != null) {
                Uri uri = data.getData();
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                if (bitmapList.size() < 5) {
                    bitmapList.add(bitmap);
                }
            }
        }
    }
}
package com.dindin.hotrovndemo.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import android.view.LayoutInflater;
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
import com.dindin.hotrovndemo.api.param.request.CreateHelpsNewsRequest;
import com.dindin.hotrovndemo.api.param.request.UploadImageHelperRequest;
import com.dindin.hotrovndemo.api.param.response.UploadImageHelperResponse;
import com.dindin.hotrovndemo.databinding.ActivityCreateReliefCampaignBinding;
import com.dindin.hotrovndemo.databinding.DialogSelectedDayMonthYearBinding;
import com.dindin.hotrovndemo.utils.GenericBody;
import com.dindin.hotrovndemo.utils.Helper;
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
import java.math.BigInteger;
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

public class CreateReliefCampaignActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 101;
    private static final int CAMERA_REQUEST_CODE = 103;
    private static final int SELECT_IMAGE_REQUEST_CODE = 104;

    ActivityCreateReliefCampaignBinding binding;
    Dialog dialog;
    List<Bitmap> bitmapList;

    Intent intent;
    int key;
    String phoneNumber;
    int field;
    int newsId;

    int date = 0, month = 0, year = 0;

    private boolean flagPermission = false;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_relief_campaign);

        if (!flagPermission) {
            checkPermission();
        }

        intent = getIntent();
        key = intent.getIntExtra("key", 0);
        phoneNumber = intent.getStringExtra("phone");
        field = intent.getIntExtra("field", 0);
        newsId = intent.getIntExtra("newsId", 0);

        binding.edtPhoneContact.setText(phoneNumber);

        dialog = new Dialog(this);
        bitmapList = new ArrayList<>();
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDataReliefCampaign();
            }
        });

        binding.btnSelectedDate.setOnClickListener(v -> {
            DialogSelectedDayMonthYearBinding binding1 = DialogSelectedDayMonthYearBinding.inflate(LayoutInflater.from(this));
            int thisDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            binding1.numberPickerDay.setMaxValue(31);
            binding1.numberPickerDay.setMinValue(1);
            binding1.numberPickerDay.setValue(thisDay);
            date = thisDay;
            binding1.numberPickerDay.setWrapSelectorWheel(true);
            binding1.numberPickerDay.setOnValueChangedListener((picker, oldVal, newVal) -> {
                date = newVal;
            });
            int thisMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
            String[] stringsMonth = {"tháng 1", "tháng 2", "tháng 3", "tháng 4",
                    "tháng 5", "tháng 6", "tháng 7", "tháng 8"
                    , "tháng 9", "tháng 10", "tháng 11", "tháng 12"};
            binding1.numberPickerMonth.setMaxValue(12);
            binding1.numberPickerMonth.setMinValue(1);
            binding1.numberPickerMonth.setValue(thisMonth);
            month = thisMonth;
            binding1.numberPickerMonth.setDisplayedValues(stringsMonth);
            binding1.numberPickerMonth.setWrapSelectorWheel(true);
            binding1.numberPickerMonth.setOnValueChangedListener((picker, oldVal, newVal) -> {
                month = newVal;
            });

            ArrayList<String> yearList = new ArrayList<>();
            String[] stringsYear;
            int thisYear = Calendar.getInstance().get(Calendar.YEAR);
            for (int i = 2020; i <= 2050; i++) {
                yearList.add(Integer.toString(i));
            }
            stringsYear = yearList.toArray(new String[0]);
            binding1.numberPickerYear.setMaxValue(2050);
            binding1.numberPickerYear.setMinValue(2020);
            binding1.numberPickerYear.setValue(thisYear);
            year = thisYear;
            binding1.numberPickerYear.setDisplayedValues(stringsYear);
            binding1.numberPickerYear.setWrapSelectorWheel(true);
            binding1.numberPickerYear.setOnValueChangedListener((picker, oldVal, newVal) -> {
                year = newVal;
            });
            binding1.btnDone.setOnClickListener(v1 -> {
                if (Helper.isValidDate(date, month, year)) {
                    binding.tvDate.setText(date + "/" + month + "/" + year);
                    dialog.dismiss();
                } else {
                    Toast.makeText(this, "Ngày bạn lựa chọn chưa hợp lệ", Toast.LENGTH_LONG).show();
                }
            });
            dialog.setContentView(binding1.getRoot());
            Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
            dialog.show();
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
                    if (!flagPermission) {
                        checkPermission();
                    } else {
                        openDialogAddImage();
                    }
                }
            }
        });
        handleRemoveImage();
    }

    private void createDataReliefCampaign() {
        CreateHelpsNewsRequest request = new CreateHelpsNewsRequest();
        request.setNewsId(newsId);
        request.setPhoneCreated(phoneNumber);
        request.setFieldsId(field);
        request.setAdminHelper(binding.edtAdminHelper.getText().toString().trim());
        request.setPhoneContact(binding.edtPhoneContact.getText().toString().trim());
        request.setRolePersonHelper(binding.edtRolePersonHelper.getText().toString().trim());
        request.setOrganization(binding.edtOrganization.getText().toString().trim());
        Calendar calendar = Calendar.getInstance();
        int dateCreated = calendar.get(Calendar.YEAR) * 10000
                + calendar.get(Calendar.MONTH) * 100
                + calendar.get(Calendar.DAY_OF_MONTH);
        int dateTime = year * 10000 + month * 100 + date;
        request.setTimeBegin(Math.min(dateCreated, dateTime));
        request.setTimeEnd(Math.max(dateCreated, dateTime));
        request.setSupportValue(binding.edtSupportValue.getText().toString().trim());
        request.setDateCreated(BigInteger.valueOf(dateCreated));
        request.setSecCode(SecCodeConstant.SCCreateHelpsNews);

        TypeToken<CreateHelpsNewsRequest> token = new TypeToken<CreateHelpsNewsRequest>() {
        };
        GenericBody<CreateHelpsNewsRequest> requestGenericBody = new GenericBody<CreateHelpsNewsRequest>(request, token);
        APIService service = APIClient.getClient(this, URLConstant.URLBaseNews).create(APIService.class);
        service.postToServerAPI(URLConstant.URLCreateHelpsNews, requestGenericBody)
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
                            if (data.getResultData() != null) {
                                Integer helpsId = data.getResultData();
                                uploadImgHelper(helpsId);
                                showDialogCreateSuccessful();
                            }
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

    private void uploadImgHelper(Integer helpId) {
        UploadImageHelperRequest uploadImageHelperRequest = new UploadImageHelperRequest();

        for (int i = 0; i < bitmapList.size(); i++) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmapList.get(i).compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

            uploadImageHelperRequest.setHelpId(helpId);
            uploadImageHelperRequest.setOrderNum(i);
            uploadImageHelperRequest.setType(field);
            uploadImageHelperRequest.setImage(encodedImage);
            uploadImageHelperRequest.setSecCode(SecCodeConstant.SCUploadImageHelper);

            TypeToken<UploadImageHelperRequest> stringListTypeToken = new TypeToken<UploadImageHelperRequest>() {
            };
            GenericBody<UploadImageHelperRequest> request = new GenericBody<UploadImageHelperRequest>(uploadImageHelperRequest, stringListTypeToken);

            APIService service = APIClient.getClient(getApplicationContext(),
                    URLConstant.URLBaseNews).create(APIService.class);

            service.postToServerAPI(URLConstant.URLBaseImage, request).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<JsonElement>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull JsonElement jsonElement) {
                            GsonBuilder gSon = new GsonBuilder();
                            Type collectionType = new TypeToken<ResponseBase<UploadImageHelperResponse>>() {
                            }.getType();
                            ResponseBase<UploadImageHelperResponse> data = new Gson().fromJson(jsonElement.getAsJsonObject().toString(), collectionType);
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
        dialog.setContentView(R.layout.dialog_notify_create_relief_campaign_successfull);
        dialog.findViewById(R.id.btnDone).setOnClickListener(v -> {
            dialog.dismiss();
            finish();
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void checkPermission() {
        Dexter.withContext(this)
                .withPermissions(Manifest.permission.CAMERA,
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
        alertDialog.setMessage("Truy cập vào ảnh, phương tiện và tệp trên thiết bị của bạn\nChup ảnh và quay video");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (!flagPermission) {
                    checkPermission();
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
        }
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
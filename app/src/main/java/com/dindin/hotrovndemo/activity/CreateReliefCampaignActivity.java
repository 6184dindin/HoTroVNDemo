package com.dindin.hotrovndemo.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.databinding.ActivityCreateReliefCampaignBinding;
import com.dindin.hotrovndemo.databinding.DialogAddImageBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CreateReliefCampaignActivity extends AppCompatActivity {
    private static final int MY_CAMERA_PERMISSION_CODE = 1;
    private static final int CAMERA_REQUEST = 2;
    private static final int SELECT_IMAGE_CODE = 3;

    ActivityCreateReliefCampaignBinding binding;
    Dialog dialog;
    List<Uri> uriList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_relief_campaign);
        dialog = new Dialog(this);
        uriList = new ArrayList<>();
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.dialog_notify_create_relief_campaign_successfull);
                dialog.findViewById(R.id.btnDone).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        binding.btnAddImage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(uriList.size() >= 5) {
                    Toast.makeText(getBaseContext(),
                            "Bạn đã chọn đủ 5 hình ảnh" +
                                    "\nNếu muốn chọn thêm vui lòng xóa những lựa chọn trước đó",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    openDialogAddImage();
                }
            }
        });
        handleRemoveImage();
    }

    private void handleRemoveImage() {
        binding.btnDeleteImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uriList.remove(0);
                setListImage();
            }
        });
        binding.btnDeleteImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uriList.remove(1);
                setListImage();
            }
        });
        binding.btnDeleteImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uriList.remove(2);
                setListImage();
            }
        });
        binding.btnDeleteImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uriList.remove(3);
                setListImage();
            }
        });
        binding.btnDeleteImage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uriList.remove(4);
                setListImage();
            }
        });
    }
    private void setListImage() {
        int length = uriList.size();
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
                Glide.with(this).load(uriList.get(0)).into(binding.img1);
                binding.layoutImage2.setVisibility(View.GONE);
                binding.layoutImage3.setVisibility(View.GONE);
                binding.layoutImage4.setVisibility(View.GONE);
                binding.layoutImage5.setVisibility(View.GONE);
                break;
            case 2:
                binding.tvNumberImageSelect.setText("2/5");
                binding.layoutImage.setVisibility(View.VISIBLE);
                binding.layoutImage1.setVisibility(View.VISIBLE);
                Glide.with(this).load(uriList.get(0)).into(binding.img1);
                binding.layoutImage2.setVisibility(View.VISIBLE);
                Glide.with(this).load(uriList.get(1)).into(binding.img2);
                binding.layoutImage3.setVisibility(View.GONE);
                binding.layoutImage4.setVisibility(View.GONE);
                binding.layoutImage5.setVisibility(View.GONE);
                break;
            case 3:
                binding.tvNumberImageSelect.setText("3/5");
                binding.layoutImage.setVisibility(View.VISIBLE);
                binding.layoutImage1.setVisibility(View.VISIBLE);
                Glide.with(this).load(uriList.get(0)).into(binding.img1);
                binding.layoutImage2.setVisibility(View.VISIBLE);
                Glide.with(this).load(uriList.get(1)).into(binding.img2);
                binding.layoutImage3.setVisibility(View.VISIBLE);
                Glide.with(this).load(uriList.get(2)).into(binding.img3);
                binding.layoutImage4.setVisibility(View.GONE);
                binding.layoutImage5.setVisibility(View.GONE);
                break;
            case 4:
                binding.tvNumberImageSelect.setText("4/5");
                binding.layoutImage.setVisibility(View.VISIBLE);
                binding.layoutImage1.setVisibility(View.VISIBLE);
                Glide.with(this).load(uriList.get(0)).into(binding.img1);
                binding.layoutImage2.setVisibility(View.VISIBLE);
                Glide.with(this).load(uriList.get(1)).into(binding.img2);
                binding.layoutImage3.setVisibility(View.VISIBLE);
                Glide.with(this).load(uriList.get(2)).into(binding.img3);
                binding.layoutImage4.setVisibility(View.VISIBLE);
                Glide.with(this).load(uriList.get(3)).into(binding.img4);
                binding.layoutImage5.setVisibility(View.GONE);
                break;
            case 5:
                binding.tvNumberImageSelect.setText("5/5");
                binding.layoutImage.setVisibility(View.VISIBLE);
                binding.layoutImage1.setVisibility(View.VISIBLE);
                Glide.with(this).load(uriList.get(0)).into(binding.img1);
                binding.layoutImage2.setVisibility(View.VISIBLE);
                Glide.with(this).load(uriList.get(1)).into(binding.img2);
                binding.layoutImage3.setVisibility(View.VISIBLE);
                Glide.with(this).load(uriList.get(2)).into(binding.img3);
                binding.layoutImage4.setVisibility(View.VISIBLE);
                Glide.with(this).load(uriList.get(3)).into(binding.img4);
                binding.layoutImage5.setVisibility(View.VISIBLE);
                Glide.with(this).load(uriList.get(4)).into(binding.img5);
                break;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void openDialogAddImage() {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        DialogAddImageBinding binding = DialogAddImageBinding.inflate(LayoutInflater.from(this));
        binding.btnCancel.setOnClickListener(view -> dialog.dismiss());
        binding.btnLibrary.setOnClickListener(view -> {
            selectImage();
            dialog.dismiss();
        });
        binding.btnCamera.setOnClickListener(view -> {
            takeAPhoto();
            dialog.dismiss();
        });
        dialog.setContentView(binding.getRoot());
        dialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void takeAPhoto() {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        } else {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "Bạn cần cho phép truy cập camera", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SELECT_IMAGE_CODE:
                getUriImage(resultCode, data);
                setListImage();
                break;
            case CAMERA_REQUEST:
                if(resultCode == Activity.RESULT_OK) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    if(uriList.size() < 5) {
                        uriList.add(getImageUri(photo));
                        selectImage();
                    }
                }
                break;
        }
    }

    private void getUriImage(int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            assert data != null;
            if (data.getClipData() != null) {
                ClipData mClipData = data.getClipData();

                for (int i = 0; i < mClipData.getItemCount(); i++) {
                    ClipData.Item item = mClipData.getItemAt(i);
                    Uri uri = item.getUri();
                    if(uriList.size() < 5) {
                        uriList.add(uri);
                    }
                    else {
                        break;
                    }
                }
            } else if (data.getData() != null) {
                Uri uri = data.getData();
                if(uriList.size() < 5) {
                    uriList.add(uri);
                }
            }
        }
    }

    private Uri getImageUri(Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(this.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}
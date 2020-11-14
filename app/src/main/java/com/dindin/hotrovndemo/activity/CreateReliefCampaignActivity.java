package com.dindin.hotrovndemo.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.GridLayoutManager;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.databinding.ActivityCreateReliefCampaignBinding;
import com.dindin.hotrovndemo.databinding.DialogAddImageBinding;
import com.dindin.hotrovndemo.utils.ImageSelectAdapterEvent;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class CreateReliefCampaignActivity extends AppCompatActivity implements View.OnClickListener, ImageSelectAdapterEvent {
    private static final int MY_CAMERA_PERMISSION_CODE = 1;
    private static final int CAMERA_REQUEST = 2;
    private static final int SELECT_IMAGE_CODE = 3;
    private static final String TAG = "long";


    private MutableLiveData<Integer> numberImagesSelect = new MutableLiveData<>();
    private ImagesSelectAdapter adapter;


    ActivityCreateReliefCampaignBinding binding;
    Dialog dialog;

    private List<ImageChoose> imageChooses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_relief_campaign);
        settingRecyclerView();
        dialog = new Dialog(this);
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

        onClick();
    }

    private void settingRecyclerView() {
        imageChooses = new ArrayList<>();
        adapter = new ImagesSelectAdapter(imageChooses, this);
        binding.rcvImage.setAdapter(adapter);
        binding.rcvImage.setLayoutManager(new GridLayoutManager(this, 5));


        numberImagesSelect.postValue(0);

        numberImagesSelect.observe(this, (integer) -> binding.tvNumberImageSelect.setText(integer + "/5"));
    }

    private void onClick() {
        binding.btnAddImage.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        openDialogAddImage();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void openDialogAddImage() {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        DialogAddImageBinding binding = DialogAddImageBinding.inflate(LayoutInflater.from(this));
        binding.btnCancel.setOnClickListener(view -> dialog.dismiss());
        binding.btnLibrary.setOnClickListener(view -> {
            selectInLibrary();
            dialog.dismiss();
        });
        binding.btnCamera.setOnClickListener(view -> {
            takeAPhoto();
            dialog.dismiss();
        });
        dialog.setContentView(binding.getRoot());
        dialog.show();
    }

    private void selectInLibrary() {
        if (imageChooses.size() < 5) {
            selectImage();
        } else {
            Toast.makeText(this, "Đã chọn tối đa số ảnh", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void takeAPhoto() {
        if (imageChooses.size() >= 5) {
            Toast.makeText(this, "Đã chọn tối đa số ảnh", Toast.LENGTH_SHORT).show();
        } else {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
            } else {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
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
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SELECT_IMAGE_CODE:
                getUriImage(resultCode, data);
                break;
        }

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            imageChooses.add(new ImageChoose(false, null, photo));
            numberImagesSelect.postValue(imageChooses.size());
            adapter.notifyDataSetChanged();
        }
    }

    private void getUriImage(int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            List<ImageChoose> imageChooses1 = new ArrayList<>();
            if (data.getClipData() != null) {

                ClipData mClipData = data.getClipData();

                for (int i = 0; i < mClipData.getItemCount(); i++) {
                    ClipData.Item item = mClipData.getItemAt(i);
                    Uri uri = item.getUri();
                    imageChooses1.add(new ImageChoose(true, uri, null));
                    Log.e(TAG, uri.toString());
                }
            } else if (data.getData() != null) {
                Log.e(TAG, "data");
                Uri uri = data.getData();
                imageChooses1.add(new ImageChoose(true, uri, null));


            }

            onListSizeChanged(imageChooses1);
            numberImagesSelect.postValue(imageChooses.size());
            adapter.notifyDataSetChanged();
        }
    }

    private void onListSizeChanged(List<ImageChoose> list) {
        for (int i = 0; i < list.size(); i++) {
            imageChooses.add(list.get(i));
        }
        if (imageChooses.size() > 5) {
            int size = imageChooses.size();
            for (int i = 0; i < size - 5; i++) {
                imageChooses.remove(0);
            }
        }

    }

    @Override
    public void onDeleteItem(int pos) {
        imageChooses.remove(pos);
        numberImagesSelect.postValue(imageChooses.size());
        adapter.notifyDataSetChanged();
    }
}
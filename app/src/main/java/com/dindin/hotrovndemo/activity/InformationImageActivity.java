package com.dindin.hotrovndemo.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.databinding.ActivityInformationImageBinding;

import java.util.List;

public class InformationImageActivity extends AppCompatActivity {
    ActivityInformationImageBinding binding;
    List<Uri> uriList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_information_image);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.img1.setPadding(0, 0, 0, 0);
                binding.img2.setPadding(getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image));
                binding.img3.setPadding(getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image));
                binding.img4.setPadding(getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image));
                binding.img5.setPadding(getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image));
            }
        });
        binding.img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.img2.setPadding(0, 0, 0, 0);
                binding.img1.setPadding(getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image));
                binding.img3.setPadding(getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image));
                binding.img4.setPadding(getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image));
                binding.img5.setPadding(getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image));
            }
        });
        binding.img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.img3.setPadding(0, 0, 0, 0);
                binding.img1.setPadding(getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image));
                binding.img2.setPadding(getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image));
                binding.img4.setPadding(getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image));
                binding.img5.setPadding(getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image));
            }
        });
        binding.img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.img4.setPadding(0, 0, 0, 0);
                binding.img1.setPadding(getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image));
                binding.img2.setPadding(getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image));
                binding.img3.setPadding(getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image));
                binding.img5.setPadding(getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image));
            }
        });
        binding.img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.img5.setPadding(0, 0, 0, 0);
                binding.img1.setPadding(getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image));
                binding.img2.setPadding(getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image));
                binding.img3.setPadding(getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image));
                binding.img4.setPadding(getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image),
                        getResources().getDimensionPixelOffset(R.dimen.padding_image));
            }
        });
    }
}

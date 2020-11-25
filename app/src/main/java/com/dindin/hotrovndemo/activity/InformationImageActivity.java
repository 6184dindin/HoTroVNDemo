package com.dindin.hotrovndemo.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.databinding.ActivityInformationImageBinding;

import java.util.ArrayList;
import java.util.List;

public class InformationImageActivity extends AppCompatActivity {
    ActivityInformationImageBinding binding;
    List<String> stringsURL;
    Intent intent;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_information_image);

        intent = getIntent();
        stringsURL = new ArrayList<>();
        int size = intent.getIntExtra("size", 0);
        for (int i = 0; i < size; i++) {
            stringsURL.add(intent.getStringExtra("img" + i));
        }

        binding.img1.setPadding(0, 0, 0, 0);
        Glide.with(this).load(intent.getStringExtra("img" + 0))
                .placeholder(getResources().getDrawable(R.drawable.ic_image_gallery))
                .error(getResources().getDrawable(R.drawable.ic_image_gallery))
                .into(binding.imgChoose);
        Glide.with(this).load(intent.getStringExtra("img" + 0))
                .placeholder(getResources().getDrawable(R.drawable.ic_image_gallery))
                .error(getResources().getDrawable(R.drawable.ic_image_gallery))
                .into(binding.img1);
        Glide.with(this).load(intent.getStringExtra("img" + 1))
                .placeholder(getResources().getDrawable(R.drawable.ic_image_gallery))
                .error(getResources().getDrawable(R.drawable.ic_image_gallery))
                .into(binding.img2);
        Glide.with(this).load(intent.getStringExtra("img" + 2))
                .placeholder(getResources().getDrawable(R.drawable.ic_image_gallery))
                .error(getResources().getDrawable(R.drawable.ic_image_gallery))
                .into(binding.img3);
        Glide.with(this).load(intent.getStringExtra("img" + 3))
                .placeholder(getResources().getDrawable(R.drawable.ic_image_gallery))
                .error(getResources().getDrawable(R.drawable.ic_image_gallery))
                .into(binding.img4);
        Glide.with(this).load(intent.getStringExtra("img" + 4))
                .placeholder(getResources().getDrawable(R.drawable.ic_image_gallery))
                .error(getResources().getDrawable(R.drawable.ic_image_gallery))
                .into(binding.img5);

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
                Glide.with(InformationImageActivity.this).load(intent.getStringExtra("img" + 0))
                        .placeholder(getResources().getDrawable(R.drawable.ic_image_gallery))
                        .error(getResources().getDrawable(R.drawable.ic_image_gallery))
                        .into(binding.imgChoose);
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
                Glide.with(InformationImageActivity.this).load(intent.getStringExtra("img" + 1))
                        .placeholder(getResources().getDrawable(R.drawable.ic_image_gallery))
                        .error(getResources().getDrawable(R.drawable.ic_image_gallery))
                        .into(binding.imgChoose);
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
                Glide.with(InformationImageActivity.this).load(intent.getStringExtra("img" + 2))
                        .placeholder(getResources().getDrawable(R.drawable.ic_image_gallery))
                        .error(getResources().getDrawable(R.drawable.ic_image_gallery))
                        .into(binding.imgChoose);
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
                Glide.with(InformationImageActivity.this).load(intent.getStringExtra("img" + 3))
                        .placeholder(getResources().getDrawable(R.drawable.ic_image_gallery))
                        .error(getResources().getDrawable(R.drawable.ic_image_gallery))
                        .into(binding.imgChoose);
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
                Glide.with(InformationImageActivity.this).load(intent.getStringExtra("img" + 4))
                        .placeholder(getResources().getDrawable(R.drawable.ic_image_gallery))
                        .error(getResources().getDrawable(R.drawable.ic_image_gallery))
                        .into(binding.imgChoose);
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

package com.dindin.hotrovndemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.databinding.ActivityInformationImageBinding;

import java.util.ArrayList;
import java.util.List;

public class InformationImageActivity extends AppCompatActivity {
    ActivityInformationImageBinding binding;
    List<Integer> listImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_information_image);

        binding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}

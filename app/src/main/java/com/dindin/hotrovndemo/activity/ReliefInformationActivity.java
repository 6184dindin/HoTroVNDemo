package com.dindin.hotrovndemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.databinding.ActivityReliefInformationBinding;

public class ReliefInformationActivity extends AppCompatActivity {
    ActivityReliefInformationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_relief_information);
    }
}
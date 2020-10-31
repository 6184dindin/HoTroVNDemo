package com.dindin.hotrovndemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.databinding.ActivityLoginPhoneNumberBinding;

public class LoginPhoneNumber extends AppCompatActivity {
    ActivityLoginPhoneNumberBinding binding;
    Intent intent;
    int key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login_phone_number);
        intent = getIntent();
        key = intent.getIntExtra("key", 0);
        startAct();
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPhoneNumber.this, ReliefBulletinActivity.class);
//                intent.putExtra("key", intent.getIntExtra("key", 0));
                intent.putExtra("key", key);
                startActivity(intent);
            }
        });
    }
    private void startAct() {
        if(key == 1) {
            binding.layoutTermsAndPolicy.setVisibility(View.GONE);
        }
        if(key == 2) {
            binding.layoutTermsAndPolicy.setVisibility(View.VISIBLE);
        }
    }
}
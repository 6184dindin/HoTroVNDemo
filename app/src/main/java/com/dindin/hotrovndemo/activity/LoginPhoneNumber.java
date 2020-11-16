package com.dindin.hotrovndemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.databinding.ActivityLoginPhoneNumberBinding;

public class LoginPhoneNumber extends AppCompatActivity {
    ActivityLoginPhoneNumberBinding binding;
    Intent intent;
    int key;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_phone_number);
        intent = getIntent();
        key = intent.getIntExtra("key", 0);
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumber = binding.edtPhoneNumber.getText().toString();
                if (phoneNumber.equals("")) {
                    binding.edtPhoneNumber.setError(getString(R.string.error_null));
                } else if (phoneNumber.length() < 10) {
                    binding.edtPhoneNumber.setError(getString(R.string.error_length));
                } else {
                    Intent intent = new Intent(LoginPhoneNumber.this, SelectedTypeCampaignJoinedActivity.class);
                    intent.putExtra("key", key);
                    intent.putExtra("phone", phoneNumber);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }
        });
    }
}
package com.dindin.hotrovndemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.btnNeedRelief.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginPhoneNumber.class);
                intent.putExtra("key", 1);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in,R.anim.fade_in);
            }
        });
        binding.btnHelperJoined.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginPhoneNumber.class);
                intent.putExtra("key", 2);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in,R.anim.fade_in);
            }
        });
    }
}
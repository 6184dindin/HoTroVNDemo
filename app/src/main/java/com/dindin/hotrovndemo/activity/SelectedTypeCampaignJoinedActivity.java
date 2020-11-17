package com.dindin.hotrovndemo.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.databinding.ActivitySelectedTypeCampaignJoinedBinding;

public class SelectedTypeCampaignJoinedActivity extends AppCompatActivity {
    ActivitySelectedTypeCampaignJoinedBinding binding;
    Intent intent;
    int key;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_selected_type_campaign_joined);
        intent = getIntent();
        key = intent.getIntExtra("key", 0);
        phoneNumber = intent.getStringExtra("phone");
        binding.btnBack.setOnClickListener((view) -> {
            finish();
        });
        binding.btnNaturalDisasters.setOnClickListener((view) -> {
            nextAct(1);
        });
        binding.btnMedical.setOnClickListener((view) -> {
            nextAct(2);
        });
        binding.btnEducation.setOnClickListener((view) -> {
            nextAct(3);
        });
        binding.btnAccident.setOnClickListener((view) -> {
            nextAct(4);
        });
        binding.btnMissing.setOnClickListener((view) -> {
            nextAct(5);
        });
        binding.btnLonelyOldPeople.setOnClickListener((view) -> {
            nextAct(6);
        });
        binding.btnCommunity.setOnClickListener((view) -> {
            nextAct(7);
        });
    }

    private void nextAct(int type) {
        Intent intent = new Intent(SelectedTypeCampaignJoinedActivity.this, ReliefBulletinActivity.class);
        intent.putExtra("key", key);
        intent.putExtra("phone", phoneNumber);
        intent.putExtra("type", type);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
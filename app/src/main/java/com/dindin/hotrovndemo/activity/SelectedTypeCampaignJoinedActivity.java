package com.dindin.hotrovndemo.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.adapter.FieldJoinedAdapter;
import com.dindin.hotrovndemo.databinding.ActivitySelectedTypeCampaignJoinedBinding;
import com.dindin.hotrovndemo.utils.Field;
import com.dindin.hotrovndemo.utils.Helper;

import java.util.List;

public class SelectedTypeCampaignJoinedActivity extends AppCompatActivity {
    ActivitySelectedTypeCampaignJoinedBinding binding;
    Intent intent;
    int key;
    String phoneNumber;
    FieldJoinedAdapter adapter;
    List<Field> fields;

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
        fields = Helper.getFields(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.rcFiled.setLayoutManager(layoutManager);
        adapter = new FieldJoinedAdapter(fields);
        adapter.setListener(filed -> {
            nextAct((int) filed);
        });
        binding.rcFiled.setAdapter(adapter);
    }

    private void nextAct(int field) {
        Intent intent = new Intent(SelectedTypeCampaignJoinedActivity.this, ReliefBulletinActivity.class);
        intent.putExtra("key", key);
        intent.putExtra("phone", phoneNumber);
        intent.putExtra("field", field);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
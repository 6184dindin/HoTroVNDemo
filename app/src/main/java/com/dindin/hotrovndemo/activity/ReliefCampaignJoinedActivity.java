package com.dindin.hotrovndemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.dindin.hotrovndemo.Poco;
import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.databinding.ActivityReliefCampaignJoinedBinding;
import com.dindin.hotrovndemo.fragment.ShowListReliefFragment;

import java.util.ArrayList;
import java.util.List;

public class ReliefCampaignJoinedActivity extends AppCompatActivity {
    ActivityReliefCampaignJoinedBinding binding;
    Intent intent;
    int key;
    List<Poco> pocos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_relief_campaign_joined);
        intent = getIntent();
        key = intent.getIntExtra("key", 0);
        createList();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentListReliefCampaignJoined, new ShowListReliefFragment(pocos, key))
                .commit();
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void createList() {
        pocos.add(new Poco());
        pocos.add(new Poco());
        pocos.add(new Poco());
        pocos.add(new Poco());
        pocos.add(new Poco());
        pocos.add(new Poco());
    }
}
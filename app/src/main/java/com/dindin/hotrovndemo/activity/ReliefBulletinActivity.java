package com.dindin.hotrovndemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.dindin.hotrovndemo.News;
import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.databinding.ActivityReliefBulletinBinding;
import com.dindin.hotrovndemo.fragment.GoogleMapFragment;
import com.dindin.hotrovndemo.fragment.ShowListReliefFragment;

import java.util.ArrayList;
import java.util.List;

public class ReliefBulletinActivity extends AppCompatActivity {
    ActivityReliefBulletinBinding binding;
    Intent intent;
    int key;
    String phoneNumber;
    int field;
    List<News> news = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_relief_bulletin);
        intent = getIntent();
        key = intent.getIntExtra("key", 0);
        phoneNumber = intent.getStringExtra("phone");
        field = intent.getIntExtra("field", 0);
        createList();
        startAct();
    }

    private void startAct() {
        if (key == 1) {
            startActNeedRelief();
        }
        if (key == 2) {
            startActHelperJoined();
        }
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void startActNeedRelief() {
        binding.layoutAddNewsletter.setVisibility(View.VISIBLE);
        binding.btnShowListReliefJoined.setVisibility(View.GONE);
        binding.btnAddNewsletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReliefBulletinActivity.this, CreateReliefNewsletterActivity.class);
                intent.putExtra("key", key);
                intent.putExtra("phone", phoneNumber);
                intent.putExtra("field", field);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentMapAndListRelief, new ShowListReliefFragment(news, key,field,phoneNumber))
                .commit();
    }

    private void startActHelperJoined() {
        binding.layoutAddNewsletter.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentMapAndListRelief, new GoogleMapFragment(news))
                .commit();
        binding.btnShowListReliefJoined.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReliefBulletinActivity.this, ReliefCampaignJoinedActivity.class);
                intent.putExtra("key", key);
                intent.putExtra("phone", phoneNumber);
                intent.putExtra("field", field);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    private void createList() {
        news.add(new News());
        news.add(new News());
        news.add(new News());
        news.add(new News());
        news.add(new News());
        news.add(new News());
    }
}
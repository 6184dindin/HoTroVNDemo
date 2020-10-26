package com.dindin.hotrovndemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dindin.hotrovndemo.AdapterPoco;
import com.dindin.hotrovndemo.Poco;
import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.databinding.ActivityReliefBulletinBinding;

import java.util.ArrayList;
import java.util.List;

public class ReliefBulletinActivity extends AppCompatActivity {
    ActivityReliefBulletinBinding binding;
    AdapterPoco adapterPoco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_relief_bulletin);
        List<Poco> pocos = new ArrayList<>();
        pocos.add(new Poco());
        pocos.add(new Poco());
        pocos.add(new Poco());
        pocos.add(new Poco());
        pocos.add(new Poco());
        pocos.add(new Poco());
        adapterPoco = new AdapterPoco(pocos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.rcViewListRelief.setLayoutManager(layoutManager);
        binding.rcViewListRelief.setAdapter(adapterPoco);
    }
}
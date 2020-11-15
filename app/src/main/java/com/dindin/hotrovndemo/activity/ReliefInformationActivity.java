package com.dindin.hotrovndemo.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dindin.hotrovndemo.HelperJoined;
import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.adapter.AdapterHelperJoined;
import com.dindin.hotrovndemo.adapter.IAdapterHelperJoined;
import com.dindin.hotrovndemo.databinding.ActivityReliefInformationBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.graphics.Color.TRANSPARENT;

public class ReliefInformationActivity extends AppCompatActivity {
    ActivityReliefInformationBinding binding;
    List<HelperJoined> helperJoinedList;
    AdapterHelperJoined adapterHelperJoined;
    Dialog dialog;
    Intent intent;
    int key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_relief_information);
        intent = getIntent();
        key = intent.getIntExtra("key", 0);
        dialog = new Dialog(this);
        helperJoinedList = new ArrayList<>();
        helperJoinedList.add(new HelperJoined());
        helperJoinedList.add(new HelperJoined());
        adapterHelperJoined = new AdapterHelperJoined(helperJoinedList, this);
        startAct();

        adapterHelperJoined.setiAdapterHelperJoined(new IAdapterHelperJoined() {
            @Override
            public void openDialogShowInformationReliefCampaign(HelperJoined helperJoined) {
                dialog.setContentView(R.layout.dialog_show_information_relief_campaign);
                Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setGravity(Gravity.CENTER);
                dialog.findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
                dialog.show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.rcViewListReliefCampaign.setLayoutManager(layoutManager);
        binding.rcViewListReliefCampaign.setAdapter(adapterHelperJoined);
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnCreateReliefCampaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReliefInformationActivity.this, CreateReliefCampaignActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startAct() {
        if (key == 1) {
            binding.btnCreateReliefCampaign.setVisibility(View.GONE);
        }
        if (key == 2) {
        }
    }

}
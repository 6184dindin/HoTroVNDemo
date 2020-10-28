package com.dindin.hotrovndemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.dindin.hotrovndemo.adapter.AdapterHelperJoined;
import com.dindin.hotrovndemo.HelperJoined;
import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.adapter.IAdapterHelperJoined;
import com.dindin.hotrovndemo.databinding.ActivityReliefInformationBinding;

import java.util.ArrayList;
import java.util.List;

public class ReliefInformationActivity extends AppCompatActivity {
    ActivityReliefInformationBinding binding;
    List<HelperJoined> helperJoinedList;
    AdapterHelperJoined adapterHelperJoined;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_relief_information);
        dialog = new Dialog(this);
        helperJoinedList = new ArrayList<>();
        helperJoinedList.add(new HelperJoined());
        helperJoinedList.add(new HelperJoined());
        adapterHelperJoined = new AdapterHelperJoined(helperJoinedList, this);
        adapterHelperJoined.setiAdapterHelperJoined(new IAdapterHelperJoined() {
            @Override
            public void openDialogShowInformationReliefCampaign(HelperJoined helperJoined) {
                dialog.setContentView(R.layout.dialog_show_information_relief_campaign);
                dialog.findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.rcViewListReliefCampaign.setLayoutManager(layoutManager);
        binding.rcViewListReliefCampaign.setAdapter(adapterHelperJoined);
    }
}
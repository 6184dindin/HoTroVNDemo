package com.dindin.hotrovndemo.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dindin.hotrovndemo.HelperJoined;
import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.adapter.AdapterHelperJoined;
import com.dindin.hotrovndemo.adapter.IAdapterHelperJoined;
import com.dindin.hotrovndemo.databinding.ActivityReliefInformationBinding;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.graphics.Color.*;
import static com.dindin.hotrovndemo.R.id.image_1;

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
                /// click vào 5 ảnh sang màn information_image
                information_image(R.id.image_1);
                information_image(R.id.image_2);
                information_image(R.id.image_3);
                information_image(R.id.image_4);
                information_image(R.id.image_5);


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

    // xet onclick vào imge
    public void information_image(int id) {
        dialog.findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Intent intent = new Intent(ReliefInformationActivity.this, InformationImageActivity.class);
                startActivity(intent);
            }
        });
    }

}
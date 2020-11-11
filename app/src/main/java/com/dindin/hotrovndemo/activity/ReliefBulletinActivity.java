package com.dindin.hotrovndemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.dindin.hotrovndemo.Poco;
import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.databinding.ActivityReliefBulletinBinding;
import com.dindin.hotrovndemo.fragment.GoogleMapFragment;
import com.dindin.hotrovndemo.fragment.ShowListReliefFragment;
import com.dindin.hotrovndemo.utils.Helper;
import com.dindin.hotrovndemo.utils.Province;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.List;

public class ReliefBulletinActivity extends AppCompatActivity {
    ActivityReliefBulletinBinding binding;
    Intent intent;
    int key;
    List<Poco> pocos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_relief_bulletin);
        intent = getIntent();
        key = intent.getIntExtra("key", 0);
        createList();
        startAct();
    }

    private void startAct() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentMapAndListRelief, new ShowListReliefFragment(pocos, key))
                .commit();
        if (key == 1) {
            startActNeedRelief();
        }
        if (key == 2) {
            startActHelperJoined();
        }
    }

    private void startActNeedRelief() {
        binding.layoutAddNewsletter.setVisibility(View.VISIBLE);
        binding.btnNotification.setImageDrawable(getResources().getDrawable(R.drawable.ic_notification_bell));
        binding.btnAddNewsletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReliefBulletinActivity.this, CreateReliefNewsletterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    private void startActHelperJoined() {
        binding.btnNotification.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_list_circle));
        binding.layoutNavMap.setVisibility(View.VISIBLE);
        binding.btnSearch.setVisibility(View.VISIBLE);
        binding.btnGetMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentMapAndListRelief, new GoogleMapFragment(pocos))
                        .commit();
            }
        });
        binding.btnGetList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentMapAndListRelief, new ShowListReliefFragment(pocos, key))
                        .commit();
            }
        });
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.layoutSelectedAddress.setVisibility(View.VISIBLE);
                binding.btnSelectedProvince.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        binding.layoutSelectedPicker.setVisibility(View.VISIBLE);
                        binding.layoutSelectedProvince.setVisibility(View.GONE);
                        getList();
                        position = 0;
                        binding.numberPicker.setMinValue(1);
                        binding.numberPicker.setMaxValue(getList().length);
                        binding.numberPicker.setDisplayedValues(getList());
                        binding.numberPicker.setWrapSelectorWheel(false);
                        binding.numberPicker.setValue(1);
                        binding.numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                position = newVal - 1;
                            }
                        });
                        binding.btnSelected.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                binding.tvProvince.setText(provinces.get(position).getName());
                                binding.layoutSelectedPicker.setVisibility(View.GONE);
                                binding.layoutSelectedProvince.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                binding.layoutSelectedPicker.setVisibility(View.GONE);
                                binding.layoutSelectedProvince.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                });
                binding.btnFilter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        binding.layoutSelectedAddress.setVisibility(View.GONE);
                    }
                });
                binding.btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        binding.layoutSelectedAddress.setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentMapAndListRelief, new GoogleMapFragment(pocos))
                                .commit();
                    }
                });
            }
        });
    }

    List<Province> provinces;
    int position;

    private String[] getList() {
        provinces = new ArrayList<>();
        provinces = (ArrayList<Province>) Helper.getProvinces(this);
        String[] stringsNameProvince = new String[provinces.size()];
        if (!provinces.isEmpty()) {
            for (int i = 0; i < provinces.size(); i++) {
                stringsNameProvince[i] = provinces.get(i).getName();
            }
        }
        return stringsNameProvince;
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
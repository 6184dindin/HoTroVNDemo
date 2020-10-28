package com.dindin.hotrovndemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.databinding.ActivityCreateReliefNewsletterBinding;
import com.dindin.hotrovndemo.utils.Helper;
import com.dindin.hotrovndemo.utils.Province;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.List;

public class CreateReliefNewsletterActivity extends AppCompatActivity {
    ActivityCreateReliefNewsletterBinding binding;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_relief_newsletter);
        dialog = new Dialog(this);
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.dialog_notify_create_relief_newsletter_successfull);
                dialog.findViewById(R.id.btnDone).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        binding.btnSelectedProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.dialog_selected_province_district_village);
                com.shawnlin.numberpicker.NumberPicker numberPicker = dialog.findViewById(R.id.numberPicker);
                getList();
                position = 0;
                numberPicker.setMinValue(1);
                numberPicker.setMaxValue(getList().length);
                numberPicker.setDisplayedValues(getList());
                numberPicker.setWrapSelectorWheel(false);
                numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        position = newVal - 1;
                    }
                });
                dialog.findViewById(R.id.btnDone).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        binding.edtProvince.setText(provinces.get(position).getName());
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
    }
    List<Province> provinces;
    int position;
    private String[] getList() {
        provinces = new ArrayList<>();
        provinces = (ArrayList<Province>) Helper.getProvinces(this);
        String[] stringsNameProvince = new String[provinces.size()];
        if(!provinces.isEmpty()) {
            for (int i = 0; i < provinces.size(); i++) {
                stringsNameProvince[i] = provinces.get(i).getName();
            }
        }
        return stringsNameProvince;
    }
}
package com.dindin.hotrovndemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.dindin.hotrovndemo.api.APIClient;
import com.dindin.hotrovndemo.api.JsonPlaceHolderAPI;
import com.dindin.hotrovndemo.utils.Helper;
import com.dindin.hotrovndemo.utils.Province;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Test extends AppCompatActivity {


    JsonPlaceHolderAPI jsonPlaceHolderProductAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        com.shawnlin.numberpicker.NumberPicker numberPicker = findViewById(R.id.numberPicker);
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
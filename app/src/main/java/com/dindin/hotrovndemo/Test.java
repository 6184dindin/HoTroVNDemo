package com.dindin.hotrovndemo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dindin.hotrovndemo.utils.City;
import com.dindin.hotrovndemo.utils.Define;
import com.dindin.hotrovndemo.utils.InfoAddress;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        List<InfoAddress> infoAddresses = (ArrayList<InfoAddress>) Define.getProvinces(this);

        City city = new City();
        List<City> cities = Define.getCities(this);
        cities.get(0).getInfoAddresses().get(0).getName();



        TextView textView = findViewById(R.id.tv);

        Integer integer = -123456789;
        Date date = new Date(integer);

        textView.setText(Define.dfDateTime.format(date));
    }
}
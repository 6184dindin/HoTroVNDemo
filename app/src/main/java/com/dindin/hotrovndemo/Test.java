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
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Test extends AppCompatActivity {

    GoogleMap mMap;
    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mapView = findViewById(R.id.myMap);
    }

    public GoogleMap getmMap() {
        return mMap;
    }
}
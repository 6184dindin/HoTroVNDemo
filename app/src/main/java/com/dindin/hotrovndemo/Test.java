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
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonPlaceHolderProductAPI = APIClient.getClient("https://14.248.83.116:6060")
                        .create(JsonPlaceHolderAPI.class);
                jsonPlaceHolderProductAPI.getlisthelpjobsbyphone("1", "04E17D83-E3F2-46B4-861B-AE4ABD2D8F03")
                .enqueue(new Callback<List<Poco>>() {
                    @Override
                    public void onResponse(Call<List<Poco>> call, Response<List<Poco>> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getBaseContext(), String.valueOf(response.body().size()),Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getBaseContext(), "fail1",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Poco>> call, Throwable t) {
                        Log.d("Test", t.getMessage());
                    }
                });
            }
        });
    }
}
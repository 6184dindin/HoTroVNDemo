package com.dindin.hotrovndemo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.dindin.hotrovndemo.adapter.AdapterPoco;
import com.dindin.hotrovndemo.Poco;
import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.databinding.ActivityReliefBulletinBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class ReliefBulletinActivity extends AppCompatActivity {
    ActivityReliefBulletinBinding binding;
    AdapterPoco adapterPoco;
    GoogleMap map;
    FusedLocationProviderClient fusedLocationProviderClient;

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

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        binding.googleMap.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(ReliefBulletinActivity.this, Manifest.permission.INTERNET)
                + ContextCompat.checkSelfPermission(ReliefBulletinActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                + ContextCompat.checkSelfPermission(ReliefBulletinActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            createMap();
        } else {
            ActivityCompat.requestPermissions(ReliefBulletinActivity.this,
                    new String[]{Manifest.permission.INTERNET,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, 113);
        }
    }

    void createMap() {
        binding.googleMap.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                if (ActivityCompat.checkSelfPermission(ReliefBulletinActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(ReliefBulletinActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if(location != null) {
                            Toast.makeText(getBaseContext(), "Successful", Toast.LENGTH_LONG).show();
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            Marker marker = map.addMarker(new MarkerOptions().position(latLng)
                                                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView())));
                            marker.setTag(1);
                            CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(13).build();
                            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker m) {
                                    int position = (int) m.getTag();
                                    Toast.makeText(getBaseContext(),String.valueOf(position), Toast.LENGTH_LONG).show();
                                    return false;
                                }
                            });
                        }
                    }
                });
            }
        });
        binding.googleMap.onStart();
    }
    private Bitmap getMarkerBitmapFromView() {
        View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_marker_on_map, null);
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 113) {
            if((grantResults.length > 0)) {
                int grantResultTotal = 0;
                for(int grantResult : grantResults) {
                    grantResultTotal += grantResult;
                }
                if(grantResultTotal == PackageManager.PERMISSION_GRANTED){
                    createMap();
                }
            }
        }
    }
}
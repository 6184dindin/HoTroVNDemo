package com.dindin.hotrovndemo.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.dindin.hotrovndemo.News;
import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.activity.ReliefInformationActivity;
import com.dindin.hotrovndemo.databinding.DialogSelectedProvinceCityDistrictBinding;
import com.dindin.hotrovndemo.databinding.FragmentGoogleMapBinding;
import com.dindin.hotrovndemo.utils.City;
import com.dindin.hotrovndemo.utils.District;
import com.dindin.hotrovndemo.utils.Helper;
import com.dindin.hotrovndemo.utils.InfoAddress;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Objects;

import static android.graphics.Color.TRANSPARENT;

public class GoogleMapFragment extends Fragment {
    FragmentGoogleMapBinding binding;

    List<News> news;
    Dialog dialog;

    List<InfoAddress> provinces;
    List<City> cities;
    List<District> districts;

    GoogleMap map;

    public GoogleMapFragment(List<News> newsList) {
        this.news = newsList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_google_map, container, false);

        dialog = new Dialog(getContext());

        provinces = Helper.getProvinces(getContext());
        cities = Helper.getCities(getContext());
        districts = Helper.getDistricts(getContext());

//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        binding.googleMap.onCreate(savedInstanceState);
        createMap();
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogSelectedProvinceCityDistrictBinding binding1 = DialogSelectedProvinceCityDistrictBinding.inflate(LayoutInflater.from(getContext()));
                binding1.btnDismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                binding1.btnSelectedProvince.setOnClickListener(v1 -> {
                    binding1.layoutSelectedPicker.setVisibility(View.VISIBLE);
                    infoAddress = provinces.get(0);
                    binding1.tvSelected.setText(getResources().getString(R.string.selected_province));
                    String[] strings = Helper.getNameInfoAddress(provinces);
                    binding1.numberPicker.setMinValue(1);
                    binding1.numberPicker.setMaxValue(strings.length);
                    binding1.numberPicker.setDisplayedValues(strings);
                    binding1.numberPicker.setWrapSelectorWheel(false);
                    binding1.numberPicker.setValue(1);
                    binding1.numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
                        infoAddress = provinces.get(newVal - 1);
                    });
                    binding1.btnSelected.setOnClickListener(v4 -> {
                        binding1.tvProvince.setText(infoAddress.getName());
                        binding1.tvCity.setText(getResources().getString(R.string.selected_city));
                        binding1.tvDistrict.setText(getResources().getString(R.string.selected_district));
                        binding1.layoutSelectedPicker.setVisibility(View.GONE);
                        posProvince = infoAddress.getId();
                        posCity = 0;
                        posDistrict = 0;
                        for (City c : cities) {
                            if(c.getId().equals(infoAddress.getId())) {
                                city = c;
                                break;
                            }
                        }
                    });
                });
                binding1.btnSelectedCity.setOnClickListener(v2 -> {
                    if(city == null) {
                        return;
                    }
                    List<InfoAddress> infoAddresses = city.getInfoAddresses();
                    binding1.layoutSelectedPicker.setVisibility(View.VISIBLE);
                    infoAddress = infoAddresses.get(0);
                    binding1.tvSelected.setText(getResources().getString(R.string.selected_city));
                    String[] strings = Helper.getNameInfoAddress(infoAddresses);
                    binding1.numberPicker.setMinValue(1);
                    binding1.numberPicker.setMaxValue(strings.length);
                    binding1.numberPicker.setDisplayedValues(strings);
                    binding1.numberPicker.setWrapSelectorWheel(false);
                    binding1.numberPicker.setValue(1);
                    binding1.numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
                        infoAddress = infoAddresses.get(newVal - 1);
                    });
                    binding1.btnSelected.setOnClickListener(v1 -> {
                        binding1.tvCity.setText(infoAddress.getName());
                        binding1.tvDistrict.setText(getResources().getString(R.string.selected_district));
                        binding1.layoutSelectedPicker.setVisibility(View.GONE);
                        posCity = infoAddress.getId();
                        posDistrict = 0;
                        for (District d : districts) {
                            if(d.getId().equals(infoAddress.getId())) {
                                district = d;
                                break;
                            }
                        }
                    });
                });
                binding1.btnSelectedDistrict.setOnClickListener(v3 -> {
                    if(district == null) {
                        return;
                    }
                    List<InfoAddress> infoAddresses = district.getInfoAddresses();
                    binding1.layoutSelectedPicker.setVisibility(View.VISIBLE);
                    infoAddress = infoAddresses.get(0);
                    binding1.tvSelected.setText(getResources().getString(R.string.selected_district));
                    String[] strings = Helper.getNameInfoAddress(infoAddresses);
                    binding1.numberPicker.setMinValue(1);
                    binding1.numberPicker.setMaxValue(strings.length);
                    binding1.numberPicker.setDisplayedValues(strings);
                    binding1.numberPicker.setWrapSelectorWheel(false);
                    binding1.numberPicker.setValue(1);
                    binding1.numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
                        infoAddress = infoAddresses.get(newVal - 1);
                    });
                    binding1.btnSelected.setOnClickListener(v1 -> {
                        binding1.tvDistrict.setText(infoAddress.getName());
                        binding1.layoutSelectedPicker.setVisibility(View.GONE);
                        posDistrict = infoAddress.getId();
                    });
                });
                dialog.setContentView(binding1.getRoot());
                Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
                dialog.show();
            }
        });
        return binding.getRoot();
    }

    InfoAddress infoAddress;
    Integer posProvince = 0;
    Integer posCity = 0;
    Integer posDistrict = 0;
    District district = null;
    City city = null;

    private void createMap() {
        binding.googleMap.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                Toast.makeText(getContext(), "Successful", Toast.LENGTH_LONG).show();
                LatLng latLng = new LatLng(21, 106);
                Marker marker = map.addMarker(new MarkerOptions().position(latLng)
                        .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView())));
                marker.setTag(1);
                CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(13).build();
                map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker m) {
                        int position = (int) m.getTag();
                        Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getContext(), ReliefInformationActivity.class);
                        startActivity(intent);
                        return false;
                    }
                });
            }
        });
        binding.googleMap.onStart();
    }

    private Bitmap getMarkerBitmapFromView() {
        View customMarkerView = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_marker_on_map, null);
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

}
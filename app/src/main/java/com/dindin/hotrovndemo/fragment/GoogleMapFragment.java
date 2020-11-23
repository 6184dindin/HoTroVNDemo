package com.dindin.hotrovndemo.fragment;

import android.annotation.SuppressLint;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.activity.ReliefInformationActivity;
import com.dindin.hotrovndemo.api.APIClient;
import com.dindin.hotrovndemo.api.APIService;
import com.dindin.hotrovndemo.api.param.base.ResponseBase;
import com.dindin.hotrovndemo.api.param.constant.SecCodeConstant;
import com.dindin.hotrovndemo.api.param.constant.URLConstant;
import com.dindin.hotrovndemo.api.param.request.GetListShortNewsRequest;
import com.dindin.hotrovndemo.api.param.response.News;
import com.dindin.hotrovndemo.databinding.DialogSelectedProvinceCityDistrictBinding;
import com.dindin.hotrovndemo.databinding.FragmentGoogleMapBinding;
import com.dindin.hotrovndemo.utils.City;
import com.dindin.hotrovndemo.utils.Define;
import com.dindin.hotrovndemo.utils.District;
import com.dindin.hotrovndemo.utils.GenericBody;
import com.dindin.hotrovndemo.utils.InfoAddress;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.graphics.Color.TRANSPARENT;

public class GoogleMapFragment extends Fragment {
    FragmentGoogleMapBinding binding;

    int key;
    String phoneNumber;
    int field;
    Dialog dialog;

    List<InfoAddress> provinces;
    List<City> cities;
    List<District> districts;

    GoogleMap map;

    InfoAddress infoAddress;
    Integer posProvince = 0;
    Integer posCity = 0;
    Integer posDistrict = 0;
    District district = null;
    City city = null;

    Date dateBegin, dateEnd;

    public GoogleMapFragment(int key, String phoneNumber, int field) {
        this.key = key;
        this.phoneNumber = phoneNumber;
        this.field = field;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_google_map, container, false);

        binding.googleMap.onCreate(savedInstanceState);

        dialog = new Dialog(getContext());

        Calendar calendar = Calendar.getInstance();
        dateEnd = calendar.getTime();
        calendar.roll(Calendar.DATE, -10);
        dateBegin = calendar.getTime();

        createMap();

        provinces = Define.getProvinces(getContext());
        cities = Define.getCities(getContext());
        districts = Define.getDistricts(getContext());

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
                binding1.btnSearch.setOnClickListener(v1 -> {
                    Calendar calendar = Calendar.getInstance();
                    dateEnd = calendar.getTime();
                    calendar.roll(Calendar.DATE, -10);
                    dateBegin = calendar.getTime();
                    createMap();
                });
                binding1.btnSelectedProvince.setOnClickListener(v1 -> {
                    binding1.layoutSelectedPicker.setVisibility(View.VISIBLE);
                    infoAddress = provinces.get(0);
                    binding1.tvSelected.setText(getResources().getString(R.string.selected_province));
                    String[] strings = Define.getNameInfoAddress(provinces);
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
                    String[] strings = Define.getNameInfoAddress(infoAddresses);
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
                    String[] strings = Define.getNameInfoAddress(infoAddresses);
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

        binding.btnRefresh.setOnClickListener(v -> {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(dateBegin);
            dateEnd = dateBegin;
            calendar1.roll(Calendar.DATE, -10);
            dateBegin = calendar1.getTime();
            createMap();
        });

        return binding.getRoot();
    }

    private void createMap() {
        GetListShortNewsRequest request = new GetListShortNewsRequest();
        request.setFieldsId(field);
        request.setCity(posCity);
        request.setDistrict(posDistrict);
        request.setProvince(posProvince);
        request.setCountry(0);
        String stringDateBegin = Define.dfDate.format(dateBegin);
        String stringDateEnd = Define.dfDate.format(dateEnd);
        request.setDateEnd(Integer.parseInt(stringDateEnd));
        request.setDateBegin(Integer.parseInt(stringDateBegin));
        request.setSecCode(SecCodeConstant.SCGetShortInfoNews);

        TypeToken<GetListShortNewsRequest> token = new TypeToken<GetListShortNewsRequest>(){};
        GenericBody<GetListShortNewsRequest> requestGenericBody = new GenericBody<>(request, token);
        APIService service = APIClient.getClient(getContext(), URLConstant.URLBaseNews).create(APIService.class);
        service.postToServerAPI(URLConstant.URLGetShortInfoNews, requestGenericBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonElement>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull JsonElement jsonElement) {
                        GsonBuilder gson = new GsonBuilder();
                        Type collectionType = new TypeToken<ResponseBase<List<News>>>(){}.getType();
                        ResponseBase<List<News>> data = gson.create().fromJson(jsonElement.getAsJsonObject().toString(), collectionType);
                        if (data.getResultCode().equals("001")){
                            List<News> news = data.getResultData();
                            if (news != null){
                                binding.googleMap.getMapAsync(googleMap -> {
                                    map = googleMap;
                                    for (int i = 0; i < news.size(); i++) {
                                        LatLng latLng = new LatLng(news.get(i).getLat(), news.get(i).getLng());
                                        Date date = new Date(news.get(i).getDateCreated());
                                        Marker marker = map.addMarker(new MarkerOptions().position(latLng)
                                                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(Define.dfDateTime.format(date),
                                                        news.get(i).getCountHelperJoined()))));
                                        marker.setTag(news.get(i).getId());
                                        CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(13).build();
                                        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                                        map.setOnMarkerClickListener(m -> {
                                            int newsId = (int) m.getTag();
                                            Intent intent = new Intent(getContext(), ReliefInformationActivity.class);
                                            intent.putExtra("newsId", newsId);
                                            intent.putExtra("key", key);
                                            intent.putExtra("phone", phoneNumber);
                                            intent.putExtra("field", field);
                                            startActivity(intent);
                                            return false;
                                        });
                                    }

                                });
                                binding.googleMap.onStart();
                            }
                            else {
                                Calendar calendar1 = Calendar.getInstance();
                                calendar1.setTime(dateBegin);
                                dateEnd = dateBegin;
                                calendar1.roll(Calendar.DATE, -10);
                                dateBegin = calendar1.getTime();
                                createMap();
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("TEST", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    private Bitmap getMarkerBitmapFromView(String dateTime, int countHelperJoin) {
        View customMarkerView = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_marker_on_map, null);
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        TextView tvDateTime = customMarkerView.findViewById(R.id.tvDateTime);
        TextView tvCountHelperJoined = customMarkerView.findViewById(R.id.tvCountHelperJoined);
        ImageView imgMarker = customMarkerView.findViewById(R.id.imgMarker);
        tvDateTime.setText(dateTime);
        tvCountHelperJoined.setText("(" + countHelperJoin + ")");
        if(countHelperJoin > 0){
            imgMarker.setImageDrawable(getResources().getDrawable(R.drawable.ic_pin_green));
        }
        else {
            imgMarker.setImageDrawable(getResources().getDrawable(R.drawable.ic_pin_red));
        }
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }

}
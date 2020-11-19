package com.dindin.hotrovndemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.dindin.hotrovndemo.api.APIClient;
import com.dindin.hotrovndemo.api.APIService;
import com.dindin.hotrovndemo.api.param.base.ResponseBase;
import com.dindin.hotrovndemo.api.param.constant.SecCodeConstant;
import com.dindin.hotrovndemo.api.param.constant.URLConstant;
import com.dindin.hotrovndemo.api.param.request.CreateNewsRequest;
import com.dindin.hotrovndemo.api.param.request.GetListHelpJobsByPhoneRequest;
import com.dindin.hotrovndemo.api.param.request.GetListSupportNewsByPhoneRequest;
import com.dindin.hotrovndemo.api.param.response.GetListHelpJobsByPhoneResponse;
import com.dindin.hotrovndemo.api.param.response.GetListSupportNewsByPhoneResponse;
import com.dindin.hotrovndemo.api.param.response.News;
import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.databinding.ActivityReliefBulletinBinding;
import com.dindin.hotrovndemo.fragment.GoogleMapFragment;
import com.dindin.hotrovndemo.fragment.ShowListReliefFragment;
import com.dindin.hotrovndemo.utils.GenericBody;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ReliefBulletinActivity extends AppCompatActivity {
    ActivityReliefBulletinBinding binding;
    Intent intent;
    int key;
    String phoneNumber;
    int field;
    List<News> news = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_relief_bulletin);
        intent = getIntent();
        key = intent.getIntExtra("key", 0);
        phoneNumber = intent.getStringExtra("phone");
        field = intent.getIntExtra("field", 0);
        getListSupportNewsByPhone();
        startAct();
    }

    private void startAct() {
        if (key == 1) {
            startActNeedRelief();
        }
        if (key == 2) {
            startActHelperJoined();
        }
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void startActNeedRelief() {
        binding.layoutAddNewsletter.setVisibility(View.VISIBLE);
        binding.btnShowListReliefJoined.setVisibility(View.GONE);
        binding.btnAddNewsletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReliefBulletinActivity.this, CreateReliefNewsletterActivity.class);
                intent.putExtra("key", key);
                intent.putExtra("phone", phoneNumber);
                intent.putExtra("field", field);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentMapAndListRelief, new ShowListReliefFragment(news, key,field,phoneNumber))
                .commit();
    }

    private void startActHelperJoined() {
        binding.layoutAddNewsletter.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentMapAndListRelief, new GoogleMapFragment(news))
                .commit();
        binding.btnShowListReliefJoined.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReliefBulletinActivity.this, ReliefCampaignJoinedActivity.class);
                intent.putExtra("key", key);
                intent.putExtra("phone", phoneNumber);
                intent.putExtra("field", field);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    private void getListSupportNewsByPhone() {
        GetListSupportNewsByPhoneRequest request = new GetListSupportNewsByPhoneRequest();
        request.setFieldsId(field);
        request.setPhoneNumber(phoneNumber);
        request.setSecCode(SecCodeConstant.SCGetListSupportNewsByPhone);

        TypeToken<GetListSupportNewsByPhoneRequest> token = new TypeToken<GetListSupportNewsByPhoneRequest>(){};
        GenericBody<GetListSupportNewsByPhoneRequest> requestGenericBody = new GenericBody<>(request, token);
        APIService service = APIClient.getClient(this, URLConstant.URLBaseNews).create(APIService.class);
        service.postToServerAPI(URLConstant.URLGetListSupportNewsByPhone, requestGenericBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonElement>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull JsonElement jsonElement) {
                        GsonBuilder gson = new GsonBuilder();
                        Type collectionType = new TypeToken<ResponseBase<GetListSupportNewsByPhoneResponse>>(){}.getType();
                        ResponseBase<GetListSupportNewsByPhoneResponse> data = gson.create().fromJson(jsonElement.getAsJsonObject().toString(), collectionType);
//                        for (int i = 0; i < data.getResultData().size(); i++){
//                            news.add(new News(data.getResultData().get(i).getId(), data.getResultData().get(i).getCountry(), data.getResultData().get(i).getProvince(),
//                                    data.getResultData().get(i).getCity(), data.getResultData().get(i).getDistrict(), data.getResultData().get(i).getVillage(),
//                                    data.getResultData().get(i).getLat(), data.getResultData().get(i).getLng(), data.getResultData().get(i).getDateNotif(),
//                                    data.getResultData().get(i).getRequestSupport(), data.getResultData().get(i).getDateCreated(), data.getResultData().get(i).getCountHelperJoined()));
//                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
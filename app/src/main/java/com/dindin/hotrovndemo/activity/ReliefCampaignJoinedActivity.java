package com.dindin.hotrovndemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.dindin.hotrovndemo.api.APIClient;
import com.dindin.hotrovndemo.api.APIService;
import com.dindin.hotrovndemo.api.param.base.ResponseBase;
import com.dindin.hotrovndemo.api.param.constant.SecCodeConstant;
import com.dindin.hotrovndemo.api.param.constant.URLConstant;
import com.dindin.hotrovndemo.api.param.request.GetListHelpJobsByPhoneRequest;
import com.dindin.hotrovndemo.api.param.request.GetListSupportNewsByPhoneRequest;
import com.dindin.hotrovndemo.api.param.response.GetListHelpJobsByPhoneResponse;
import com.dindin.hotrovndemo.api.param.response.GetListSupportNewsByPhoneResponse;
import com.dindin.hotrovndemo.api.param.response.News;
import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.databinding.ActivityReliefCampaignJoinedBinding;
import com.dindin.hotrovndemo.fragment.ShowListReliefFragment;
import com.dindin.hotrovndemo.utils.GenericBody;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

public class ReliefCampaignJoinedActivity extends AppCompatActivity {
    ActivityReliefCampaignJoinedBinding binding;
    Intent intent;
    int key;
    String phoneNumber;
    int field;
    List<News> news = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_relief_campaign_joined);
        intent = getIntent();
        key = intent.getIntExtra("key", 0);
        phoneNumber = intent.getStringExtra("phone");
        field = intent.getIntExtra("field", 0);
        getListHelpJobsByPhone();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentListReliefCampaignJoined, new ShowListReliefFragment(news, key, field, phoneNumber))
                .commit();
        binding.btnBack.setOnClickListener(v -> finish());
    }

    private void getListHelpJobsByPhone(){
        GetListHelpJobsByPhoneRequest request = new GetListHelpJobsByPhoneRequest();
        request.setFieldsId(field);
        request.setPhoneNumber(phoneNumber);
        request.setSecCode(SecCodeConstant.SCGetListHelpJobsByPhone);

        TypeToken<GetListHelpJobsByPhoneRequest> token = new TypeToken<GetListHelpJobsByPhoneRequest>(){};
        GenericBody<GetListHelpJobsByPhoneRequest> requestGenericBody = new GenericBody<>(request, token);
        APIService service = APIClient.getClient(this, URLConstant.URLBaseNews).create(APIService.class);
        service.postToServerAPI(URLConstant.URLGetListHelpJobsByPhone, requestGenericBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonElement>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull JsonElement jsonElement) {
                        GsonBuilder gson = new GsonBuilder();
                        Type collectionType = new TypeToken<ResponseBase<GetListHelpJobsByPhoneResponse>>(){}.getType();
                        ResponseBase<GetListHelpJobsByPhoneResponse> data = gson.create().fromJson(jsonElement.getAsJsonObject().toString(), collectionType);
//                        news.add(new News(data.getResultData().getId(), data.getResultData().getCountry(), data.getResultData().getProvince(),
//                                data.getResultData().getCity(), data.getResultData().getDistrict(), data.getResultData().getVillage(),
//                                data.getResultData().getLat(), data.getResultData().getLng(), data.getResultData().getDateNotif(),
//                                data.getResultData().getRequestSupport(), data.getResultData().getDateCreated(), data.getResultData().getCountHelperJoined()));
                        Log.d("TEST Pan đẹp trai", data.getResultData().get(0).getCity().toString());
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
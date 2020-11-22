package com.dindin.hotrovndemo.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.adapter.HelperJoinedAdapter;
import com.dindin.hotrovndemo.api.APIClient;
import com.dindin.hotrovndemo.api.APIService;
import com.dindin.hotrovndemo.api.param.base.ResponseBase;
import com.dindin.hotrovndemo.api.param.constant.SecCodeConstant;
import com.dindin.hotrovndemo.api.param.constant.URLConstant;
import com.dindin.hotrovndemo.api.param.request.GetInfoNewsRequest;
import com.dindin.hotrovndemo.api.param.response.News;
import com.dindin.hotrovndemo.api.param.response.getinfonewsresponse.GetInfoNewsResponse;
import com.dindin.hotrovndemo.api.param.response.getinfonewsresponse.Helper;
import com.dindin.hotrovndemo.api.param.response.getinfonewsresponse.NewsInfo;
import com.dindin.hotrovndemo.databinding.ActivityReliefInformationBinding;
import com.dindin.hotrovndemo.utils.GenericBody;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.graphics.Color.TRANSPARENT;

public class ReliefInformationActivity extends AppCompatActivity {
    ActivityReliefInformationBinding binding;

    HelperJoinedAdapter helperJoinedAdapter;
    Dialog dialog;
    Intent intent;
    int key;
    String phoneNumber;
    int field;
    int newsId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_relief_information);
        intent = getIntent();
        key = intent.getIntExtra("key", 0);
        phoneNumber = intent.getStringExtra("phone");
        field = intent.getIntExtra("field", 0);
        newsId = intent.getIntExtra("newsId", 0);

        dialog = new Dialog(this);

        startAct();

        binding.btnBack.setOnClickListener(v -> finish());
        binding.btnCreateReliefCampaign.setOnClickListener(v -> {
            Intent intent = new Intent(ReliefInformationActivity.this, CreateReliefCampaignActivity.class);
            intent.putExtra("key", key);
            intent.putExtra("phone", phoneNumber);
            intent.putExtra("field", field);
            intent.putExtra("newsId", newsId);
            startActivity(intent);
        });
    }
    private void getInfoNews() {
        GetInfoNewsRequest request = new GetInfoNewsRequest();
        request.setNewsId(newsId);
        request.setFieldsId(field);
        request.setSecCode(SecCodeConstant.SCGetInfoNews);

        TypeToken<GetInfoNewsRequest> token = new TypeToken<GetInfoNewsRequest>() {
        };
        GenericBody<GetInfoNewsRequest> requestGenericBody = new GenericBody<GetInfoNewsRequest>(request, token);
        APIService service = APIClient.getClient(this, URLConstant.URLBaseNews).create(APIService.class);
        service.postToServerAPI(URLConstant.URLGetInfoNews, requestGenericBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonElement>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull JsonElement jsonElement) {
                        GsonBuilder gson = new GsonBuilder();
                        Type collectionType = new TypeToken<ResponseBase<List<News>>>() {
                        }.getType();
                        ResponseBase<GetInfoNewsResponse> data = gson.create().fromJson(jsonElement.getAsJsonObject().toString(), collectionType);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void setNewsInfo(NewsInfo newsInfo) {

    }
    private void setHelperList(List<Helper> helpers) {
        helperJoinedAdapter = new HelperJoinedAdapter(helpers, this);
        helperJoinedAdapter.setOnClickHelperJoinedListener(helper -> {
            dialog.setContentView(R.layout.dialog_show_information_relief_campaign);
            Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
            dialog.show();
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.rcViewListReliefCampaign.setLayoutManager(layoutManager);
        binding.rcViewListReliefCampaign.setAdapter(helperJoinedAdapter);
    }
    private void startAct() {
        if (key == 1) {
            binding.btnCreateReliefCampaign.setVisibility(View.GONE);
        }
        if (key == 2) {
        }
    }

}
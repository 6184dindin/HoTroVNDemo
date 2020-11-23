package com.dindin.hotrovndemo.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.adapter.HelperJoinedAdapter;
import com.dindin.hotrovndemo.api.APIClient;
import com.dindin.hotrovndemo.api.APIService;
import com.dindin.hotrovndemo.api.param.base.ResponseBase;
import com.dindin.hotrovndemo.api.param.constant.SecCodeConstant;
import com.dindin.hotrovndemo.api.param.constant.URLConstant;
import com.dindin.hotrovndemo.api.param.request.DownloadImageHelperRequest;
import com.dindin.hotrovndemo.api.param.request.DownloadImageNewsRequest;
import com.dindin.hotrovndemo.api.param.request.GetInfoNewsRequest;
import com.dindin.hotrovndemo.api.param.response.DownloadImageResponse;
import com.dindin.hotrovndemo.api.param.response.getinfonewsresponse.GetInfoNewsResponse;
import com.dindin.hotrovndemo.api.param.response.getinfonewsresponse.Helper;
import com.dindin.hotrovndemo.api.param.response.getinfonewsresponse.NewsInfo;
import com.dindin.hotrovndemo.databinding.ActivityReliefInformationBinding;
import com.dindin.hotrovndemo.databinding.DialogShowInformationReliefCampaignBinding;
import com.dindin.hotrovndemo.utils.Define;
import com.dindin.hotrovndemo.utils.GenericBody;
import com.dindin.hotrovndemo.utils.InfoAddress;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
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

    List<InfoAddress> provinces;
    List<InfoAddress> cities;
    List<InfoAddress> districts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_relief_information);
        intent = getIntent();
        key = intent.getIntExtra("key", 0);
        phoneNumber = intent.getStringExtra("phone");
        field = intent.getIntExtra("field", 0);
        newsId = intent.getIntExtra("newsId", 0);

        provinces = Define.getProvinces(this);
        cities = Define.getListCity(this);
        districts = Define.getListDistrict(this);

        dialog = new Dialog(this);
        startAct();
        getInfoNews();

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
                        Type collectionType = new TypeToken<ResponseBase<GetInfoNewsResponse>>() {
                        }.getType();
                        ResponseBase<GetInfoNewsResponse> data = gson.create().fromJson(jsonElement.getAsJsonObject().toString(), collectionType);
                        if (data.getResultCode().equals("001")) {
                            if (data.getResultData() != null) {
                                if (data.getResultData().getNewsInfo() != null) {
                                    setNewsInfo(data.getResultData().getNewsInfo());
                                }
                                if (data.getResultData().getHelpers() != null) {
                                    setHelperList(data.getResultData().getHelpers());
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("SetTextI18n")
    private void setNewsInfo(NewsInfo newsInfo) {
        String provinceString = "";
        String cityString = "";
        String districtString = "";

        for (InfoAddress p : provinces) {
            if (p.getId().equals(newsInfo.getProvince())) {
                provinceString = p.getName();
                break;
            }
        }
        if (!provinceString.isEmpty()) {
            for (InfoAddress c : cities) {
                if (c.getId().equals(newsInfo.getCity())) {
                    cityString = c.getName() + ", ";
                    break;
                }
            }
        }
        if (!cityString.isEmpty()) {
            for (InfoAddress d : districts) {
                if (d.getId().equals(newsInfo.getProvince())) {
                    districtString = d.getName() + ", ";
                    break;
                }
            }
        }

        binding.tvAddress.setText(districtString + cityString + provinceString);
        binding.tvRequestSupport.setText(newsInfo.getRequestSupport() != null ? newsInfo.getRequestSupport() : "");


        Date date = new Date(newsInfo.getDateCreated());
        binding.tvDateTime.setText(Define.dfDateTime.format(date));

        binding.tvAdminPostAndPhoneContact.setText((newsInfo.getAdminPost() != null ? newsInfo.getAdminPost() : "")
                + " | "
                + (newsInfo.getPhoneContact() != null ? newsInfo.getPhoneContact() : ""));

        binding.tvRolePersonPost.setText(newsInfo.getRolePersonPost() != null ? newsInfo.getRolePersonPost() : "");

        binding.btnSeeDetails.setOnClickListener(v -> {
            downloadImageNews(newsInfo.getId());
        });
    }

    private void setHelperList(List<Helper> helpers) {
        helperJoinedAdapter = new HelperJoinedAdapter(helpers, this);
        helperJoinedAdapter.setOnClickHelperJoinedListener(helper -> {
            downloadImageHelper(helper.getHelpsId());
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.rcViewListReliefCampaign.setLayoutManager(layoutManager);
        binding.rcViewListReliefCampaign.setAdapter(helperJoinedAdapter);
    }

    private void downloadImageNews(int newsId) {
        DownloadImageNewsRequest request = new DownloadImageNewsRequest();
        request.setNewsId(newsId);
        request.setType(field);
        request.setSecCode(SecCodeConstant.SCDownloadImage);

        TypeToken<DownloadImageNewsRequest> token = new TypeToken<DownloadImageNewsRequest>() {
        };
        GenericBody<DownloadImageNewsRequest> requestGenericBody = new GenericBody<DownloadImageNewsRequest>(request, token);
        APIService service = APIClient.getClient(this, URLConstant.URLBaseImage).create(APIService.class);
        service.postToServerAPI(URLConstant.URLDownloadImage, requestGenericBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonElement>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull JsonElement jsonElement) {
                        GsonBuilder gSon = new GsonBuilder();
                        Type collectionType = new TypeToken<ResponseBase<List<DownloadImageResponse>>>() {
                        }.getType();
                        ResponseBase<List<DownloadImageResponse>> data = gSon.create().fromJson(jsonElement.getAsJsonObject().toString(), collectionType);
                        if (data.getResultCode().equals("001")) {
                            showDialogInformation(1, data.getResultData());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void downloadImageHelper(int helpsId) {
        DownloadImageHelperRequest request = new DownloadImageHelperRequest();
        request.setHelpsId(helpsId);
        request.setType(field);
        request.setSecCode(SecCodeConstant.SCDownloadImageHelper);

        TypeToken<DownloadImageHelperRequest> token = new TypeToken<DownloadImageHelperRequest>() {
        };
        GenericBody<DownloadImageHelperRequest> requestGenericBody = new GenericBody<DownloadImageHelperRequest>(request, token);
        APIService service = APIClient.getClient(this, URLConstant.URLBaseImage).create(APIService.class);

        service.postToServerAPI(URLConstant.URLDownloadImageHelper, requestGenericBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonElement>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull JsonElement jsonElement) {
                        GsonBuilder gSon = new GsonBuilder();
                        Type collectionType = new TypeToken<ResponseBase<List<DownloadImageResponse>>>() {
                        }.getType();
                        ResponseBase<List<DownloadImageResponse>> data = gSon.create().fromJson(jsonElement.getAsJsonObject().toString(), collectionType);
                        if (data.getResultCode().equals("001")) {
                            showDialogInformation(2, data.getResultData());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void showDialogInformation(int type, List<DownloadImageResponse> downloadImageResponses) {
        DialogShowInformationReliefCampaignBinding binding1 = DialogShowInformationReliefCampaignBinding.inflate(LayoutInflater.from(this));
        binding1.btnClose.setOnClickListener(v -> {
            dialog.dismiss();
        });
        if (type == 1) {
            binding1.tvTitle1.setText(getResources().getString(R.string.information_relief));
        }
        if (type == 2) {
            binding1.tvTitle1.setText(getResources().getString(R.string.information_need_relief));
        }
        if (downloadImageResponses != null) {
            if (downloadImageResponses.size() > 0) {
                Glide.with(this).load(downloadImageResponses.get(0).getLinkedOutside())
                        .placeholder(getResources().getDrawable(R.drawable.ic_imagegallery))
                        .error(getResources().getDrawable(R.drawable.ic_imagegallery))
                        .into(binding1.img1);
                binding1.layoutImage.setOnClickListener(v -> {
                    Intent intent1 = new Intent(ReliefInformationActivity.this, InformationImageActivity.class);
                    for (int i = 0; i < downloadImageResponses.size(); i++) {
                        intent1.putExtra("img" + i, downloadImageResponses.get(i).getLinkedOutside());
                    }
                    intent1.putExtra("size", downloadImageResponses.size());
                    startActivity(intent1);
                });
            }
            if (downloadImageResponses.size() > 1) {
                Glide.with(this).load(downloadImageResponses.get(1).getLinkedOutside())
                        .placeholder(getResources().getDrawable(R.drawable.ic_imagegallery))
                        .error(getResources().getDrawable(R.drawable.ic_imagegallery))
                        .into(binding1.img2);
            }
            if (downloadImageResponses.size() > 2) {
                Glide.with(this).load(downloadImageResponses.get(2).getLinkedOutside())
                        .placeholder(getResources().getDrawable(R.drawable.ic_imagegallery))
                        .error(getResources().getDrawable(R.drawable.ic_imagegallery))
                        .into(binding1.img3);
            }
            if (downloadImageResponses.size() > 3) {
                Glide.with(this).load(downloadImageResponses.get(3).getLinkedOutside())
                        .placeholder(getResources().getDrawable(R.drawable.ic_imagegallery))
                        .error(getResources().getDrawable(R.drawable.ic_imagegallery))
                        .into(binding1.img4);
            }
            if (downloadImageResponses.size() > 4) {
                Glide.with(this).load(downloadImageResponses.get(4).getLinkedOutside())
                        .placeholder(getResources().getDrawable(R.drawable.ic_imagegallery))
                        .error(getResources().getDrawable(R.drawable.ic_imagegallery))
                        .into(binding1.img5);
            }
        }
        dialog.setContentView(binding1.getRoot());
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
        dialog.show();
    }

    private void startAct() {
        if (key == 1) {
            binding.btnCreateReliefCampaign.setVisibility(View.GONE);
        }
        if (key == 2) {
        }
    }

}
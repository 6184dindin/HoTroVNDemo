package com.dindin.hotrovndemo.api;

import com.dindin.hotrovndemo.Poco;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JsonPlaceHolderAPI {
    @POST("/HoTroVN/SupportNews/getlisthelpjobsbyphone")
    @FormUrlEncoded
    Call<List<Poco>> getlisthelpjobsbyphone(@Field("PhoneNumber") String PhoneNumber,
                                        @Field("SecCode") String SecCode);
}

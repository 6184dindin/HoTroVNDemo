package com.dindin.hotrovndemo.api;

import com.dindin.hotrovndemo.Poco;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JsonPlaceHolderAPI {
    @POST("/HoTroVN/SupportNews/getlistshortnewsrq")
    @FormUrlEncoded
    Call<List<Poco>> getlistshortnewsrq(@Field("Country") int Country,
                                        @Field("Province") int Province,
                                        @Field("City") int City,
                                        @Field("District") int District,
                                        @Field("Village") int Village,
                                        @Field("DateBegin") int DateBegin,
                                        @Field("DateEnd") int DateEnd,
                                        @Field("SecCode") String SecCode);
}

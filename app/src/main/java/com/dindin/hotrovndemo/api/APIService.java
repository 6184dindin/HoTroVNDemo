package com.dindin.hotrovndemo.api;

import com.dindin.hotrovndemo.utils.GenericBody;
import com.google.gson.JsonElement;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface APIService {

    @Headers("Content-Type: application/json")
    @POST
    Observable<JsonElement> postToServerAPI(@Url String fullUrl , @Body @SuppressWarnings("rawtypes") GenericBody request);
}

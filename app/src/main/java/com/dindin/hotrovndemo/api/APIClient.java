package com.dindin.hotrovndemo.api;

import android.content.Context;

import com.dindin.hotrovndemo.utils.GenericBodyTypeAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static final Gson gson = new GsonBuilder()
            .setLenient()
            .registerTypeAdapterFactory(GenericBodyTypeAdapterFactory.getGenericBodyTypeAdapterFactory())
            .create();
    public static Retrofit getClient(Context context, String url) {
        Retrofit retrofit = null;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        try {
            URL urlMain = new URL(url);

            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .baseUrl(urlMain)
                        .client(client)
                        .build();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return retrofit;
    }
}

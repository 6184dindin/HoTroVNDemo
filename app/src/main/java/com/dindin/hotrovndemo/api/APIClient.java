package com.dindin.hotrovndemo.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit=null;
    public static Retrofit getClient(String url)
    {
        OkHttpClient httpClient = new OkHttpClient.Builder().build();
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder()
                    .baseUrl(url)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return  retrofit;
    }
}

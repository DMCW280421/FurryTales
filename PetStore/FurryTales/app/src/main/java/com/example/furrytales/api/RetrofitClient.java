package com.example.furrytales.api;


import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    private static RetrofitClient retrofitClient = null;
    private API api;

    private RetrofitClient() {
        api = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(API.class);
    }

    public static RetrofitClient getInstance() {
        if (retrofitClient == null)
            retrofitClient = new RetrofitClient();
        return retrofitClient;
    }

    public API getApi() {
        return api;
    }
}

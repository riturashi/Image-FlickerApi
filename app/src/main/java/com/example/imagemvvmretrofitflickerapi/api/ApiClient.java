package com.example.imagemvvmretrofitflickerapi.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Map<String, Retrofit> mUrlToClientMap = new HashMap<>();


    public static Retrofit getClient(String rootPath) {
        Retrofit clientToReturn = mUrlToClientMap.get(rootPath);
        if (clientToReturn == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();
            Retrofit client = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .baseUrl(rootPath)
                    .build();
            clientToReturn = client;
            mUrlToClientMap.put(rootPath, client);
        }

        return clientToReturn;
    }
}

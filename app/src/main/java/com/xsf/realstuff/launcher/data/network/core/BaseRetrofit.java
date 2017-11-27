package com.xsf.realstuff.launcher.data.network.core;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: xushangfei
 * Time: created at 2017/11/19.
 * Description:
 */

public abstract class BaseRetrofit {
    public Retrofit get() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(customerBaseUrl().getBaseUrl())
                .client(getHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

    protected abstract OkHttpClient getHttpClient();

    public abstract IBaseUrl customerBaseUrl();
}


package com.xsf.realstuff.launcher.data.network.core;

import com.xsf.realstuff.launcher.common.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Author: xushangfei
 * Time: created at 2017/11/19.
 * Description:
 */

public abstract class BaseOkHttpClient {

    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

    public OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                //.cache(new Cache(FileUtils.getHttpCacheDir(mApplication), Constants.Config.HTTP_CACHE_SIZE))
                .connectTimeout(Constants.Config.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(Constants.Config.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS);
                //.addInterceptor(loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE));
        //.addInterceptor(new CacheInterceptor())

        builder = customize(builder);
        return builder.build();
    }

    protected abstract OkHttpClient.Builder customize(OkHttpClient.Builder builder);

}

package com.xsf.realstuff.launcher;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.squareup.leakcanary.LeakCanary;
import com.xsf.realstuff.BuildConfig;
import com.xsf.realstuff.launcher.common.Constants;
import com.xsf.realstuff.launcher.data.AppDataManager;
import com.xsf.realstuff.launcher.data.IDataManger;
import com.xsf.framework.util.LogUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.xsf.realstuff.launcher.common.Constants.baseUrl;

/**
 * Author: xushangfei
 * Time: created at 2017/11/6.
 * Description:
 */

public class RealStuffApplication extends Application {

    /**
     * application中不会出现内存泄露
     */

    private static Context mContext;

    /**
     * 网络相关
     */
    private static Retrofit mRetrofit;


    private static IDataManger mDataManger;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        //初始化日志
        new LogUtils.Builder()
                //设置全局的总开关
                .setLogSwitch(BuildConfig.DEBUG)
                .setGlobalTag("RealStuff")
                .setLog2FileSwitch(false)
                //输出日志是否带边框
                .setBorderSwitch(true)
                .setLogFilter(LogUtils.V);
        if (BuildConfig.DEBUG) {
            //初始化Stetho
            Stetho.initialize(
                    Stetho.newInitializerBuilder(mContext)
                            .enableDumpapp(
                                    Stetho.defaultDumperPluginsProvider(mContext))
                            .enableWebKitInspector(
                                    Stetho.defaultInspectorModulesProvider(mContext))
                            .build());
            //初始化LeakCanary
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
        }

        //初始化网络
        Gson gson = new Gson();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        OkHttpClient client = new OkHttpClient.Builder()
                //.cache(new Cache(FileUtils.getHttpCacheDir(mApplication), Constants.Config.HTTP_CACHE_SIZE))
                .connectTimeout(Constants.Config.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(Constants.Config.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                //.addInterceptor(new CacheInterceptor())

                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        //初始化数据管理
        mDataManger = AppDataManager.getInstance();


    }

    public static Context getAppContext() {
        return mContext;
    }

    public static Retrofit getRetrofit() {
        return mRetrofit;
    }

    public static IDataManger getDadaManager() {
        return mDataManger;
    }
}

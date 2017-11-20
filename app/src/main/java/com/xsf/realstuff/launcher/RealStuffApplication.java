package com.xsf.realstuff.launcher;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.xsf.framework.util.LogUtils;
import com.xsf.realstuff.BuildConfig;
import com.xsf.realstuff.launcher.data.AppDataManager;
import com.xsf.realstuff.launcher.data.IDataManger;
import com.xsf.realstuff.launcher.data.network.RealStuffHttpClient;
import com.xsf.realstuff.launcher.data.network.RealStuffRetrofit;

import retrofit2.Retrofit;

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
    private static Retrofit mGankIoRetrofit;


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
                .setBorderSwitch(false)
                .setLogFilter(LogUtils.D);
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

        //初始化gankIo网络

        mGankIoRetrofit = new RealStuffRetrofit(new RealStuffHttpClient()).get();

        //初始化数据管理
        mDataManger = AppDataManager.getInstance();


    }

    public static Context getAppContext() {
        return mContext;
    }

    public static Retrofit getGankIoRetrofit() {
        return mGankIoRetrofit;
    }

    public static IDataManger getDadaManager() {
        return mDataManger;
    }
}

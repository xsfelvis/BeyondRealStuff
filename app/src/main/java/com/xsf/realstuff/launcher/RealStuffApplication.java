package com.xsf.realstuff.launcher;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.xsf.moduleframework.util.LogUtils;
import com.xsf.realstuff.BuildConfig;

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

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        new LogUtils.Builder()
                //设置全局的总开关
                .setLogSwitch(BuildConfig.DEBUG)
                .setGlobalTag("RealStuff")
                .setLog2FileSwitch(false)
                //输出日志是否带边框
                .setBorderSwitch(true)
                .setLogFilter(LogUtils.V);

        Stetho.initialize(
                Stetho.newInitializerBuilder(mContext)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(mContext))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(mContext))
                        .build());

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
           LeakCanary.install(this);
        //Constants.isNight = dataManager.getTheme();

    }

    public static Context getAppContext() {
        return mContext;
    }
}

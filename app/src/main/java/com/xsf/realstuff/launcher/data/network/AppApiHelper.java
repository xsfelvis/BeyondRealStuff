package com.xsf.realstuff.launcher.data.network;

import com.xsf.realstuff.launcher.RealStuffApplication;
import com.xsf.realstuff.launcher.data.network.response.ThemeResponse;

import io.reactivex.Observable;

/**
 * Author: xushangfei
 * Time: created at 2017/11/8.
 * Description:
 */

public class AppApiHelper implements IApiHelper {
    IGanIoService mGankIoSerVice;

    public static AppApiHelper getInstance() {
        return SingleHolder.instance;
    }

    private static class SingleHolder {
        private static final AppApiHelper instance = new AppApiHelper();
    }


    private AppApiHelper() {
        this.mGankIoSerVice = RealStuffApplication.getGankIoRetrofit().create(IGanIoService.class);
    }

    @Override
    public Observable<ThemeResponse> getThemeDataCall(String path) {
        return mGankIoSerVice.getThemeDataCall(path);
    }

    @Override
    public Observable<ThemeResponse> getSearchDataCall(String content, String type, String page) {
        return mGankIoSerVice.getSearchDataCall(content, type, page);
    }
}

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
    IApiService mApiSerVice;

    public static AppApiHelper getInstance() {
        return SingleHolder.instance;
    }

    private static class SingleHolder {
        private static final AppApiHelper instance = new AppApiHelper();
    }


    private AppApiHelper() {
        this.mApiSerVice = RealStuffApplication.getRetrofit().create(IApiService.class);
    }

    @Override
    public Observable<ThemeResponse> getThemeDataCall(String path) {
        return mApiSerVice.getThemeDataCall(path);
    }

    @Override
    public Observable<ThemeResponse> getSearchDataCall(String content, String type, String page) {
        return mApiSerVice.getSearchDataCall(content, type, page);
    }
}

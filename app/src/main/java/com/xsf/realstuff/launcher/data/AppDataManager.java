package com.xsf.realstuff.launcher.data;

import com.xsf.realstuff.launcher.data.database.AppDbHelper;
import com.xsf.framework.base.data.IDbHelper;
import com.xsf.realstuff.launcher.data.network.AppApiHelper;
import com.xsf.realstuff.launcher.data.network.IApiHelper;
import com.xsf.realstuff.launcher.data.network.response.ThemeResponse;
import com.xsf.realstuff.launcher.data.preference.AppSharePreferences;
import com.xsf.framework.base.data.ISharePreferenceHelper;

import io.reactivex.Observable;

/**
 * Author: xushangfei
 * Time: created at 2017/11/8.
 * Description:
 */

public class AppDataManager implements IDataManger {

    IApiHelper mApiHelper;
    IDbHelper mDbHelper;
    ISharePreferenceHelper mSharePreferenecesHelper;

    public static AppDataManager getInstance() {
        return SingleHolder.instance;
    }

    private static class SingleHolder {
        private static final AppDataManager instance = new AppDataManager(
                AppApiHelper.getInstance(),
                AppDbHelper.getInstance(),
                AppSharePreferences.getInstance()
        );
    }


    private AppDataManager(IApiHelper apiHelper, IDbHelper dbHelper, ISharePreferenceHelper sharePreferenecesHelper) {
        this.mApiHelper = apiHelper;
        this.mDbHelper = dbHelper;
        this.mSharePreferenecesHelper = sharePreferenecesHelper;
    }

    @Override
    public Observable<ThemeResponse> getThemeDataCall(String path) {
        return mApiHelper.getThemeDataCall(path);
    }

    @Override
    public Observable<ThemeResponse> getSearchDataCall(String content, String type, String page) {
        return mApiHelper.getSearchDataCall(content, type, page);
    }


    @Override
    public void setTheme(boolean isNight) {
        mSharePreferenecesHelper.setTheme(isNight);
    }

    @Override
    public boolean getTheme() {
        return mSharePreferenecesHelper.getTheme();
    }

    @Override
    public void setOrder(String orderJsonStirng) {
        mSharePreferenecesHelper.setOrder(orderJsonStirng);
    }

    @Override
    public String getOrderString() {
        return mSharePreferenecesHelper.getOrderString();
    }
}

package com.xsf.realstuff.launcher.data.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.xsf.framework.base.data.ISharePreferenceHelper;
import com.xsf.realstuff.launcher.RealStuffApplication;
import com.xsf.realstuff.launcher.common.Constants;

/**
 * Author: xushangfei
 * Time: created at 2017/11/8.
 * Description:
 */

public class AppSharePreferences implements ISharePreferenceHelper {
    private String PREF_KEY_ORDER = "PREF_KEY_ORDER";
    private String MODE_NIGHT_OR_DAY = "MODE_NIGHT_OR_DAY";

    private final SharedPreferences mPrefs;

    public static AppSharePreferences getInstance() {
        return SingleHolder.instance;
    }

    private static class SingleHolder {
        private static final AppSharePreferences instance = new AppSharePreferences(RealStuffApplication.getAppContext(), Constants.DB_NAME);
    }

    private AppSharePreferences(Context context, String preferencesname) {
        mPrefs = context.getSharedPreferences(preferencesname, Context.MODE_PRIVATE);
    }

    @Override
    public void setOrder(String orderJsonStirng) {
        mPrefs.edit().putString(PREF_KEY_ORDER, orderJsonStirng).apply();
    }

    @Override
    public String getOrderString() {
        return mPrefs.getString(PREF_KEY_ORDER, "");
    }

    @Override
    public void setTheme(boolean isNight) {
        mPrefs.edit().putBoolean(MODE_NIGHT_OR_DAY, isNight).apply();
    }

    @Override
    public boolean getTheme() {
        return mPrefs.getBoolean(MODE_NIGHT_OR_DAY, false);
    }
}

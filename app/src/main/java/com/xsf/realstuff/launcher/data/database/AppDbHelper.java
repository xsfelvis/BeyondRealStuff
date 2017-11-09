package com.xsf.realstuff.launcher.data.database;

/**
 * Author: xushangfei
 * Time: created at 2017/11/8.
 * Description:
 */

public class AppDbHelper implements IDbHelper {

    public static AppDbHelper getInstance() {
        return SingleHolder.instance;
    }


    private static class SingleHolder {
        private static final AppDbHelper instance = new AppDbHelper();
    }

    private AppDbHelper() {
    }
}

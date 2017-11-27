package com.xsf.realstuff.launcher.common;

/**
 * Author: xushangfei
 * Time: created at 2017/11/6.
 * Description:
 */

public class Constants {

    public static class Config {
        public static final int HTTP_CACHE_SIZE = 20 * 1024 * 1024;
        public static final int HTTP_CONNECT_TIMEOUT = 15 * 1000;
        public static final int HTTP_READ_TIMEOUT = 20 * 1000;
        public static final int INTERVAL_TIME = 2 * 1000;
    }


    public static final String GANKIO = "http://gank.io/api/";

    public static final int PAGECOUNT = 20;

    public static String DB_NAME = "realstuff.db";
    public static String PREFERENCE_NAME = "realstuff";

    public static final int OPENSTATUS = 0;
    public static final int CLOSESTATUS = 1;

    public static boolean ERROR = false;

    public static boolean isNight;
}

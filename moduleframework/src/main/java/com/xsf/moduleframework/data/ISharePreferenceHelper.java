package com.xsf.moduleframework.data;

/**
 * Author: xushangfei
 * Time: created at 2017/11/6.
 * Description:
 */

public interface ISharePreferenceHelper {
    /**
     * 夜间 or 白天模式
     * @param isNight
     */
    void setTheme(boolean isNight);

    boolean getTheme();

}

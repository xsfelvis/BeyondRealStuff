package com.xsf.framework.base.data;

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

    /**
     * 设置首页展示
     * @param orderJsonStirng
     */
    void setOrder(String orderJsonStirng);

    String getOrderString();

}

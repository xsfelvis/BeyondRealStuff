package com.xsf.realstuff.launcher.data.network;

import com.xsf.realstuff.launcher.data.network.response.ThemeResponse;

import io.reactivex.Observable;

/**
 * Author: xushangfei
 * Time: created at 2017/11/6.
 * Description:
 */

public interface IApiHelper {
    /**
     * 获取主题配置
     * @param path
     * @return
     */
    Observable<ThemeResponse> getThemeDataCall(String path);

    /**
     * 获取搜索配置
     * @param content
     * @param type
     * @param page
     * @return
     */

    Observable<ThemeResponse> getSearchDataCall(String content,String type,String page);
}

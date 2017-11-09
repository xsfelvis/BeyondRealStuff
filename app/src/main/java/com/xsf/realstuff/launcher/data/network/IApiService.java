package com.xsf.realstuff.launcher.data.network;

import com.xsf.realstuff.launcher.common.Constants;
import com.xsf.realstuff.launcher.data.network.response.ThemeResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Author: xushangfei
 * Time: created at 2017/11/8.
 * Description:
 */

public interface IApiService {

    @GET("data/{path}")
    Observable<ThemeResponse> getThemeDataCall(@Path("path") String path);

    @GET("search/query/{content}/category/{type}/count/" + Constants.PAGECOUNT + "/page/{page}")
    Observable<ThemeResponse> getSearchDataCall(@Path("content") String content, @Path("type") String type, @Path("page") String page);
}

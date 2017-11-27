package com.xsf.realstuff.launcher.data.network;

import com.xsf.realstuff.launcher.data.network.core.BaseOkHttpClient;

import okhttp3.OkHttpClient;

/**
 * Author: xushangfei
 * Time: created at 2017/11/19.
 * Description: 干货集中营
 */

public class RealStuffHttpClient extends BaseOkHttpClient {
    @Override
    protected OkHttpClient.Builder customize(OkHttpClient.Builder builder) {
        return builder;
    }
}

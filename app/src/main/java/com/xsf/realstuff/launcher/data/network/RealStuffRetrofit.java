package com.xsf.realstuff.launcher.data.network;

import com.xsf.realstuff.launcher.data.network.core.BaseRetrofit;
import com.xsf.realstuff.launcher.data.network.core.IBaseUrl;

import okhttp3.OkHttpClient;

import static com.xsf.realstuff.launcher.common.Constants.GANKIO;

/**
 * Author: xushangfei
 * Time: created at 2017/11/19.
 * Description:
 */

public class RealStuffRetrofit extends BaseRetrofit {
    RealStuffHttpClient mHttpClient;

    @Override
    protected OkHttpClient getHttpClient() {
        return mHttpClient.getClient();
    }

    public RealStuffRetrofit(RealStuffHttpClient mHttpClient) {
        this.mHttpClient = mHttpClient;
    }

    @Override
    public IBaseUrl customerBaseUrl() {
        return new IBaseUrl() {
            @Override
            public String getBaseUrl() {
                return GANKIO;
            }
        };
    }
}

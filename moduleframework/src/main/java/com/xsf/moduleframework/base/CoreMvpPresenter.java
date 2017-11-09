package com.xsf.moduleframework.base;

/**
 * Author: xushangfei
 * Time: created at 2017/11/6.
 * Description:
 */

public interface CoreMvpPresenter<V extends CoreMvpView> {
    void attachView(V view);

    void onDetch();
}

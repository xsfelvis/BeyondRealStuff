package com.xsf.realstuff.launcher.common.base;

/**
 * Author: xushangfei
 * Time: created at 2017/11/6.
 * Description:
 */

public interface MvpPresenter<V extends MvpView> {
    void attachView(V view);

    void onDetchView();
}

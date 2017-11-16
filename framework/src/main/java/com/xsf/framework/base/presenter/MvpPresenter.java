package com.xsf.framework.base.presenter;

/**
 * Author: xushangfei
 * Time: created at 2017/11/6.
 * Description:
 */

public interface MvpPresenter<V extends MvpView> {
    void attachView(V view);

    void onDetchView();
}

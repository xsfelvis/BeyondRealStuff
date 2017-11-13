package com.xsf.realstuff.launcher.common.base;

import com.xsf.realstuff.launcher.data.IDataManger;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Author: xushangfei
 * Time: created at 2017/11/8.
 * Description:
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private IDataManger mDataManger;

    private CompositeDisposable mCompositeDisposable;

    private V mvpView;

    public BasePresenter(IDataManger mDataManger, CompositeDisposable mCompositeDisposable) {
        this.mDataManger = mDataManger;
        this.mCompositeDisposable = mCompositeDisposable;
    }

    @Override
    public void attachView(V view) {
        this.mvpView = view;
    }

    @Override
    public void onDetchView() {
        mCompositeDisposable.dispose();
    }

    public IDataManger getDataManger() {
        return mDataManger;
    }

    public CompositeDisposable getmCompositeDisposable() {
        return mCompositeDisposable;
    }

    public V getMvpView() {
        return mvpView;
    }
}

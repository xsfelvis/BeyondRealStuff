package com.xsf.moduleframework.base;

import com.xsf.moduleframework.data.IDataManger;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Author: xushangfei
 * Time: created at 2017/11/6.
 * Description: 所有Presenter必须实现此接口
 */

public class CoreBasePresenter<V extends CoreMvpView> implements CoreMvpPresenter<V> {
    private final IDataManger mDataManager;
    private final CompositeDisposable mCompositeDisposable;
    private V mvpView;

    public CoreBasePresenter(IDataManger dataManger, CompositeDisposable compositeDisposable) {
        this.mDataManager = dataManger;
        this.mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void attachView(V view) {
        this.mvpView = view;
    }

    @Override
    public void onDetch() {
        mCompositeDisposable.dispose();
    }

    public IDataManger getmDataManager() {
        return mDataManager;
    }

    public CompositeDisposable getmCompositeDisposable() {
        return mCompositeDisposable;
    }
}

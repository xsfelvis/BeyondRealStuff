package com.xsf.realstuff.launcher.presenter.Impl;

import com.xsf.realstuff.launcher.data.IDataManger;
import com.xsf.realstuff.launcher.data.network.response.ThemeResponse;
import com.xsf.realstuff.launcher.presenter.IBeautyPagePresenter;
import com.xsf.realstuff.launcher.common.BasePresenter;
import com.xsf.realstuff.launcher.ui.moudle.beautypic.view.IBeautyView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: xushangfei
 * Time: created at 2017/11/10.
 * Description:
 */

public class BeautyPagePresenterImpl<V extends IBeautyView> extends BasePresenter<V> implements IBeautyPagePresenter<V> {
    public BeautyPagePresenterImpl(IDataManger mDataManger, CompositeDisposable mCompositeDisposable) {
        super(mDataManger, mCompositeDisposable);
    }

    @Override
    public void attachView(V view) {
        super.attachView(view);
    }

    @Override
    public void loadData(String path) {
        getCompositeDisposable().add(
                getDataManger().getThemeDataCall(path)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ThemeResponse>() {
                            @Override
                            public void accept(ThemeResponse themeResponse) throws Exception {
                                getMvpView().showView(themeResponse.getResults());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getMvpView().showError();
                            }
                        }));

    }
}

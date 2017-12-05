package com.xsf.realstuff.launcher.presenter.Impl;


import com.xsf.realstuff.launcher.common.BasePresenter;
import com.xsf.realstuff.launcher.data.IDataManger;
import com.xsf.realstuff.launcher.data.network.response.ThemeResponse;
import com.xsf.realstuff.launcher.presenter.ICommonMvpPresenter;
import com.xsf.realstuff.launcher.ui.module.main.theme.view.ICommonView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class CommonPresenterImpl<V extends ICommonView> extends BasePresenter<V> implements ICommonMvpPresenter<V> {

    public CommonPresenterImpl(IDataManger dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);

    }

    @Override
    public void attachView(V view) {
        super.attachView(view);


    }

    @Override
    public void loadData(String path) {

        getCompositeDisposable().add(getDataManger()
                .getThemeDataCall(path)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ThemeResponse>() {
                    @Override
                    public void accept(ThemeResponse themeResponse) throws Exception {
                        getMvpView().showList(themeResponse.getResults());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().showError();
                    }
                }));
    }



}

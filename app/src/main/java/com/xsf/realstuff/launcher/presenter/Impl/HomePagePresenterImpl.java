package com.xsf.realstuff.launcher.presenter.Impl;

import com.xsf.realstuff.launcher.data.IDataManger;
import com.xsf.realstuff.launcher.data.network.response.Result;
import com.xsf.realstuff.launcher.data.network.response.ThemeResponse;
import com.xsf.realstuff.launcher.presenter.IHomePagePresenter;
import com.xsf.realstuff.launcher.ui.base.BasePresenter;
import com.xsf.realstuff.launcher.ui.moudle.main.homepage.view.IHomePageView;
import com.xsf.realstuff.launcher.util.LogUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: xushangfei
 * Time: created at 2017/11/8.
 * Description:
 */

public class HomePagePresenterImpl<V extends IHomePageView> extends BasePresenter<V> implements IHomePagePresenter<V> {

    public static final String TAG = "HomePagePresenterImpl";


    public HomePagePresenterImpl(IDataManger mDataManger, CompositeDisposable mCompositeDisposable) {
        super(mDataManger, mCompositeDisposable);
    }

    @Override
    public void attachView(V view) {
        super.attachView(view);
    }


    @Override
    public void loadData(String path) {
        LogUtils.d(TAG, getMvpView());
        final List<Result> list = new ArrayList<>();
        getmCompositeDisposable().add(getmDataManger().getThemeDataCall(path)
                //将返回值转换为多个Observable<Result>
                .concatMap(new Function<ThemeResponse, ObservableSource<Result>>() {
                    @Override
                    public ObservableSource<Result> apply(ThemeResponse themeResponse) throws Exception {
                        if (!themeResponse.isError()) {
                            return Observable.fromIterable(themeResponse.getResults());
                        }
                        return null;
                    }
                })
                //过滤不需要的项目
                .filter(new Predicate<Result>() {
                    @Override
                    public boolean test(Result result) throws Exception {
                        return filter(result.getType());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {
                        list.add(result);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().showError();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        getMvpView().showList(list);
                    }
                })

        );
    }

    private boolean filter(String requestType) {

        try {
            String type = URLDecoder.decode(requestType, "utf-8");
            if ("Android".equals(type) || "iOS".equals(type) || "前端".equals(type)
                    || "拓展资源".equals(type)) {
                return true;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }


}

package com.xsf.realstuff.launcher.presenter.Impl;


import com.xsf.realstuff.launcher.common.BasePresenter;
import com.xsf.realstuff.launcher.data.IDataManger;
import com.xsf.realstuff.launcher.data.network.response.Result;
import com.xsf.realstuff.launcher.presenter.IDetailMVPPresenter;
import com.xsf.realstuff.launcher.ui.module.detail.view.IDetailView;
import com.xsf.framework.util.LogUtils;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public class DetailPresenterImpl<V extends IDetailView> extends BasePresenter<V> implements IDetailMVPPresenter<V> {


    public DetailPresenterImpl(IDataManger DataManager, CompositeDisposable CompositeDisposable) {
        super(DataManager, CompositeDisposable);
        LogUtils.v("dagger", CompositeDisposable);
    }

    @Override
    public void attachView(V view) {
        super.attachView(view);
    }


    @Override
    public void queryIsLike(String id) {
       /* if (getDataManger().getIsCollnection(id)) {
            getMvpView().showLike();
        } else {
            getMvpView().showUnLike();
        }*/
    }

    @Override
    public void add(Result result) {

       /* getDataManger().addConnection(result);
        //本地存储图片
        if (result.getImages() != null) {
            for (String imgUrl : result.getImages()) {
                getDataManger().addImage(new Image(null, imgUrl, result.getId()));
            }
        }
        getMvpView().showLike();*/

    }


    @Override
    public void Cancel(String id) {
        /*getDataManger().cancelCollection(id);
        getMvpView().showUnLike();*/
    }
}

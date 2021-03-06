package com.xsf.realstuff.launcher.presenter;

import com.xsf.framework.base.presenter.MvpPresenter;
import com.xsf.realstuff.launcher.data.network.response.Result;
import com.xsf.realstuff.launcher.ui.module.detail.view.IDetailView;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public interface IDetailMVPPresenter<V extends IDetailView> extends MvpPresenter<V> {
    void queryIsLike(String id);
    void add(Result result);
    void Cancel(String id);
}


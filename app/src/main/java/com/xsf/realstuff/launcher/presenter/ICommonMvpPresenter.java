package com.xsf.realstuff.launcher.presenter;


import com.xsf.realstuff.launcher.common.base.MvpPresenter;
import com.xsf.realstuff.launcher.ui.moudle.main.theme.view.ICommonView;

/**
 * Created by Administrator on 2017/3/30 0030.
 */

public interface ICommonMvpPresenter<V extends ICommonView> extends MvpPresenter<V> {

    void loadData(String path);
}

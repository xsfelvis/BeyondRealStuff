package com.xsf.realstuff.launcher.presenter;

import com.xsf.framework.base.presenter.MvpPresenter;
import com.xsf.realstuff.launcher.ui.module.beautypic.view.IBeautyView;

/**
 * Author: xushangfei
 * Time: created at 2017/11/10.
 * Description:
 */

public interface IBeautyPagePresenter<V extends IBeautyView> extends MvpPresenter<V> {
    void loadData(String path);
}

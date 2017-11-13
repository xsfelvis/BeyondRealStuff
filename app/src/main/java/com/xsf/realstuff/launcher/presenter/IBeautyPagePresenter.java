package com.xsf.realstuff.launcher.presenter;

import com.xsf.realstuff.launcher.common.base.MvpPresenter;
import com.xsf.realstuff.launcher.ui.moudle.beautypic.view.IBeautyView;

/**
 * Author: xushangfei
 * Time: created at 2017/11/10.
 * Description:
 */

public interface IBeautyPagePresenter<V extends IBeautyView> extends MvpPresenter<V> {
    void loadData(String path);
}

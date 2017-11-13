package com.xsf.realstuff.launcher.presenter;

import com.xsf.realstuff.launcher.common.base.MvpPresenter;
import com.xsf.realstuff.launcher.ui.moudle.main.homepage.view.IHomePageView;

/**
 * Author: xushangfei
 * Time: created at 2017/11/8.
 * Description:
 */

public interface IHomePagePresenter<V extends IHomePageView> extends MvpPresenter<V>{
    void loadData(String path);
}

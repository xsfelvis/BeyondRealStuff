package com.xsf.realstuff.launcher.ui.module.main.homepage.view;

import com.xsf.realstuff.launcher.data.network.response.Result;
import com.xsf.framework.base.presenter.MvpView;

import java.util.List;

/**
 * Author: xushangfei
 * Time: created at 2017/11/8.
 * Description: HomePageFragmen刷新UI的动作
 */

public interface IHomePageView extends MvpView {
    /**
     * 展示列表
     */
    void showList(List<Result> resultList);
}

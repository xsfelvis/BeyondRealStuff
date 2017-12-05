package com.xsf.realstuff.launcher.ui.module.beautypic.view;

import com.xsf.realstuff.launcher.data.network.response.Result;
import com.xsf.framework.base.presenter.MvpView;

import java.util.List;

/**
 * Author: xushangfei
 * Time: created at 2017/11/10.
 * Description:
 */

public interface IBeautyView extends MvpView{
    void showView(List<Result> results);
}

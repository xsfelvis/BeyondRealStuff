package com.xsf.realstuff.launcher.ui.moudle.main.theme.view;


import com.xsf.framework.base.presenter.MvpView;
import com.xsf.realstuff.launcher.data.network.response.Result;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public interface ICommonView extends MvpView {
    void showList(List<Result> resultsBeen);
}

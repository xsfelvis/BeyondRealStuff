package com.xsf.realstuff.launcher.ui.base;

import android.os.Bundle;

/**
 * Author: xushangfei
 * Time: created at 2017/11/7.
 * Description:
 */

public abstract class AbstractLazyFragment extends BaseFragment {
    /**
     * 控件是否初始化完成
     */
    protected boolean isViewInitiated;
    /**
     * 页面是否可见
     */
    protected boolean isVisibleToUser;
    /**
     * 数据是否加载
     */
    protected boolean isDataInitiated;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData(false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData(false);
    }

    /**
     * 加载数据
     */
    public abstract void loadData();


    protected void prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            loadData();
            isDataInitiated = true;
        }
    }


}

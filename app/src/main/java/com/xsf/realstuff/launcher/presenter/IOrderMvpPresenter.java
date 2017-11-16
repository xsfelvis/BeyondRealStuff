package com.xsf.realstuff.launcher.presenter;


import com.xsf.framework.base.presenter.MvpPresenter;
import com.xsf.realstuff.launcher.data.model.Order;
import com.xsf.realstuff.launcher.ui.moudle.main.order.view.IOrderView;

import java.util.List;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public interface IOrderMvpPresenter<V extends IOrderView> extends MvpPresenter<V> {
    void setOrderString(List<Order> orderList);
    void getOrderList();
}

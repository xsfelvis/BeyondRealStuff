package com.xsf.realstuff.launcher.ui.module.main.order.view;

import com.xsf.framework.base.presenter.MvpView;
import com.xsf.realstuff.launcher.data.model.Order;

import java.util.List;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public interface IOrderView extends MvpView {
     void showView(List<Order> orders);
     void addOrder();
}

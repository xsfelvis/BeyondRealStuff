package com.xsf.realstuff.launcher.presenter.Impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xsf.realstuff.launcher.common.BasePresenter;
import com.xsf.realstuff.launcher.data.IDataManger;
import com.xsf.realstuff.launcher.data.model.Order;
import com.xsf.realstuff.launcher.presenter.IOrderMvpPresenter;
import com.xsf.realstuff.launcher.ui.module.main.order.view.IOrderView;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Administrator on 2017/4/18 0018.
 */
public class OrderPresenterImpl<V extends IOrderView> extends BasePresenter<V> implements IOrderMvpPresenter<V> {
    private Gson gson = new Gson();


    public OrderPresenterImpl(IDataManger DataManager, CompositeDisposable CompositeDisposable) {
        super(DataManager, CompositeDisposable);
    }

    @Override
    public void attachView(V view) {
        super.attachView(view);
    }


    @Override
    public void setOrderString(List<Order> orderList) {
        String jsonString = gson.toJson(orderList);
        getDataManger().setOrder(jsonString);
    }

    @Override
    public void getOrderList() {
        String orderJsonString = getDataManger().getOrderString();
        List<Order> retList = gson.fromJson(orderJsonString,
                new TypeToken<List<Order>>() {
                }.getType());
        if (retList != null && retList.size() > 0) {
            getMvpView().showView(retList);
        } else {
            getMvpView().addOrder();
        }
    }


}

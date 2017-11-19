package com.xsf.realstuff.launcher.ui.moudle.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xsf.framework.util.LogUtils;
import com.xsf.realstuff.R;
import com.xsf.realstuff.launcher.RealStuffApplication;
import com.xsf.realstuff.launcher.common.AbstractLazyFragment;
import com.xsf.realstuff.launcher.data.IDataManger;
import com.xsf.realstuff.launcher.data.model.Order;
import com.xsf.realstuff.launcher.ui.moudle.main.homepage.HomePageFragment;
import com.xsf.realstuff.launcher.ui.moudle.main.order.OrderActivity;
import com.xsf.realstuff.launcher.ui.moudle.main.theme.CommonFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.xsf.realstuff.launcher.common.Constants.OPENSTATUS;
import static com.xsf.realstuff.launcher.ui.moudle.main.order.OrderActivity.ORDERCHANGE;
import static com.xsf.realstuff.launcher.ui.moudle.main.order.OrderActivity.ORDERLIST;

/**
 * Author: xushangfei
 * Time: created at 2017/11/6.
 * Description:
 */

public class MainFragment extends AbstractLazyFragment {

    public static final String TAG = "MainFragment";

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.icon_add)
    ImageView iconAdd;
    @BindView(R.id.bar_layout)
    AppBarLayout barLayout;
    @BindView(R.id.addlayout)
    RelativeLayout addlayout;

    private IDataManger dataManager;
    private List<String> tabNames;
    private List<Fragment> fragmentList;
    private Unbinder mUnbinder;

    private FragmentStatePagerAdapter pagerAdapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public MainFragment() {
        this.dataManager = RealStuffApplication.getDadaManager();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tabNames = new ArrayList<>();
        fragmentList = new ArrayList<>();
        //固定栏目
        fragmentList.add(HomePageFragment.newInstance());
        tabNames.add(getResources().getString(R.string.ttitle_homepage));

        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //初始化栏目数据
        String orderString = dataManager.getOrderString();
        LogUtils.d(TAG, orderString);
        if ("".equals(orderString)) {
            tabNames.add(getResources().getString(R.string.title_android));
            tabNames.add(getResources().getString(R.string.title_ios));
            tabNames.add(getResources().getString(R.string.title_web));
            fragmentList.add(CommonFragment.newInstance(getResources().getString(R.string.title_android)));
            fragmentList.add(CommonFragment.newInstance(getResources().getString(R.string.title_ios)));
            fragmentList.add(CommonFragment.newInstance(getResources().getString(R.string.title_web)));
            viewpager.setOffscreenPageLimit(fragmentList.size());
        } else {
            Gson gson = new Gson();
            List<Order> orders = gson.fromJson(orderString,
                    new TypeToken<List<Order>>() {
                    }.getType());

            resetOrders(orders);
        }
        viewpager.setOffscreenPageLimit(fragmentList.size());
        pagerAdapter = new ViewpagerAdapter(getChildFragmentManager());
        viewpager.setAdapter(pagerAdapter);
        tablayout.setupWithViewPager(viewpager);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mUnbinder.unbind();
    }

    @OnClick({R.id.icon_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_add:
                startActivityForResult(new Intent(getActivity(), OrderActivity.class), 0);
                break;
            default:
                break;
        }
    }


    @Override
    public void loadData() {

    }


    private class ViewpagerAdapter extends FragmentStatePagerAdapter {

        public ViewpagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames.get(position);
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ORDERCHANGE) {
            viewpager.setCurrentItem(0);
            List<Order> orders = (List<Order>) data.getSerializableExtra(ORDERLIST);
            tabNames.clear();
            fragmentList.clear();
            fragmentList.add(HomePageFragment.newInstance());
            tabNames.add(getResources().getString(R.string.ttitle_homepage));
            resetOrders(orders);
            tablayout.setupWithViewPager(viewpager);
            pagerAdapter.notifyDataSetChanged();
            viewpager.setOffscreenPageLimit(fragmentList.size());

        }
    }

    private void resetOrders(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return;
        }
        for (Order order : orders) {
            if (order.getStatus() == OPENSTATUS) {
                tabNames.add(order.getTheme());
                fragmentList.add(CommonFragment.newInstance(order.getTheme()));
            }
        }
    }

}

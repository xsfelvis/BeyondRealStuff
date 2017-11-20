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
    TabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.icon_add)
    ImageView mIconAdd;
    @BindView(R.id.bar_layout)
    AppBarLayout mBarLayout;
    @BindView(R.id.addlayout)
    RelativeLayout mAddlayout;

    private IDataManger mDataManager;
    private List<String> mTabNames;
    private List<Fragment> mFragmentList;
    private Unbinder mUnbinder;

    private FragmentStatePagerAdapter mPagerAdapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public MainFragment() {
        this.mDataManager = RealStuffApplication.getDadaManager();
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
        mTabNames = new ArrayList<>();
        mFragmentList = new ArrayList<>();
        //固定栏目
        mFragmentList.add(HomePageFragment.newInstance());
        mTabNames.add(getResources().getString(R.string.ttitle_homepage));

        mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //初始化栏目数据
        initTheme();
        mViewpager.setOffscreenPageLimit(mFragmentList.size());
        mPagerAdapter = new ViewpagerAdapter(getChildFragmentManager());
        mViewpager.setAdapter(mPagerAdapter);
        mTablayout.setupWithViewPager(mViewpager);
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
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabNames.get(position);
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ORDERCHANGE) {
            mViewpager.setCurrentItem(0);
            List<Order> orders = (List<Order>) data.getSerializableExtra(ORDERLIST);
            mTabNames.clear();
            mFragmentList.clear();
            mFragmentList.add(HomePageFragment.newInstance());
            mTabNames.add(getResources().getString(R.string.ttitle_homepage));
            resetOrders(orders);
            mTablayout.setupWithViewPager(mViewpager);
            mPagerAdapter.notifyDataSetChanged();
            mViewpager.setOffscreenPageLimit(mFragmentList.size());

        }
    }

    private void resetOrders(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return;
        }
        for (Order order : orders) {
            if (order.getStatus() == OPENSTATUS) {
                mTabNames.add(order.getTheme());
                mFragmentList.add(CommonFragment.newInstance(order.getTheme()));
            }
        }
    }


    private void initTheme() {
        String orderString = mDataManager.getOrderString();
        LogUtils.d(TAG, orderString);
        if ("".equals(orderString)) {
            mTabNames.add(getResources().getString(R.string.title_android));
            mTabNames.add(getResources().getString(R.string.title_ios));
            mTabNames.add(getResources().getString(R.string.title_web));
            mFragmentList.add(CommonFragment.newInstance(getResources().getString(R.string.title_android)));
            mFragmentList.add(CommonFragment.newInstance(getResources().getString(R.string.title_ios)));
            mFragmentList.add(CommonFragment.newInstance(getResources().getString(R.string.title_web)));
            mViewpager.setOffscreenPageLimit(mFragmentList.size());
        } else {
            Gson gson = new Gson();
            List<Order> orders = gson.fromJson(orderString,
                    new TypeToken<List<Order>>() {
                    }.getType());

            resetOrders(orders);
        }
    }


}

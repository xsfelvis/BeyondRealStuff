package com.xsf.realstuff.launcher.ui.moudle.main;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
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

import com.xsf.realstuff.R;
import com.xsf.realstuff.launcher.RealStuffApplication;
import com.xsf.realstuff.launcher.data.IDataManger;
import com.xsf.realstuff.launcher.ui.base.AbstractLazyFragment;
import com.xsf.realstuff.launcher.ui.moudle.main.homepage.HomePageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    @BindView(R.id.floatButton)
    FloatingActionButton floatButton;
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
        initfbc();
        tabNames = new ArrayList<>();
        fragmentList = new ArrayList<>();
        //固定栏目
        fragmentList.add(HomePageFragment.newInstance());
        tabNames.add(getResources().getString(R.string.title1));

        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //初始化栏目数据
        String orderString = dataManager.getOrderString();
        /*if ("".equals(orderString)) {
            viewpager.setOffscreenPageLimit(4);
            tabNames.add(getResources().getString(R.string.title2));
            tabNames.add(getResources().getString(R.string.title3));
            tabNames.add(getResources().getString(R.string.title4));

            fragmentList.add(CommonFragment.newInstance("Android"));
            fragmentList.add(CommonFragment.newInstance("iOS"));
            fragmentList.add(CommonFragment.newInstance("前端"));
        } else {
            Gson gson = new Gson();
            List<Order> orders = gson.fromJson(orderString,
                    new TypeToken<List<Order>>() {
                    }.getType());
            for (Order order : orders) {
                if (order.getStatus() == OPENSTATUS) {
                    tabNames.add(order.getTheme());
                    fragmentList.add(CommonFragment.newInstance(order.getTheme()));
                }
            }
        }*/
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

    @Override
    protected void refreshUI() {

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

    private void initfbc() {
        if (dataManager.getTheme()) {
            floatButton.setImageResource(R.drawable.ic_search_brone_24dp);
        } else {
            floatButton.setImageResource(R.drawable.ic_search_white_24dp);
        }

    }

}

package com.xsf.realstuff.launcher.ui.moudle.main;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
        if ("".equals(orderString)) {
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

    @OnClick({R.id.icon_add, R.id.floatButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_add:
                startActivityForResult(new Intent(getActivity(), OrderActivity.class), 0);
                break;
            /*case R.id.floatButton:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(getActivity(), floatButton, floatButton.getTransitionName());
                    startActivity(new Intent(getActivity(), SearchActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(getActivity(), SearchActivity.class));
                }
                break;*/
            default:
                break;
        }
    }

    @Override
    protected void refreshUI() {
        TypedValue tablayoutcolor = new TypedValue();
        TypedValue addlayoutcolor = new TypedValue();
        TypedValue fbcolor = new TypedValue();
        getActivity().getTheme().resolveAttribute(R.attr.tablayoutbgcolor, tablayoutcolor, true);
        getActivity().getTheme().resolveAttribute(R.attr.addlayout, addlayoutcolor, true);
        getActivity().getTheme().resolveAttribute(R.attr.fbcolor, fbcolor, true);
        tablayout.setBackgroundColor(getResources().getColor(tablayoutcolor.resourceId));
        addlayout.setBackgroundColor(getResources().getColor(addlayoutcolor.resourceId));
        floatButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(fbcolor.resourceId)));
        initfbc();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ORDERCHANGE) {
            viewpager.setCurrentItem(0);
            List<Order> orders = (List<Order>) data.getSerializableExtra("orderlist");
            tabNames.clear();
            fragmentList.clear();
            fragmentList.add(HomePageFragment.newInstance());
            tabNames.add(getResources().getString(R.string.title1));
            for (Order order : orders) {
                if (order.getStatus() == OPENSTATUS) {
                    tabNames.add(order.getTheme());
                    fragmentList.add(CommonFragment.newInstance(order.getTheme()));
                }
            }

            tablayout.setupWithViewPager(viewpager);
            pagerAdapter.notifyDataSetChanged();
            viewpager.setOffscreenPageLimit(fragmentList.size());

        }
    }

}

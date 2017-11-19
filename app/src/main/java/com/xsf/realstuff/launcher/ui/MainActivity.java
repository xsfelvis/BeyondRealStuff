package com.xsf.realstuff.launcher.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.xsf.framework.util.FrameWorkActivityManager;
import com.xsf.realstuff.R;
import com.xsf.realstuff.launcher.common.BaseActivity;
import com.xsf.realstuff.launcher.common.BaseFragment;
import com.xsf.realstuff.launcher.ui.widget.NavigationItemView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;

import static com.xsf.realstuff.launcher.common.Constants.Config.INTERVAL_TIME;


public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    FrameLayout mContainer;
    @BindView(R.id.tab)
    PageBottomTabLayout mTab;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    Unbinder mUnbinder;
    private List<Fragment> mFragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        NavigationController navigationController = mTab.custom()
                .addItem(newItem(R.mipmap.tab_home_normal, R.mipmap.tab_home_selected))
                .addItem(newItem(R.mipmap.tab_fuli_normal, R.mipmap.tab_fuli_selected))
                .addItem(newItem(R.mipmap.tab_profile_normal, R.mipmap.tab_profile_selected))
                .build();
        mFragmentList.add(BaseFragment.newInstance(0));
        mFragmentList.add(BaseFragment.newInstance(1));
        mFragmentList.add(BaseFragment.newInstance(2));
        mViewpager.setOffscreenPageLimit(mFragmentList.size());
        PagerAdapter pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        };
        mViewpager.setAdapter(pagerAdapter);
        navigationController.setupWithViewPager(mViewpager);

    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > INTERVAL_TIME) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                FrameWorkActivityManager.getInstance().removeActivity(this);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private BaseTabItem newItem(int drawble, int drawbleSelect) {

        NavigationItemView navigationItemView = new NavigationItemView(MainActivity.this);
        navigationItemView.initialize(drawble, drawbleSelect);
        return navigationItemView;
    }




}

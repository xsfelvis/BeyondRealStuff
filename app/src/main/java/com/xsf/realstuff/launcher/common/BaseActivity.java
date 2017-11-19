package com.xsf.realstuff.launcher.common;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.xsf.framework.util.FrameWorkActivityManager;
import com.xsf.framework.util.RxBus;
import com.xsf.framework.util.image.ImageLoaderManager;
import com.xsf.realstuff.R;
import com.xsf.realstuff.launcher.data.IDataManger;

import io.reactivex.Observable;

/**
 * Author: xushangfei
 * Time: created at 2017/11/7.
 * Description:
 */

public abstract class BaseActivity extends AppCompatActivity {
    IDataManger mDataManager;
    //ActivityComponent mActivityComponent;
    Observable<Boolean> mObservable;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameWorkActivityManager.getInstance().addActivity(this);
        inject();
        //mActivityComponent.inject(this);
        //initTheme();
        //setContentView(getLayoutId());

        setColorStatusBar();
    }


    private void inject() {

        /*mActivityComponent= DaggerActivityComponent.builder().
                applicationComponent(((GankApp) getApplication()).getApplicationComponent()).
                activityModule(new ActivityModule()).build();*/
    }



    public void setColorStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            TypedValue statusBarColor = new TypedValue();
            Resources.Theme theme = getTheme();
            theme.resolveAttribute(R.attr.colorPrimaryDark, statusBarColor, true);
            Resources resources = getResources();
            window.setStatusBarColor(resources.getColor(statusBarColor.resourceId));
        }
    }


    public void initToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    @Override
    protected void onDestroy() {

        FrameWorkActivityManager.getInstance().removeActivity(this);
        RxBus.getInstance().unregister(Boolean.class, mObservable);
        ImageLoaderManager.getImageLoader().clearMemoryCache(this);
        super.onDestroy();
    }

}

package com.xsf.moduleframework.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.xsf.moduleframework.data.IDataManger;
import com.xsf.moduleframework.util.RxBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Author: xushangfei
 * Time: created at 2017/11/6.
 * Description:
 */

public abstract class CoreBaseActivity extends AppCompatActivity {

    /*
     * 解决Vector兼容性问题
     *
     * First up, this functionality was originally released in 23.2.0,
     * but then we found some memory usage and Configuration updating
     * issues so we it removed in 23.3.0. In 23.4.0 (technically a fix
     * release) we’ve re-added the same functionality but behind a flag
     * which you need to manually enable.
     *
     * http://www.jianshu.com/p/e3614e7abc03
     */
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Inject
    IDataManger mDataManager;
    Unbinder mUnbinder;

    Observable<Boolean> mObservable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        initTheme();
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        setStatusBarColor();
        mObservable = RxBus.getInstance().register(Boolean.class);
        mObservable.subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                //showAnimation();
                //refreshBackground();
                refreshUI();
                //refreshsSatusBar();
            }
        });


    }


    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }

    /**
     * 初始化依赖注入
     */
    protected abstract void inject();

    /**
     * 初始化主题色
     */

    protected abstract void initTheme();

    /**
     * 布局文件
     */
    protected abstract int getLayoutId();

    /**
     * 设置状态栏颜色
     */
    protected abstract void setStatusBarColor();

    /**
     * 显示动画
     */
    //rotected abstract void showAnimation();

    /**
     * 刷新背景
     */
    //protected abstract void refreshBackground();

    /**
     * 刷新UI
     */
    protected abstract void refreshUI();

    /**
     * 刷新状态栏
     */
    //protected abstract void refreshsSatusBar();
}

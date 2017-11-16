package com.xsf.realstuff.launcher.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;

import com.xsf.realstuff.R;
import com.xsf.realstuff.launcher.ui.moudle.main.MainFragment;
import com.xsf.realstuff.launcher.ui.moudle.beautypic.BeautyPicFragment;
import com.xsf.realstuff.launcher.ui.moudle.setting.SettingFragment;
import com.xsf.realstuff.launcher.util.RxBus;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Author: xushangfei
 * Time: created at 2017/11/7.
 * Description:
 */

public abstract class BaseFragment extends Fragment {
    protected BaseActivity mActivity;
    Observable<Boolean> mObservable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            mActivity = activity;
            mObservable = RxBus.getInstance().register(Boolean.class);
            mObservable.subscribe(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean aBoolean) throws Exception {
                    refreshUI();
                }
            });
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
        RxBus.getInstance().unregister(Boolean.class, mObservable);
    }

    protected void refreshToolbar(Toolbar toolbar) {
        TypedValue typedValue = new TypedValue();
        getActivity().getTheme().resolveAttribute(R.attr.toolbarcolor, typedValue, true);
        toolbar.setBackgroundColor(getResources().getColor(typedValue.resourceId));
    }

    public static BaseFragment newInstance(int position) {

        BaseFragment fragment;
        switch (position) {
            case 0:
                fragment = MainFragment.newInstance();
                break;
            case 1:
                fragment = BeautyPicFragment.newInstance();
                break;
            case 2:
                fragment = SettingFragment.newInstance();
                break;
            default:
                fragment = MainFragment.newInstance();
                break;
        }

        return fragment;
    }

    protected abstract void refreshUI();


}

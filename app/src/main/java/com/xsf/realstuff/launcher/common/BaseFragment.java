package com.xsf.realstuff.launcher.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;

import com.xsf.framework.util.RxBus;
import com.xsf.realstuff.R;
import com.xsf.realstuff.launcher.ui.moudle.beautypic.BeautyPicFragment;
import com.xsf.realstuff.launcher.ui.moudle.main.MainFragment;
import com.xsf.realstuff.launcher.ui.moudle.setting.SettingFragment;

import io.reactivex.Observable;

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



}

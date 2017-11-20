package com.xsf.realstuff.launcher.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;

import com.xsf.realstuff.R;

/**
 * Author: xushangfei
 * Time: created at 2017/11/7.
 * Description:
 */

public abstract class BaseFragment extends Fragment {
    protected BaseActivity mActivity;

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
    }

    protected void refreshToolbar(Toolbar toolbar) {
        TypedValue typedValue = new TypedValue();
        getActivity().getTheme().resolveAttribute(R.attr.toolbarcolor, typedValue, true);
        toolbar.setBackgroundColor(getResources().getColor(typedValue.resourceId));
    }

}

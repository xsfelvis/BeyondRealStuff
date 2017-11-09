package com.xsf.realstuff.launcher.ui.moudle;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xsf.realstuff.R;
import com.xsf.realstuff.launcher.ui.base.AbstractLazyFragment;


public class GirlsFragment extends AbstractLazyFragment {

    public static GirlsFragment newInstance() {
        return new GirlsFragment();
    }

    public GirlsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mz, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    protected void refreshUI() {

    }

    @Override
    public void loadData() {

    }

}

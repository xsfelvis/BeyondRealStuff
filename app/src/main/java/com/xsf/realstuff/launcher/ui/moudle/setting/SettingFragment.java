package com.xsf.realstuff.launcher.ui.moudle.setting;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xsf.realstuff.R;
import com.xsf.realstuff.launcher.RealStuffApplication;
import com.xsf.realstuff.launcher.common.AbstractLazyFragment;
import com.xsf.realstuff.launcher.data.IDataManger;
import com.xsf.framework.util.DataCleanManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SettingFragment extends AbstractLazyFragment {


    IDataManger mDataManager;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_clean)
    TextView mTvClean;
    @BindView(R.id.line3)
    View mLine3;
    @BindView(R.id.ll_clean)
    LinearLayout mLlClean;
    @BindView(R.id.tv_about_app)
    TextView mTvAboutApp;
    private Unbinder mUnbinder;


    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    public SettingFragment() {
        mDataManager = RealStuffApplication.getDadaManager();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar.setTitle(getResources().getString(R.string.my));
        //清除缓存
        mLlClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataCleanManager.cleanApplicationData(getActivity(), new String[0]);
                Snackbar.make(mLlClean, getResources().getString(R.string.cleancache), Snackbar.LENGTH_SHORT).show();
            }
        });
        try {
            PackageManager manager = RealStuffApplication.getAppContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(RealStuffApplication.getAppContext().getPackageName(), 0);
            String version = info.versionName;
            mTvAboutApp.setText(getResources().getString(R.string.nowcodeversion) + version);
        } catch (Exception e) {
            e.printStackTrace();
        }


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
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void loadData() {


    }


}

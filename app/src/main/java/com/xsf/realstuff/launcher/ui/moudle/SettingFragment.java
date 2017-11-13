package com.xsf.realstuff.launcher.ui.moudle;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xsf.realstuff.R;
import com.xsf.realstuff.launcher.RealStuffApplication;
import com.xsf.realstuff.launcher.common.base.AbstractLazyFragment;
import com.xsf.realstuff.launcher.data.IDataManger;
import com.xsf.realstuff.launcher.util.DataCleanManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SettingFragment extends AbstractLazyFragment {

    @BindView(R.id.switchCompat)
    SwitchCompat switchCompat;
    IDataManger dataManager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.tv_clean)
    TextView tvClean;
    @BindView(R.id.tv_night)
    TextView tvNight;
    @BindView(R.id.tv_collection)
    TextView tvCollection;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.ll_clean)
    LinearLayout llClean;
    @BindView(R.id.ll_collection)
    LinearLayout llCollection;
    @BindView(R.id.tv_about_app)
    TextView mTvAboutApp;
    private Unbinder mUnbinder;


    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    public SettingFragment() {
        dataManager = RealStuffApplication.getDadaManager();
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
        toolbar.setTitle("我的");
        llCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), CollectionActivity.class));
            }
        });
        //清除缓存
        llClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataCleanManager.cleanApplicationData(getActivity(), new String[0]);
                Snackbar.make(llClean, "清除缓存成功", Snackbar.LENGTH_SHORT).show();
            }
        });
        try {
            PackageManager manager = RealStuffApplication.getAppContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(RealStuffApplication.getAppContext().getPackageName(), 0);
            String version = info.versionName;
            mTvAboutApp.setText("当前版本号："+version);
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
    protected void refreshUI() {

    }

    @Override
    public void loadData() {


    }


}

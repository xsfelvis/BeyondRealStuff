package com.xsf.realstuff.launcher.ui.module.picdetail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.chrisbanes.photoview.PhotoView;
import com.xsf.framework.util.image.ImageLoaderManager;
import com.xsf.realstuff.R;
import com.xsf.realstuff.launcher.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PicDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.photo_view)
    PhotoView photoView;
    Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_detail);
        mUnbinder = ButterKnife.bind(this);
        initToolbar(mToolbar);
        mToolbar.setBackgroundColor(getResources().getColor(R.color.transparent));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PicDetailActivity.super.onBackPressed();
            }
        });
        ImageLoaderManager.getImageLoader().displayImage(this, getIntent().getStringExtra("img_url"), photoView);
    }


    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }
}

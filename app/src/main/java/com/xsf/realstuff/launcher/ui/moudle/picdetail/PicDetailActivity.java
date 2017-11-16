package com.xsf.realstuff.launcher.ui.moudle.picdetail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.chrisbanes.photoview.PhotoView;
import com.xsf.realstuff.R;
import com.xsf.realstuff.launcher.common.base.BaseActivity;
import com.xsf.realstuff.launcher.util.image.ImageLoaderManager;

import butterknife.BindView;

public class PicDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.photo_view)
    PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.transparent));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PicDetailActivity.super.onBackPressed();
            }
        });
        ImageLoaderManager.getImageLoader().displayImage(this,getIntent().getStringExtra("img_url"),photoView);
        //Glide.with(this).load(getIntent().getStringExtra("img_url")).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(photoView);
    }

    @Override
    protected void refreshUI() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pic_detail;
    }
}

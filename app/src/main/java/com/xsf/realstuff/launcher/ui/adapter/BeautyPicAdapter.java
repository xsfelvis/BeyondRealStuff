package com.xsf.realstuff.launcher.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.xsf.realstuff.R;
import com.xsf.realstuff.launcher.data.network.response.Result;
import com.xsf.realstuff.launcher.ui.adapter.base.CommonSimpleAdapter;
import com.xsf.realstuff.launcher.ui.adapter.base.ViewHolder;
import com.xsf.framework.util.image.ImageLoaderManager;

import java.util.List;


/**
 * Created by Administrator on 2017/4/24 0024.
 */

public class BeautyPicAdapter extends CommonSimpleAdapter<Result> {


    public BeautyPicAdapter(Context context, int layoutId, List<Result> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Result result, int position) {
        ImageLoaderManager.getImageLoader().displayImage(holder.itemView.getContext(), result.getUrl(), (ImageView) holder.getView(R.id.mzimage));
    }
}

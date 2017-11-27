package com.xsf.realstuff.launcher.ui.adapter;

import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haozhang.lib.SlantedTextView;
import com.xsf.realstuff.R;
import com.xsf.realstuff.launcher.data.network.response.Result;
import com.xsf.framework.util.DensityUtil;
import com.xsf.framework.util.image.ImageLoaderManager;

import java.util.List;


/**
 * Created by Administrator on 2017/4/5 0005.
 */

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.ViewHolder> {
    List<Result> mList;
    public static final int FIRST_STICKY_VIEW = 1;
    public static final int HAS_STICKY_VIEW = 2;
    public static final int NONE_STICKY_VIEW = 3;
    public static final String UNKNOWAUTHOR = "无名氏";
    public static final String ANDROID = "Android";
    public static final String WEB = "前端";
    public static final String IOS = "iOS";
    public static final String MORERESOURCE = "拓展资源";

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_index, parent, false);
        ViewHolder hodler = new ViewHolder(view);
        return hodler;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ViewHolder recyclerViewHolder = holder;
        recyclerViewHolder.tvTitle.setText(mList.get(position).getDesc());
        recyclerViewHolder.anthor.setText(TextUtils.isEmpty(mList.get(position).getWho()) ? UNKNOWAUTHOR : mList.get(position).getWho());

        if (mList.get(position).getImages() != null && mList.get(position).getImages().size() > 0) {
            recyclerViewHolder.ivIndex.setVisibility(View.VISIBLE);

            ImageLoaderManager.getImageLoader().displayImage(recyclerViewHolder.itemView.getContext(), mList.get(position).getImages().get(0) + "?imageView2/0/w/"
                    + DensityUtil.getScreenWidth(recyclerViewHolder.itemView.getContext()) + "/"
                    + "h/" + DensityUtil.dip2px(recyclerViewHolder.itemView.getContext(), 200), recyclerViewHolder.ivIndex);

        } else {
            recyclerViewHolder.ivIndex.setVisibility(View.GONE);

        }


        //吸顶布局
        if (position == 0) {
            recyclerViewHolder.tvToptext.setVisibility(View.INVISIBLE);
            recyclerViewHolder.itemView.setTag(FIRST_STICKY_VIEW);
        } else {
            if (!TextUtils.equals(mList.get(position).getPublishedAt(), mList.get(position - 1).getPublishedAt())) {
                recyclerViewHolder.tvToptext.setVisibility(View.VISIBLE);
                recyclerViewHolder.tvToptext.setText(mList.get(position).getPublishedAt().split("T")[0]);
                recyclerViewHolder.itemView.setTag(HAS_STICKY_VIEW);
            } else {
                recyclerViewHolder.tvToptext.setVisibility(View.GONE);
                recyclerViewHolder.itemView.setTag(NONE_STICKY_VIEW);
            }
        }
        recyclerViewHolder.itemView.setContentDescription(mList.get(position).getPublishedAt().split("T")[0]);


        //右上角标签
        recyclerViewHolder.slante.setText(mList.get(position).getType());
        switch (mList.get(position).getType()) {
            case ANDROID:
                recyclerViewHolder.slante.setSlantedBackgroundColor
                        (recyclerViewHolder.itemView.getContext().getResources().getColor(R.color.darkred));
                break;
            case IOS:
                recyclerViewHolder.slante.setSlantedBackgroundColor
                        (recyclerViewHolder.itemView.getContext().getResources().getColor(R.color.tomato));
                break;
            case WEB:
                recyclerViewHolder.slante.setSlantedBackgroundColor
                        (recyclerViewHolder.itemView.getContext().getResources().getColor(R.color.tan));
            case MORERESOURCE:
                recyclerViewHolder.slante.setSlantedBackgroundColor
                        (recyclerViewHolder.itemView.getContext().getResources().getColor(R.color.darkgoldenrod));
                break;
            default:
                break;
        }


        recyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.OnItemClick(v, position);
            }
        });
        recyclerViewHolder.ivIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.OnImageViewClick(v, position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return null != mList ? mList.size() : 0;
    }

    public void notifyData(List<Result> list) {
        this.mList = list;
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvToptext;
        ImageView ivIndex;
        TextView tvTitle;
        SlantedTextView slante;
        TextView anthor;
        android.support.v4.widget.Space space;
        AppBarLayout appBarLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            slante = (SlantedTextView) itemView.findViewById(R.id.slante);
            ivIndex = (ImageView) itemView.findViewById(R.id.iv_index);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvToptext = (TextView) itemView.findViewById(R.id.tv_top);
            space = (android.support.v4.widget.Space) itemView.findViewById(R.id.space);
            anthor = (TextView) itemView.findViewById(R.id.anthor);


        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener {
        void OnItemClick(View v, int position);

        void OnImageViewClick(View v, int position);
    }


}

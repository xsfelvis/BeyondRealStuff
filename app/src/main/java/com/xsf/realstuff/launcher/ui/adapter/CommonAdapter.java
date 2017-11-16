package com.xsf.realstuff.launcher.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xsf.realstuff.R;
import com.xsf.realstuff.launcher.data.network.response.Result;
import com.xsf.framework.util.TimeUtils;
import com.xsf.framework.util.image.ImageLoaderManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/3 0003.
 */

public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.ViewHolder> {
    private boolean fromCollection;
    private boolean fromSearch;
    private List<Result> list;
    private OnItemClickListener OnItemClickListener;

    public CommonAdapter() {
    }

    public void setData(List<Result> list) {
        this.list = list;
    }
    public void setData(List<Result> list, boolean fromCollection) {
        this.fromCollection=fromCollection;
        this.list = list;
    }
    public void setSearchData(List<Result> list, boolean fromSearch) {
        this.fromSearch = fromSearch;
        this.list = list;
    }

      public void notifyData(int startPosition, int count) {
        notifyItemRangeChanged(startPosition, count);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Result result = list.get(position);
        holder.image.setVisibility(View.GONE);
        if(fromSearch){
            holder.image.setVisibility(View.GONE);
        }
        else if(fromCollection) {
            // TODO: 2017/11/8 待增加数据库
            /*if (result.getImg() != null && result.getImg().size() > 0) {
                holder.image.setVisibility(View.VISIBLE);
                Glide.with(holder.itemView.getContext()).load(result.getImg().get(0).getImageUrl())
                        .asBitmap().centerCrop().into(holder.image);
            }*/
        }else{
            if (result.getImages() != null && result.getImages().size() > 0) {
                holder.image.setVisibility(View.VISIBLE);
                ImageLoaderManager.getImageLoader().displayImage(holder.itemView.getContext(),result.getImages().get(0),holder.image);
            }
        }

        holder.text.setText(result.getDesc());
        if(fromSearch) {
            holder.author.setText(result.getType()+" · "+result.getWho() + " · " + TimeUtils.friendlyTimeFormat(result.getPublishedAt()));
        }else{
            holder.author.setText(result.getWho() + " · " + TimeUtils.friendlyTimeFormat(result.getPublishedAt()));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.OnItemClick(v, position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.author)
        TextView author;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener {
        void OnItemClick(View v, int position);
    }


}

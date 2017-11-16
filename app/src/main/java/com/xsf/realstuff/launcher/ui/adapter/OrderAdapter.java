package com.xsf.realstuff.launcher.ui.adapter;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;

import com.xsf.realstuff.R;
import com.xsf.realstuff.launcher.data.model.Order;
import com.xsf.realstuff.launcher.ui.adapter.base.CommonSimpleAdapter;
import com.xsf.realstuff.launcher.ui.adapter.base.ViewHolder;

import java.util.List;

import static com.xsf.realstuff.launcher.common.Constants.OPENSTATUS;


/**
 * Created by Administrator on 2017/5/2 0002.
 */

public class OrderAdapter extends CommonSimpleAdapter<Order> {
    private SwitchChangeCallback mSwitchChangeCallback;

    public OrderAdapter(Context context, int layoutId, List<Order> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Order order, final int position) {
        holder.setText(R.id.title, order.getTheme());
        ((SwitchCompat) holder.getView(R.id.switchCompat)).setChecked(order.getStatus() == OPENSTATUS);
        ((SwitchCompat) holder.getView(R.id.switchCompat)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSwitchChangeCallback.OnChange(position, isChecked);
            }
        });
    }

    public void setOnItemCheckedChanged(SwitchChangeCallback switchChangeCallback) {
        this.mSwitchChangeCallback = switchChangeCallback;
    }

    public interface SwitchChangeCallback {
        void OnChange(int position, boolean isChecked);
    }
}

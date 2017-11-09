package com.xsf.moduleframework.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.xsf.moduleframework.R;

import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;

/**
 * Author: xushangfei
 * Time: created at 2017/11/7.
 * Description:
 */

public class NavigationItemView extends BaseTabItem {
    private ImageView mIcon;

    private int mDefaultDrawable;
    private int mCheckedDrawable;

    public NavigationItemView(Context context) {
        this(context, null);
    }

    public NavigationItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.item_only_icon, this, true);

        mIcon = (ImageView) findViewById(R.id.icon);
    }

    public void initialize(@DrawableRes int drawableRes, @DrawableRes int checkedDrawableRes) {
        mDefaultDrawable = drawableRes;
        mCheckedDrawable = checkedDrawableRes;
    }

    @Override
    public void setChecked(boolean checked) {
        mIcon.setImageResource(checked ? mCheckedDrawable : mDefaultDrawable);
    }

    @Override
    public void setMessageNumber(int number) {
        //设置红点消息数目
    }

    @Override
    public void setHasMessage(boolean hasMessage) {
        //设置红点消息
    }

    @Override
    public String getTitle() {
        return "no title";
    }
}

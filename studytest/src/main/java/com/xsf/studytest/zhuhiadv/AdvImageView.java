package com.xsf.studytest.zhuhiadv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Author: xushangfei
 * Time: created at 2017/12/14.
 * Description:利用drawable本身可以绘制
 */

public class AdvImageView extends AppCompatImageView {
    private int mDy;
    private int mMinDy;

    public AdvImageView(Context context) {
        this(context, null);
    }

    public AdvImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdvImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMinDy = h;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        int w = getWidth();
        int h = (int) (getWidth() * 1.0f / drawable.getIntrinsicWidth() * drawable.getIntrinsicHeight());
        drawable.setBounds(0, 0, w, h);
        canvas.save();
        canvas.translate(0, -getDy());
        super.onDraw(canvas);
        canvas.restore();

    }

    public void setDy(int dy) {
        if (getDrawable() == null) {
            return;
        }
        mDy = dy - mMinDy;
        if (mDy <= 0) {
            mDy = 0;
        }
        if (mDy > getDrawable().getBounds().height() - mMinDy) {
            mDy = getDrawable().getBounds().height() - mMinDy;
        }
        invalidate();
    }

    public int getDy() {
        return mDy;
    }


}

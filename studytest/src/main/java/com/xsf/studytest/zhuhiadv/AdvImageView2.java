package com.xsf.studytest.zhuhiadv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Author: xushangfei
 * Time: created at 2017/12/14.
 * Description:利用bitmap
 */

public class AdvImageView2 extends AppCompatImageView {
    private int mDy;
    private RectF mBitmapRectf;
    private Bitmap mBitmap;
    private int mMinDy;

    public AdvImageView2(Context context) {
        this(context, null);
    }

    public AdvImageView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdvImageView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMinDy = h;
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        mBitmap = drawableToBitmap(drawable);
        mBitmapRectf = new RectF(0, 0, w, mBitmap.getHeight() * w / mBitmap.getWidth());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmap == null) {
            return;
        }
        canvas.save();
        canvas.translate(0, -getDy());
        canvas.drawBitmap(mBitmap, null, mBitmapRectf, null);
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

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = (int) (getWidth() * 1.0f / drawable.getIntrinsicWidth() * drawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }


}

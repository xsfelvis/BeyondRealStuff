package com.xsf.realstuff.launcher.util.image;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.xsf.realstuff.R;


/**
 * 每次图片加载的配置项
 * Created by hzchenboning on 17/10/9.
 */

public class DisplayImageConfig {
    final int imageResOnLoading;
    final int imageResOnFail;
    final Drawable drawableOnLoading;
    final Drawable drawableOnFail;
    final Priority priority;
    final boolean cacheOnDisk;
    final boolean cacheOnMemory;
    final boolean needThumbnail;
    final float thumbnail;
    final BitmapTransformation transformation;

    private DisplayImageConfig(Builder builder) {
        this.imageResOnLoading = builder.imageResOnLoading;
        this.imageResOnFail = builder.imageResOnFail;
        this.priority = builder.priority;
        this.cacheOnDisk = builder.cacheOnDisk;
        this.cacheOnMemory = builder.cacheOnMemory;
        this.needThumbnail = builder.needThumbnail;
        this.thumbnail = builder.thumbnail;
        this.transformation = builder.transformation;
        this.drawableOnLoading = builder.drawableOnLoading;
        this.drawableOnFail = builder.drawableOnFail;
    }

    public int getImageResOnLoading() {
        return imageResOnLoading;
    }

    public int getImageResOnFail() {
        return imageResOnFail;
    }

    public Priority getPriority() {
        return priority;
    }

    public boolean isCacheOnDisk() {
        return cacheOnDisk;
    }

    public boolean isCacheOnMemory() {
        return cacheOnMemory;
    }

    public boolean isNeedThumbnail() {
        return needThumbnail;
    }

    public float getThumbnail() {
        return thumbnail;
    }

    public Drawable getDrawableOnLoading() {
        return drawableOnLoading;
    }

    public Drawable getDrawableOnFail() {
        return drawableOnFail;
    }

    public BitmapTransformation getTransformation() {
        return transformation;
    }

    public static class Builder {
        int imageResOnLoading = R.color.night_background;//加载中显示的图片或颜色
        int imageResOnFail = R.color.night_background;//加载失败后显示的图片或颜色
        Drawable drawableOnLoading = null;
        Drawable drawableOnFail = null;
        Priority priority = Priority.NORMAL;//加载优先级
        boolean cacheOnDisk = true;
        boolean cacheOnMemory = true;
        boolean needThumbnail = false;//是否先显示缩略图
        float thumbnail = 0.1f;//缩略图为原图的十分之一

        BitmapTransformation transformation = null;

        public Builder setImageResOnLoading(int imageResOnLoading) {
            this.imageResOnLoading = imageResOnLoading;
            return this;
        }

        public Builder setImageResOnFail(int imageResOnFail) {
            this.imageResOnFail = imageResOnFail;
            return this;
        }

        public Builder setPriority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public Builder setCacheOnDisk(boolean cacheOnDisk) {
            this.cacheOnDisk = cacheOnDisk;
            return this;
        }

        public Builder setCacheOnMemory(boolean cacheOnMemory) {
            this.cacheOnMemory = cacheOnMemory;
            return this;
        }

        public Builder setNeedThumbnail(boolean needThumbnail) {
            this.needThumbnail = needThumbnail;
            return this;
        }

        public Builder setThumbnail(float thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public Builder setTransformation(BitmapTransformation transformation) {
            this.transformation = transformation;
            return this;
        }

        public Builder setDrawableOnLoading(Drawable drawableOnLoading) {
            this.drawableOnLoading = drawableOnLoading;
            return this;
        }

        public Builder setDrawableOnFail(Drawable drawableOnFail) {
            this.drawableOnFail = drawableOnFail;
            return this;
        }

        public DisplayImageConfig build() {
            return new DisplayImageConfig(this);
        }
    }

    public enum Priority {
        IMMEDIATE,  //0ms
        LOW,        //300ms
        NORMAL,     //100ms
        HIGH        //50ms
    }
}

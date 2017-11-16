package com.xsf.realstuff.launcher.util.image.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.xsf.realstuff.launcher.util.LogUtils;
import com.xsf.realstuff.launcher.util.image.DisplayImageConfig;
import com.xsf.realstuff.launcher.util.image.IImageApiProvider;
import com.xsf.realstuff.launcher.util.image.ImageLoaderManager;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * Author: xushangfei
 * Time: created at 2017/11/16.
 * Description:
 */

public class ImageApiProvideImpl implements IImageApiProvider {
    @Override
    public void displayImage(Context context, String imageUrl, ImageView imageView) {
        doDisplayImage(context, imageUrl, imageView, null, null, 0, 0, null);
    }

    @Override
    public void displayImage(Context context, String imageUrl, ImageView imageView, int width, int height) {
        doDisplayImage(context, imageUrl, imageView, null, null, width, height, null);
    }

    @Override
    public void displayImage(Context context, String imageUrl, ImageView imageView, DisplayImageConfig config) {
        doDisplayImage(context, imageUrl, imageView, config, null, 0, 0, null);
    }

    @Override
    public void displayImage(Context context, String imageUrl, ImageView imageView, DisplayImageConfig config, int width, int height) {
        doDisplayImage(context, imageUrl, imageView, config, null, width, height, null);
    }

    @Override
    public void displayImage(Context context, File file, ImageView imageView) {
        doDisplayImage(context, file, imageView, null, null, 0, 0, null);
    }

    @Override
    public void displayImage(Context context, File file, ImageView imageView, DisplayImageConfig config) {
        doDisplayImage(context, file, imageView, config, null, 0, 0, null);
    }

    @Override
    public void displayImage(Context context, File file, ImageView imageView, DisplayImageConfig config, int width, int height) {

    }

    @Override
    public void displayImage(Context context, int ResId, ImageView imageView) {

    }

    @Override
    public void displayImage(Context context, int ResId, ImageView imageView, DisplayImageConfig config) {

    }

    @Override
    public void displayImage(Context context, String imageUrl, ImageView imageView, ResourceListener listener) {

    }

    @Override
    public void displayImage(Context context, String imageUrl, ImageView imageView, DisplayImageConfig config, ResourceListener listener) {

    }

    @Override
    public void displayBlurImage(Context context, String imageUrl, ImageView imageView, int radius, int sigma) {

    }

    @Override
    public void displayBlurImage(Context context, String imageUrl, ImageView imageView, int radius) {

    }

    @Override
    public void displayCircleImage(Context context, String imageUrl, ImageView imageView) {

    }

    @Override
    public void displayCircleImage(Context context, String imageUrl, ImageView imageView, DisplayImageConfig config) {

    }

    @Override
    public void loadImage(Context context, String imageUrl, ResourceListener resourceListener) {

    }

    @Override
    public void loadImage(Context context, String imageUrl, ResourceListener resourceListener, int width, int height) {

    }

    @Override
    public void clearMemoryCache(Context context) {

    }

    @Override
    public Bitmap getBitmapFromCache(String url) {
        return null;
    }
    private void doDisplayImage(Context context, String imageUrl, ImageView imageView,
                                DisplayImageConfig config, ResourceListener listener, int width, int height, Transformation glideTransform) {
        if (context == null || imageUrl == null || imageView == null) {
            LogUtils.d(TAG, "doDisplayImage，传入参数有误");
            return;
        }
        try {
            DrawableTypeRequest<String> request = Glide.with(context).load(imageUrl);
            dealWithDisplayOption(context, request, config, glideTransform);
            //dealWithResourceListener(request, listener);
            dealWithSize(request, width, height);
            dealWithView(request, imageView);
        } catch (Exception e) {
            LogUtils.e(TAG, e.getMessage());
        }
    }

    private void doDisplayImage(Context context, File file, ImageView imageView,
                                DisplayImageConfig config, ResourceListener listener, int width, int height, Transformation glideTransform) {
        if (context == null || file == null || imageView == null) {
            LogUtils.d(TAG, "doDisplayImage，传入参数有误");
            return;
        }
        try {
            DrawableTypeRequest<File> request = Glide.with(context).load(file);
            dealWithDisplayOption(context, request, config, glideTransform);
            //dealWithResourceListener(request, listener);
            dealWithSize(request, width, height);
            dealWithView(request, imageView);
        } catch (Exception e) {
            LogUtils.e(TAG, e.getMessage());
        }
    }

    private <T> void dealWithView(DrawableTypeRequest<T> request, ImageView imageView) {
        if (request == null || imageView == null) {
            return;
        }
        request.into(imageView);
    }

    private void dealWithSize(DrawableTypeRequest request, int widht, int height) {
        if (request == null) {
            return;
        }
        if (widht > 0 && height > 0) {
            request.override(widht, height);
        } else if (widht == IImageApiProvider.ORIGINAL_SIZE || height == IImageApiProvider.ORIGINAL_SIZE) {
            try {
                request.override(widht, height);
            } catch (Exception e) {
                LogUtils.e(TAG, e.getMessage());
            }
        }
    }

    private void dealWithDisplayOption(Context context, @NonNull DrawableRequestBuilder request, DisplayImageConfig config, Transformation transformation) {
        if (request == null) {
            return;
        }
        if (config == null) {
            config = ImageLoaderManager.getDefaultDisPlayImageConfig();
        }

        if (config.getDrawableOnLoading() != null) {
            request.placeholder(config.getDrawableOnLoading());
        } else {
            request.placeholder(config.getImageResOnLoading());
        }
        if (config.getDrawableOnFail() != null) {
            request.placeholder(config.getImageResOnFail());
        } else {
            request.error(config.getImageResOnFail());
        }
        //使用.placeholder()方法在某些情况下会导致图片显示的时候出现图片变形的情况。
        //这是因为Glide默认开启的crossFade动画导致的TransitionDrawable绘制异常
        request.dontAnimate();
        switch (config.getPriority()) {
            case IMMEDIATE:
            case HIGH:
                request.priority(Priority.HIGH);
                break;
            case NORMAL:
                request.priority(Priority.NORMAL);
                break;
            case LOW:
            default:
                request.priority(Priority.LOW);
                break;
        }
        if (config.isNeedThumbnail()) {
            request.thumbnail(config.getThumbnail());
        }
        request.skipMemoryCache(!config.isCacheOnMemory());
        if (!config.isCacheOnDisk()) {
            request.diskCacheStrategy(DiskCacheStrategy.NONE);
        } else {
            request.diskCacheStrategy(DiskCacheStrategy.SOURCE);
        }
    }

    private void doLoadImage(Context context, String imageUrl, final ResourceListener resourceListener, int width, int height) {
        if (context == null) {
            return;
        }
        if (width <= 0 || height <= 0) {
            height = width = Target.SIZE_ORIGINAL;
        }
        try {
            DrawableTypeRequest<String> request = Glide.with(context).load(imageUrl);
            final WeakReference<ResourceListener> resourceListenerWeakReference = new WeakReference<>(resourceListener);
            request.asBitmap().listener(new RequestListener<String, Bitmap>() {
                @Override
                public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                    if (resourceListenerWeakReference != null && resourceListenerWeakReference.get() != null) {
                        resourceListenerWeakReference.get().onException(e);
                        return true;
                    }
                    return false;
                }

                @Override
                public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    if (resourceListenerWeakReference != null && resourceListenerWeakReference.get() != null) {
                        resourceListenerWeakReference.get().onResourceReady(resource);
                        return true;
                    }
                    return false;
                }
            }).into(width, height);
        } catch (Exception e) {
            LogUtils.e(TAG, e.getMessage());
        }

    }


}

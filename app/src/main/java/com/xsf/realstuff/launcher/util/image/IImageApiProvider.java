package com.xsf.realstuff.launcher.util.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.File;

/**
 * Author: xushangfei
 * Time: created at 2017/11/16.
 * Description: 对外提供的的Image
 */

public interface IImageApiProvider {
    String TAG = "ImageUtil";
    int ORIGINAL_SIZE = Integer.MIN_VALUE;

    /**
     * 展示图片
     */
    void displayImage(Context context, String imageUrl, ImageView imageView);

    /**
     * 展示指定尺寸
     */
    void displayImage(Context context, String imageUrl, ImageView imageView, int width, int height);

    /**
     * 根据配置展示图片
     */
    void displayImage(Context context, String imageUrl, ImageView imageView, DisplayImageConfig config);

    /**
     * 根据配置展示指定大小图片
     */
    void displayImage(Context context, String imageUrl, ImageView imageView, DisplayImageConfig config, int width, int height);

    /**
     * 展示指定File图片
     */
    void displayImage(Context context, File file, ImageView imageView);

    /**
     * 根据配置展示指定File图片
     */
    void displayImage(Context context, File file, ImageView imageView, DisplayImageConfig config);

    void displayImage(Context context, File file, ImageView imageView, DisplayImageConfig config, int width, int height);

    /**
     * 展示指定Android Res资源图片
     */
    void displayImage(Context context, int ResId, ImageView imageView);

    /**
     * 根据配置展示指定Android Res资源图片
     */
    void displayImage(Context context, int ResId, ImageView imageView, DisplayImageConfig config);

    /**
     * 展示图片，并且监听图片加载回调
     */
    void displayImage(Context context, String imageUrl, ImageView imageView, ResourceListener listener);

    /**
     * 根据配置展示图片，并且监听图片加载回调
     */
    void displayImage(Context context, String imageUrl, ImageView imageView, DisplayImageConfig config, ResourceListener listener);

    /**
     * 展示高斯模糊图片
     *
     * @param radius 高斯模糊半径(像素),不包含中心点的像素,取值范围[1, 50]
     * @param sigma  高斯模糊标准差 默认3
     */
    void displayBlurImage(Context context, String imageUrl, ImageView imageView, int radius, int sigma);

    void displayBlurImage(Context context, String imageUrl, ImageView imageView, int radius);

    /**
     * 展示圆形图片
     * 圆形的半径为图片的Math.min(width, height)/2
     */
    void displayCircleImage(Context context, String imageUrl, ImageView imageView);

    void displayCircleImage(Context context, String imageUrl, ImageView imageView, DisplayImageConfig config);

    /**
     * 下载图片,原图尺寸
     */
    void loadImage(Context context, String imageUrl, ResourceListener resourceListener);

    /**
     * 下载指定大小的图片
     */
    void loadImage(Context context, String imageUrl, ResourceListener resourceListener, int width, int height);

    /**
     * 清理内存缓存
     */
    void clearMemoryCache(Context context);

    /**
     * 从缓存中（内存、磁盘）获取图片
     */
    Bitmap getBitmapFromCache(String url);

    interface ResourceListener {
        void onResourceReady(Bitmap resouce);

        void onException(Exception e);
    }
}

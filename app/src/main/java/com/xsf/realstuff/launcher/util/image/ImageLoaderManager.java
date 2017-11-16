package com.xsf.realstuff.launcher.util.image;

import android.support.annotation.NonNull;

import com.xsf.realstuff.launcher.util.image.impl.ImageApiProvideImpl;

/**
 * Author: xushangfei
 * Time: created at 2017/11/16.
 * Description: 封装底层图片加载
 */

public class ImageLoaderManager {
    private static IImageApiProvider sImageLoader = new ImageApiProvideImpl();     //默认的ImageLoader实现，Glide

    private static DisplayImageConfig sDefaultDisPlayImageConfig = new DisplayImageConfig.Builder().build();

    private static GlobalImageConfig sGlobalImageConfig = new GlobalImageConfig.Builder().build();

    public static IImageApiProvider getImageLoader() {
        return sImageLoader;
    }

    public static @NonNull
    GlobalImageConfig getGlobalImageConfig() {
        return sGlobalImageConfig;
    }

    public static @NonNull DisplayImageConfig getDefaultDisPlayImageConfig() {
        return sDefaultDisPlayImageConfig;
    }

    /**
     * 修改默认的ImageLoader实现类
     * @param imageLoader
     */
    public static void setImageLoader(@NonNull IImageApiProvider imageLoader) {
        sImageLoader = imageLoader;
    }

    /**
     * 修改默认的每次图片加载配置项
     * @param sDefaultDisPlayImageConfig
     */
    public static void setDefaultDisPlayImageConfig(@NonNull DisplayImageConfig sDefaultDisPlayImageConfig) {
        ImageLoaderManager.sDefaultDisPlayImageConfig = sDefaultDisPlayImageConfig;
    }

    /**
     * 修改默认的全局配置项
     * @param sGlobalImageConfig
     */
    public static void setGlobalImageConfig(@NonNull GlobalImageConfig sGlobalImageConfig) {
        ImageLoaderManager.sGlobalImageConfig = sGlobalImageConfig;
    }

}

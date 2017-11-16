package com.xsf.realstuff.launcher.util.image;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 全局的图片加载配置
 * Created by hzchenboning on 17/10/9.
 */

public class GlobalImageConfig {
    //--------- 以下是接口及常量 -------------
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({HIGH_IMAGE_QUALITY, NORMAL_IMAGE_QUALITY, LOW_IMAGE_QUALITY})
    private @interface ImageQualityMode {}

    public static final int HIGH_IMAGE_QUALITY = 100;
    public static final int NORMAL_IMAGE_QUALITY = 80;
    public static final int LOW_IMAGE_QUALITY = 50;

    //磁盘缓存文件 250MB
    private static final String DEFAULT_DISK_CACHE_DIR = "image_manager_disk_cache";
    private static final int DEFAULT_DISK_CACHE_SIZE = 50 * 1024 * 1024;

    private static final int DEFAULT_DISK_BITMAP_POOL_SIZE = 5 * 1024 * 1024;//bitmap pool大小
    //--------- 以上是接口及常量 -------------


    public static boolean NEED_ADJUST_IMAGE_QUALITY = false;
    private static int sImageQuality = HIGH_IMAGE_QUALITY;

    private final boolean useExternalDiskCacheDir;
    private final String cacheFolderName;
    private final int diskCacheSize;
    private final int bitmapPoolSize;
    private final int memoryCacheSize;
    private final boolean resizeImageUrl;

    public GlobalImageConfig(GlobalImageConfig.Builder builder) {
        this.useExternalDiskCacheDir = builder.useExternalDiskCacheDir;
        this.cacheFolderName = builder.cacheFolderName;
        this.diskCacheSize = builder.diskCacheSize;
        this.bitmapPoolSize = builder.bitmapPoolSize;
        this.memoryCacheSize = builder.memoryCacheSize;
        this.resizeImageUrl = builder.resizeImageUrl;
    }

    public static int getImageQuality() {
        return sImageQuality;
    }

    public static void setImageQuality(@ImageQualityMode int quality) {
        sImageQuality = quality;
    }

    public boolean isUseExternalDiskCacheDir() {
        return useExternalDiskCacheDir;
    }

    public String getCacheFolderName() {
        return cacheFolderName;
    }

    public int getDiskCacheSize() {
        return diskCacheSize;
    }

    public int getBitmapPoolSize() {
        return bitmapPoolSize;
    }

    public int getMemoryCacheSize() {
        return memoryCacheSize;
    }

    public boolean isResizeImageUrl() {
        return resizeImageUrl;
    }

    public static class Builder {
        boolean useExternalDiskCacheDir = true;
        String cacheFolderName = DEFAULT_DISK_CACHE_DIR;
        int diskCacheSize = DEFAULT_DISK_CACHE_SIZE;
        int bitmapPoolSize = DEFAULT_DISK_BITMAP_POOL_SIZE;

        int memoryCacheSize = 0;
        boolean resizeImageUrl = true;

        public Builder setUseExternalDiskCacheDir(boolean useExternalDiskCacheDir) {
            this.useExternalDiskCacheDir = useExternalDiskCacheDir;
            return this;
        }

        public Builder setCacheFolderName(String cacheFolderName) {
            this.cacheFolderName = cacheFolderName;
            return this;
        }

        public Builder setDiskCacheSize(int diskCacheSize) {
            this.diskCacheSize = diskCacheSize;
            return this;
        }

        public Builder setMemoryCacheSize(int memoryCacheSize) {
            this.memoryCacheSize = memoryCacheSize;
            return this;
        }

        public Builder setResizeImageUrl(boolean resizeImageUrl) {
            this.resizeImageUrl = resizeImageUrl;
            return this;
        }

        public Builder setBitmapPoolSize(int bitmapPoolSize) {
            this.bitmapPoolSize = bitmapPoolSize;
            return this;
        }

        public GlobalImageConfig build() {
            return new GlobalImageConfig(this);
        }
    }


}

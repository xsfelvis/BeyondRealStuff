package com.xsf.studytest.spi;

import android.util.Log;

/**
 * Author: xushangfei
 * Time: created at 2017/12/5.
 * Description:
 */

public class Dog implements IAnimal {
    public static final String TAG = "SPI";

    @Override
    public void run() {
        Log.d(TAG, "dog run!");
    }
}

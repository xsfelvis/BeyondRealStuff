package com.xsf.moudlerealstuff.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Author: xushangfei
 * Time: created at 2017/11/7.
 * Description:
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface DbName {
}

package com.xsf.studytest.util;

import android.content.Context;
import android.content.Intent;

import com.xsf.studytest.zhuhiadv.ZhihuAdvActivity;

/**
 * Author: xushangfei
 * Time: created at 2017/12/14.
 * Description:
 */

public class JumpUtil {
    /**
     * 加薪计划首页
     */
    public static void gotoZhihuAdv(Context context) {
        Intent intent = new Intent(context, ZhihuAdvActivity.class);
        context.startActivity(intent);
    }

}

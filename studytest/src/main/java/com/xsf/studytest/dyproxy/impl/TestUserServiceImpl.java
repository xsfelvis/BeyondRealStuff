package com.xsf.studytest.dyproxy.impl;

import com.xsf.studytest.dyproxy.ITestUserService;

/**
 * Author: xushangfei
 * Time: created at 2017/11/22.
 * Description:
 */

public class TestUserServiceImpl implements ITestUserService {
    @Override
    public void saveUser() {
        System.out.println("2:保存用户信息");
    }
}

package com.xsf.studytest.dyproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Author: xushangfei
 * Time: created at 2017/11/22.
 * Description:
 */

public class dyProxy implements InvocationHandler {
    private Object mTarget = null;

    /**
     * @param obj 真实业务对象
     * @return
     */
    public Object getProxy(Object obj) {
        this.mTarget = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //通过反射调用真实业务对象的方法
        Object result = null;
        System.out.println("打开数据库");
        result = method.invoke(mTarget, args);
        System.out.println("关闭数据库");
        return result;
    }
}

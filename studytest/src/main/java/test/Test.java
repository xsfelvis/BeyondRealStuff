package test;

import android.text.TextUtils;

import com.xsf.studytest.dyproxy.ITestUserService;
import com.xsf.studytest.dyproxy.dyProxy;
import com.xsf.studytest.dyproxy.impl.TestUserServiceImpl;

/**
 * Author: xushangfei
 * Time: created at 2017/11/22.
 * Description:
 */

public class Test {
    private static final String TAG = null;
    public static void main(String[] args) {

        ITestUserService mTestUserService = (ITestUserService) new dyProxy().getProxy(new TestUserServiceImpl());
        mTestUserService.saveUser();
        if(TextUtils.isEmpty(TAG)) {

        } else {

        }

    }
}

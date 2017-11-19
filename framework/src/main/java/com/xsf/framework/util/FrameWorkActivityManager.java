package com.xsf.framework.util;

import android.app.Activity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

/**
 * Author: xushangfei
 * Time: created at 2017/11/19.
 * Description:
 */

public class FrameWorkActivityManager {
    private Stack<Activity> mActivityStack = new Stack<Activity>();
    private Map<Activity, String> mActivityTagMap = new HashMap<Activity, String>();

    private static class InstanceHolder {
        private static final FrameWorkActivityManager instance = new FrameWorkActivityManager();
    }

    public static FrameWorkActivityManager getInstance() {
        return InstanceHolder.instance;
    }


    public void removeActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
            mActivityTagMap.remove(activity);
            activity = null;
        }
    }


    public void addActivity(Activity activity) {
        if (activity == null) {

            return;
        }

        mActivityStack.push(activity);
    }

    public Activity getCurrentActivity() {
        if (mActivityStack != null && mActivityStack.size() > 0) {
            return mActivityStack.peek();
        }
        return null;
    }

    public boolean setActivityTag(Activity activity, String activityTag){
        if(mActivityStack.contains(activity)){
            mActivityTagMap.put(activity, activityTag);
            return true;
        }

        return false;
    }

    public Activity getActivityByTag(String tag){
        Iterator<Map.Entry<Activity, String>> mapIt = mActivityTagMap.entrySet().iterator();
        while(mapIt.hasNext()){
            Map.Entry<Activity, String> it = mapIt.next();
            if(it.getValue().equals(tag)){
                return it.getKey();
            }
        }
        return null;
    }


    public void finishAllActivity() {

        Activity finishActivity = null;
        while (mActivityStack.size() > 0) {
            finishActivity = mActivityStack.pop();
            if (finishActivity != null) {
                finishActivity.finish();
                finishActivity = null;
            }
        }
        mActivityTagMap.clear();
    }
}

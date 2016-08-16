package com.tc.dekequan.base;

import android.app.Activity;

import java.util.Stack;


public class ActivityStackManager {

    private static ActivityStackManager instance;
    /**
     * activity栈
     */
    private Stack<Activity> activityStack;

    private ActivityStackManager() {
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static ActivityStackManager getInstance() {
        if (instance == null) {
            instance = new ActivityStackManager();
        }
        return instance;
    }

    /**
     * 把一个activity压入栈中
     *
     * @param activity
     */
    public void pushOneActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取栈顶的activity，先进后出原则
     *
     * @return
     */
    public Activity currentActivity() {
        Activity activity = null;
        if ((null != activityStack) && !activityStack.empty()) {
            activity = activityStack.lastElement();
        }
        return activity;
    }

    public Activity firstActivity() {
        Activity activity = null;
        if ((null != activityStack) && !activityStack.empty()) {
            activity = activityStack.firstElement();
        }
        return activity;
    }

    /**
     * 移除一个activity
     *
     * @param activity
     */
    public void popOneActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        if ((activityStack == null) || activityStack.isEmpty())
            return;

        activity.finish();
        activityStack.remove(activity);
    }

    /**
     * 退出栈中除当前外的所有Activity
     *
     * @param cls
     */
    public void popAllActivityExceptCurrent(Class cls) {
        Activity activity = null;
        while (true) {
            activity = firstActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {
                break;
            }
            popOneActivity(activity);
        }
    }

    /**
     * 退出应用程序
     */
    public void exitApplication() {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            popOneActivity(activity);
        }
    }

    /**
     * 获取应用 activity的数量
     *
     * @return
     */
    public int getStackSize() {
        if (activityStack != null) {
            return activityStack.size();
        }
        return 0;
    }
}

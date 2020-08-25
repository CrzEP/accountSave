package com.dlg.as.util;

import android.app.Activity;

import java.util.Stack;

/**
 * author: crzep
 * create time: 2020/7/15
 * description: activity管理类
 * Version: 1.0
 **/
public class ActivityContainer {

    private ActivityContainer(){
    }

    private static ActivityContainer INSTANCE ;
    private static Stack<Activity> activityStack = new Stack<>();

    public static ActivityContainer getInstance(){
        if(INSTANCE==null){
            synchronized (ActivityContainer.class){
                INSTANCE=new ActivityContainer();
            }
        }
        return INSTANCE;
    }

    /**
     * 添加activity
     * @param activity activity实例
     */
    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity getCurrentActivity(){
        return activityStack.lastElement();
    }

    /**
     * 移除act
     * @param activity 移除act实例
     */
    public void removeActivity(Activity activity) {
        activityStack.remove(activity);
    }

    /**
     * 结束所有的Activity
     */
    public void finishAllActivity(){
        for (int i = 0 , size = activityStack.size(); i < size;i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void exitApp(){
        this.finishAllActivity();
        System.exit(0);
    }

}

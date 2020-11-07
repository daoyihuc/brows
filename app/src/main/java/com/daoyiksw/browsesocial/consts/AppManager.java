package com.daoyiksw.browsesocial.consts;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import com.daoyiksw.browsesocial.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * AUTHOR : daoyi
 * TODO : Activity管理类
 * DATE : 2020/3/12
 * VERSION : 1.0
 */
public class AppManager {

    private static Stack<BaseActivity> activityStack;
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * 单实例 , UI无需考虑多线程同步问题
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到栈
     */
    public void addActivity(BaseActivity activity) {

        if (activityStack == null) {
            activityStack = new Stack<BaseActivity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（栈顶Activity）
     */
    public BaseActivity currentActivity() {
        if (activityStack == null || activityStack.isEmpty()) {
            return null;
        }
        BaseActivity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 获取当前Activity的数量
     */
    public int getActivitySize() {

        if (activityStack == null || activityStack.isEmpty()) {
            return 0;
        }
        return activityStack.size();
    }

    /**
     * 获取当前Activity（栈顶Activity） 没有找到则返回null
     */
    public BaseActivity findActivity(Class<?> cls) {
        BaseActivity activity = null;
        for (BaseActivity aty : activityStack) {
            if (aty.getClass().equals(cls)) {
                activity = aty;
                break;
            }
        }
        return activity;
    }

    /**
     * 获取指定Activity列表
     */
    public List<BaseActivity> findActivityList(Class<?> cls){
        List<BaseActivity> list=new ArrayList<>();
        for(BaseActivity aty:activityStack){
            if(aty.getClass().equals(cls)){
                list.add(aty);
            }
        }
        return list;
    }

    /**
     * 判斷當前activity是否在堆棧倒數第二個
     */
    public boolean isLastActivity(Class<?> cls) {
        int position = activityStack.size() - 2;
        if (position < 0) {
            return false;
        }
        if (cls == activityStack.get(position).getClass()) {
            return true;
        }
        return false;
    }

    /**
     * 结束当前Activity（栈顶Activity）
     */
    public void finishActivity() {
        BaseActivity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity(重载)
     */
    public void finishActivity(Activity activity) {

        if (activity != null) {
            try {
                activityStack.remove(activity);
                activity.finish();
                activity = null;
            } catch (Exception e) {
            }
        }
    }

    /**
     * 结束指定的Activity(重载)
     */
    public void finishActivity(Class<?> cls) {
        for (BaseActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束除主页面外的所有Activity(重载)
     */
    public void finishActivityWhenMain() {
        for (BaseActivity activity : activityStack) {
            if (!activity.getClass().equals(MainActivity.class)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束指定页面外的所有Activity(重载)
     */
    public void finishActivityWhenclass(Class<?> cls) {
        for (BaseActivity activity : activityStack) {
            if (!activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    public void clearTop(Class<?> cls) {
        int i = 0;
        for (; i < activityStack.size(); i++) {
            if (cls.equals(activityStack.get(i).getClass())) {
                break;
            }
        }

        int startPosition = i + 1, endPosition = activityStack.size();

        if (startPosition < activityStack.size() && endPosition >= startPosition) {
            List<BaseActivity> list = activityStack.subList(startPosition, endPosition);
            if (null != list && !list.isEmpty()) {
                for (BaseActivity acts : list) {
                    acts.finish();
                }
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 应用程序退出
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            System.exit(0);
        }
    }
}

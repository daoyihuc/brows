package com.daoyiksw.browsesocial.consts;

import android.content.Context;

/**
 * AUTHOR : 谢明峰
 * TODO : 全局类
 * DATE : 2020/3/12
 * VERSION : 1.0
 */
public class GlobalData {

    public static Context mContext;

    public static Context getContext() {

        if (mContext != null)
            return mContext;
        return AppManager.getAppManager().currentActivity();
    }
}

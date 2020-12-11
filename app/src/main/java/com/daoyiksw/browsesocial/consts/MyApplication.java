package com.daoyiksw.browsesocial.consts;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.daoyiksw.browsesocial.untils.MacUtils;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/10/20
 * @details: ''
 * @mails: '1966287146@qq.com'
 */
public class MyApplication extends Application {


    public static MyApplication mContext;
    public static  MyApplication instance() {
        return mContext;
    }
    public MyApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // daoyi
        MacUtils.init(this);
        // tencent bugly
        CrashReport.initCrashReport(getApplicationContext(), "f58fe9ece8", false);

        GlobalData.mContext=this;

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(base);//分包框架的初始化
    }
}

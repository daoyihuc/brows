package com.daoyiksw.browsesocial.untils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * @author 道义（daoyi）
 * @version 1.0
 * @date 2020-06-23
 * @params
 **/
public class StatusBarUtils_d {

    // 全局保存状态栏颜色
    private static int statusBarColor = Color.BLACK;
    // 全局保存状态栏高度
    private static int statusBarHeight = 0;
    // 全局保存自己绘制的状态栏View
    private static View statusView;

    /**
     * 使状态栏透明并且兼容有虚拟按键的手机
     */
    public static void setStatusBarTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        // 实现功能
        final Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        statusBarColor = window.getStatusBarColor();
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    /**
     * 为布局文件中新增的状态栏布局设置背景色和高度
     */
    public static void setStatusViewAttr(View view, Activity activity) {
        if (view == null || activity == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = StatusBarUtils_d.getStatusBarHeight(activity);
        view.setLayoutParams(layoutParams);

        view.setBackgroundColor(StatusBarUtils_d.getStatusBarColor(activity));
    }

    /**
     * 绘制一个和状态栏一样高的View，并添加到decorView中
     */
    public static void createStatusView(Activity activity) {
        if (activity == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        // 绘制一个和状态栏一样高的View
        statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                StatusBarUtils_d.getStatusBarHeight(activity));
        statusView.setLayoutParams(params);
        // 设置背景色
        statusView.setBackgroundColor(StatusBarUtils_d.getStatusBarColor(activity));
        // 添加 statusView 到布局中
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        decorView.addView(statusView);
    }

    /**
     * 是否显示沉浸式效果
     * @param activity 上下文
     * @param hide true 沉浸式 false 非沉浸式
     */
    public static void hideStatusView(Activity activity, boolean hide) {
        if (statusView == null) {
            return;
        }
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        View firstView = decorView.getChildAt(0);
        FrameLayout.LayoutParams firstParams = (FrameLayout.LayoutParams) firstView.getLayoutParams();
        if (hide) {
            firstParams.topMargin = 0;
            statusView.setVisibility(View.GONE);
        } else {
            firstParams.topMargin = StatusBarUtils_d.getStatusBarHeight(activity);
            statusView.setVisibility(View.VISIBLE);
        }
        firstView.setLayoutParams(firstParams);
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        if (context == null || context.getResources() == null) {
            return 0;
        }
        if (statusBarHeight != 0) {
            return statusBarHeight;
        }
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId == 0) {
            return 0;
        }
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    /**
     * 获取状态栏颜色
     */
    public static int getStatusBarColor(Activity activity) {
        if (activity == null || activity.getWindow() == null) {
            return Color.BLACK;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return Color.BLACK;
        }
        if (statusBarColor == Color.BLACK) {
            return activity.getWindow().getStatusBarColor();
        }
        if (statusBarColor == Color.TRANSPARENT) {
            return Color.BLACK;
        }
        return statusBarColor;
    }

}

package com.daoyiksw.browsesocial.untils;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * @author 道义（daoyi）
 * @version 1.0
 * @date 2020-03-27
 * @params
 **/
public class TimeUtils {

    private String color;//这里可以修改文字颜色
    WeakReference<TextView> tvCodeWr;//控件软引用，防止内存泄漏
    WeakReference<Button> button;//控件软引用，防止内存泄漏
    private CountDownTimer timer;


    public TimeUtils(TextView tvCode, String color) {
        super();
        this.tvCodeWr = new WeakReference(tvCode);
        this.color = color;
    }
    public TimeUtils(Button button, String color) {
        super();
        this.button = new WeakReference(button);
        this.color = color;
    }
    //这是倒计时执行方法
    public void RunTimer() {
        timer = new CountDownTimer(60 * 1000 - 1, 1000) {
            @Override
            public void onFinish() {
                if (tvCodeWr != null) {
                    tvCodeWr.get().setText("重新获取");
                    tvCodeWr.get().setTextColor(Color.parseColor(color));
                    tvCodeWr.get().setClickable(true);
                    tvCodeWr.get().setEnabled(true);

                }else if(button != null){
                    button.get().setText("重新获取");
                    button.get().setTextColor(Color.parseColor("#ffffff"));
                    button.get().setClickable(true);
                    button.get().setEnabled(true);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        button.get().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
                    }else{
                        GradientDrawable gradientDrawable=new GradientDrawable();
                        gradientDrawable.setColor(Color.parseColor(color));
                        gradientDrawable.setCornerRadius(24);
                        button.get().setBackground(gradientDrawable);
                    }
                }

                cancel();
            }

            @Override
            public void onTick(long millisUntilFinished) {
                if (tvCodeWr != null) {
                    tvCodeWr.get().setClickable(false);
                    tvCodeWr.get().setEnabled(false);
                    tvCodeWr.get().setText("重新获取"+millisUntilFinished / 1000 + "s");
                    tvCodeWr.get().setTextColor(Color.parseColor("#999999"));
                }else if(button != null){
                    button.get().setClickable(false);
                    button.get().setEnabled(false);
                    button.get().setText("重新获取"+millisUntilFinished / 1000 + "s");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        button.get().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#EEEEEE")));
                    }else{
                        GradientDrawable gradientDrawable=new GradientDrawable();
                        gradientDrawable.setColor(Color.parseColor("#EEEEEE"));
                        gradientDrawable.setCornerRadius(24);
                        button.get().setBackground(gradientDrawable);
                    }
                }
            }
        }.start();
    }
    //这个方法可以在activity或者fragment销毁的时候调用，防止内存泄漏
    public void cancle() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}

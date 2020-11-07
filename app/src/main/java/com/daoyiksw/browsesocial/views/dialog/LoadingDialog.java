package com.daoyiksw.browsesocial.views.dialog;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.speech.tts.Voice;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.daoyiksw.browsesocial.R;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.daoyiksw.browsesocial.untils.MacUtils.dpto;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/10/20
 * @details: ''
 * @mails: '1966287146@qq.com'
 */
public class LoadingDialog extends Dialog {

    // 获取window窗口
    private Window window;
    //设置弹窗比例
    private float windowsize = 0.8f;
    //上下文对象
    private Context context;
    // lottie 动画加载
    private LottieAnimationView imageview;
    // 加载提示语
    private TextView textView;
    // 提示文字
    private  String loadingText;
    //
    private String[] loadingTexts;
    // 动画
    ValueAnimator animator;

    {
        loadingTexts =new String[]{"加载中","加载中.","加载中..","加载中..."};
    }

    public LoadingDialog(@NonNull Context context) {
        super(context);
        this.context=context;
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context=context;
    }

    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        initData();
        init();
    }

    // 数据初始化
    public void initData(){
        this.loadingText=getContext().getResources().getString(R.string.loading_text);
    }

    // 初始化
    @SuppressLint("WrongConstant")
    private void init(){
        imageview =  findViewById(R.id.loading);
        textView = findViewById(R.id.loading_text);
        // 提示語是否爲空
        if(this.loadingText!=null&&!this.loadingText.equals("")){
            textView.setText(this.loadingText);
        }

        animator = ValueAnimator.ofInt(0, loadingTexts.length);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if((int)animation.getAnimatedValue()<loadingTexts.length){
                    textView.setText(loadingTexts[(int) animation.getAnimatedValue()]);
                }

            }
        });
        animator.setRepeatCount(Animation.INFINITE);
        animator.setRepeatMode(Animation.RESTART);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(1500);
        animator.start();

    }
    // 停止文字动画
    public void stopAnimation(){
        animator.cancel();
    }


    // 文字修改
    public void setLoadingText(String value){

        Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(value);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        textView.setText(s);
                    }
                });
    }

    @Override
    public void show() {
        super.show();

        //获取弹窗位置
        window = getWindow();
        setCanceledOnTouchOutside(false);//不允许外部点击消失
        setCancelable(false);
        window.setWindowAnimations(R.style.loading_animation);
        window.setGravity(Gravity.CENTER);
        window.getDecorView().setPadding(0, 0, 0,this.dpto(20));
        WindowManager.LayoutParams layoutParams = window.getAttributes();//获取窗口属性
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        layoutParams.width = (int) (displayMetrics.widthPixels * windowsize);
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.gravity = Gravity.CENTER;
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(dpto(10));
        window.setBackgroundDrawable(gradientDrawable);

        window.setAttributes(layoutParams);
    }

    public int dpto(int dp) {
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
    }
}

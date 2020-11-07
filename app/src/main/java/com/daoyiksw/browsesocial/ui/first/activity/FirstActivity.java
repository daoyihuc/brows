package com.daoyiksw.browsesocial.ui.first.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.consts.BaseActivity;
import com.daoyiksw.browsesocial.ui.login.activity.LoginActivity;
import com.daoyiksw.browsesocial.ui.login.activity.LoginIndexActivity;
import com.daoyiksw.browsesocial.untils.MacUtils;
import com.daoyiksw.browsesocial.views.dialog.LoadingDialog;

import java.util.TimerTask;

public class FirstActivity extends BaseActivity implements View.OnClickListener {



    // 初始化弹窗
    private LoadingDialog loadingDialog;
    // button
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MacUtils.initWindow(this);
        setContentView(R.layout.activity_first);
        initUI();
    }

    @Override
    protected void initUI() {
        button=findViewById(R.id.go);
        button.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected <T> T Https() {
        return null;
    }

    public void test(){
        showDialog("加载中");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    change();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    //timer
    public void change(){

        dissMiss();
    }


    @Override
    public void onClick(View view) {
        LoginIndexActivity.start(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}

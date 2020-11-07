package com.daoyiksw.browsesocial.ui.login.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.daoyiksw.browsesocial.MainActivity;
import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.consts.BaseActivity;
import com.daoyiksw.browsesocial.untils.MacUtils;
import com.daoyiksw.browsesocial.views.compant.Titlabar;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/6
 * @details: ''
 * @mails: '1966287146@qq.com'
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private Titlabar titlabar;
    private TextView forget;// 忘记密码

    private Button login;

    public static void start(Activity context){
        Intent intent=new Intent();
        intent.setClass(context,LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MacUtils.initWindow(this,0xffffffff,false,null,true);
        setContentView(R.layout.activity_login);
        initUI();
    }

    @Override
    protected void initUI() {

        // 导航栏
        titlabar=findViewById(R.id.titleBar);
        titlabar.setCenterTitle("手机号登录");
        titlabar.setCenterColor(0xff000000);
        titlabar.setLeftMargin(MacUtils.dpto(10),0,0,0);
        titlabar.setLeftDrawable(R.drawable.ic_baseline_arrow_back_ios_24,0x90000000);
        titlabar.addviews();
        titlabar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        // 忘记密码
        forget=findViewById(R.id.forget);
        forget.setOnClickListener(this);
        // 登录
        login=findViewById(R.id.login);
        login.setOnClickListener(this);


    }

    @Override
    protected void initData() {

    }

    @Override
    protected <T> T Https() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forget:
                ForgetActivity.start(this);
                break;
            case R.id.login:
                MainActivity.start(this);
                break;
        }
    }
}

package com.daoyiksw.browsesocial.ui.login.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.consts.BaseActivity;
import com.daoyiksw.browsesocial.untils.MacUtils;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/6
 * @details: ''
 * @mails: '1966287146@qq.com'
 */
public class LoginIndexActivity extends BaseActivity implements View.OnClickListener {


    private TextView register;
    private Button phoneButton,wxButton;

    public static void start(Activity context){

        Intent intent=new Intent();
        intent.setClass(context,LoginIndexActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MacUtils.initWindow(this);
        setContentView(R.layout.activity_login_index);
        initUI();
    }


    @Override
    protected void initUI() {
        register=findViewById(R.id.register);
        phoneButton=findViewById(R.id.phoneButton);
        wxButton=findViewById(R.id.wxButton);

        register.setOnClickListener(this);
        phoneButton.setOnClickListener(this);
        wxButton.setOnClickListener(this);
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

            case R.id.phoneButton: // 手机登录
                LoginActivity.start(this);
                break;
            case R.id.wxButton: // 微信登录
                MacUtils.ToastShow(this,"wx_login");
                break;
            case R.id.register: // s手机号注册

                RegisteActivity.start(this);
                break;

        }
    }
}

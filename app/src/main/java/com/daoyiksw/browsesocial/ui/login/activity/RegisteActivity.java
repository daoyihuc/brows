package com.daoyiksw.browsesocial.ui.login.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.consts.BaseActivity;
import com.daoyiksw.browsesocial.untils.MacUtils;
import com.daoyiksw.browsesocial.untils.TimeUtils;
import com.daoyiksw.browsesocial.views.compant.PhoneCode;
import com.daoyiksw.browsesocial.views.compant.Titlabar;

import java.util.HashMap;
import java.util.Map;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/6
 * @details: ''
 * @mails: '1966287146@qq.com'
 */
public class RegisteActivity extends BaseActivity implements View.OnClickListener {


    private Titlabar titlabar;
    private PhoneCode phoneCode;
    private Button code; // 验证码

    // 第三方类

    private TimeUtils timeUtils;


    public static void start(Activity context){

        Intent intent=new Intent();
        intent.setClass(context,RegisteActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MacUtils.initWindow(this,0xffffffff,false,null,true);
        setContentView(R.layout.activity_registe);
        initUI();

    }

    @Override
    protected void initUI() {
        titlabar=findViewById(R.id.titleBar);
        titlabar.setCenterTitle("注册");
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
        phoneCode=findViewById(R.id.phonecode);
        setPhoneCode();
        code=findViewById(R.id.code);
        timeUtils=new TimeUtils(code,"#8C54F5");
        code.setOnClickListener(this);

    }

    @Override
    protected void initData() {


    }

    @Override
    protected <T> T Https() {
        return null;
    }
    //设置验证码
    private  void setPhoneCode(){
        phoneCode.setColor_focus(0xff8C54F5);
        phoneCode.setOnInputListener(new PhoneCode.OnInputListener() {
            @Override
            public void onSucess(String code) {
                if(code.length()>=4){

                }
            }

            @Override
            public void onInput() {

            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.code:
                timeUtils.RunTimer();
                break;
        }
    }
}

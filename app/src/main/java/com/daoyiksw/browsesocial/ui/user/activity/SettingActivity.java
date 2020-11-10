package com.daoyiksw.browsesocial.ui.user.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.consts.BaseActivity;
import com.daoyiksw.browsesocial.untils.MacUtils;
import com.daoyiksw.browsesocial.views.compant.Titlabar;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/10
 * @details: ''
 * @mails: '1966287146@qq.com'
 */
public class SettingActivity extends BaseActivity {


    private Titlabar titlabar;

    public static void star(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity,SettingActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MacUtils.initWindow(this,0xffffffff,false,null,true);
        setContentView(R.layout.activity_setting);
        initUI();
    }


    @Override
    protected void initUI() {
        titlabar=findViewById(R.id.titleBar);
        // 导航栏
        titlabar.setCenterTitle("设置");
        titlabar.setCenterColor(0xff000000);
        titlabar.setLeftMargin(MacUtils.dpto(15),0,0,0);
        titlabar.setLeftDrawable(R.drawable.ic_baseline_arrow_back_ios_24,0x90000000);
        titlabar.setBackGroundColor(0xffffffff);
        titlabar.addviews();
        titlabar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected <T> T Https() {
        return null;
    }
}

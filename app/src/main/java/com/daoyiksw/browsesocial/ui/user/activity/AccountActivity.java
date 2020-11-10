package com.daoyiksw.browsesocial.ui.user.activity;

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
import com.daoyiksw.browsesocial.views.compant.Titlabar;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/10
 * @details: '我的账户'
 * @mails: '1966287146@qq.com'
 */
public class AccountActivity extends BaseActivity implements View.OnClickListener {

    private Titlabar titlabar;

    private Button recordButton;
    private TextView giftButton;

    public static void start(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity,AccountActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MacUtils.initWindow(this,0xffffffff,false,null,true);
        setContentView(R.layout.activity_account);
        initUI();
    }

    @Override
    protected void initUI() {
        // 导航栏
        titlabar=findViewById(R.id.titleBar);
        titlabar.setCenterTitle("我的账户");
        titlabar.setCenterColor(0xff000000);
        titlabar.setLeftMargin(MacUtils.dpto(15),0,0,0);
        titlabar.setLeftDrawable(R.drawable.ic_baseline_arrow_back_ios_24,0x90000000);
        titlabar.addviews();
        titlabar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        // 账户记录
        recordButton=findViewById(R.id.recordButton);
        recordButton.setOnClickListener(this);

        // 礼物收益记录
        giftButton=findViewById(R.id.giftButton);
        giftButton.setOnClickListener(this);
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
            case R.id.recordButton:
                AccountRecordsActivity.start(this);
                break;
            case R.id.giftButton:
                GiftIncomeActivity.start(this);
                break;
        }
    }
}

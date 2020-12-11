package com.daoyiksw.browsesocial.ui.user.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.consts.BaseTitleActivity;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/13
 * @details: ''
 * @mails: '1966287146@qq.com'
 */
public class WithDrawActivity extends BaseTitleActivity {

    private View view;

    public static void start(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity,WithDrawActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view=getLayoutInflater().inflate(R.layout.activity_withdraw,relativeLayout,true);
    }

    @Override
    protected void initUI() {
        super.initUI();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected <T> T Https() {
        return super.Https();
    }

    @Override
    protected void setTitlabar() {
        super.setTitlabar();
        titlabar.setCenterTitle("提现");
    }
}

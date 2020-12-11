package com.daoyiksw.browsesocial.ui.user.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.consts.BaseActivity;
import com.daoyiksw.browsesocial.consts.BaseTitleActivity;
import com.daoyiksw.browsesocial.untils.MacUtils;
import com.daoyiksw.browsesocial.views.compant.Titlabar;
import com.jaeger.library.StatusBarUtil;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/11
 * @details: ''
 * @mails: '1966287146@qq.com'
 */
public class InviteActivity extends BaseActivity {

    private Titlabar titlabar;
    private CoordinatorLayout box;

    public static void start(Activity activity){

        Intent intent=new Intent();
        intent.setClass(activity,InviteActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_invite);
        initUI();

    }

    @Override
    protected void initUI() {
        // 导航栏
        titlabar=findViewById(R.id.titleBar);
        titlabar.setCenterTitle("礼物收益");
        titlabar.setCenterColor(0xffffffff);
        titlabar.setLeftMargin(MacUtils.dpto(15),0,0,0);
        titlabar.setLeftDrawable(R.drawable.ic_baseline_arrow_back_ios_24,0xffffffff);
        titlabar.addviews();
        titlabar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        box=findViewById(R.id.box);
        changeStatus();
    }
    //状态栏修改
    public void changeStatus(){
        StatusBarUtil.setTransparentForImageView(this,box);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected <T> T Https() {
        return null;
    }
}

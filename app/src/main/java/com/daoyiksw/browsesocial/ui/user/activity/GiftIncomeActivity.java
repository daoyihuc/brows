package com.daoyiksw.browsesocial.ui.user.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.consts.BaseActivity;
import com.daoyiksw.browsesocial.pub.holder.WrapContentLinearLayoutManager;
import com.daoyiksw.browsesocial.ui.user.adapter.AccountRecodesAdpter;
import com.daoyiksw.browsesocial.ui.user.bean.AccountRecodesBean;
import com.daoyiksw.browsesocial.untils.MacUtils;
import com.daoyiksw.browsesocial.views.compant.Titlabar;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/10
 * @details: ''
 * @mails: '1966287146@qq.com'
 */
public class GiftIncomeActivity extends BaseActivity {


    private Titlabar titlabar;
    private CoordinatorLayout box;
    private RecyclerView itemRecycler;

    private AccountRecodesAdpter accountRecodesAdpter;// 适配器

    private List<AccountRecodesBean> list;

    public static void start(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity,GiftIncomeActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_income);
        initData();
        initUI();
    }
    {
        list=new ArrayList<>();
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
        setItemRecycler();


    }
    //状态栏修改
    public void changeStatus(){
        StatusBarUtil.setTransparentForImageView(this,box);
    }

    private void setItemRecycler(){
        itemRecycler=findViewById(R.id.itemRecycler);
        WrapContentLinearLayoutManager linearLayoutManager=new WrapContentLinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        itemRecycler.setLayoutManager(linearLayoutManager);
        AccountRecodesAdpter accountRecodesAdpter=new AccountRecodesAdpter(this,list);
        itemRecycler.setAdapter(accountRecodesAdpter);

    }

    @Override
    protected void initData() {
        for(int i=0;i<3;i++){
            AccountRecodesBean accountRecodesBean=new AccountRecodesBean();
            accountRecodesBean.setName("礼物");
            accountRecodesBean.setTime("2020-09-01");
            accountRecodesBean.setCount("10");
            list.add(accountRecodesBean);
        }
    }

    @Override
    protected <T> T Https() {
        return null;
    }
}

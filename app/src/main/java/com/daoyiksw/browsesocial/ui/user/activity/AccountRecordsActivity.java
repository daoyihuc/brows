package com.daoyiksw.browsesocial.ui.user.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.consts.BaseActivity;
import com.daoyiksw.browsesocial.pub.holder.RecycleViewDivider;
import com.daoyiksw.browsesocial.pub.holder.WrapContentLinearLayoutManager;
import com.daoyiksw.browsesocial.ui.user.adapter.AccountRecodesAdpter;
import com.daoyiksw.browsesocial.ui.user.bean.AccountRecodesBean;
import com.daoyiksw.browsesocial.untils.MacUtils;
import com.daoyiksw.browsesocial.views.compant.Titlabar;

import java.util.ArrayList;
import java.util.List;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/10
 * @details: '账户记录'
 * @mails: '1966287146@qq.com'
 */
public class AccountRecordsActivity extends BaseActivity {

    private Titlabar titlabar;

    private RecyclerView ItemRecycler; // recycler 记录
    private AccountRecodesAdpter accountRecodesAdpter;// 适配器

    private List<AccountRecodesBean> list;

    {
        list=new ArrayList<>();
    }

    public static void start(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity,AccountRecordsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MacUtils.initWindow(this,0xffffffff,false,null,true);
        setContentView(R.layout.activity_account_records);
        initData();
        initUI();
    }

    @Override
    protected void initUI() {
        // 导航栏
        titlabar=findViewById(R.id.titleBar);
        titlabar.setCenterTitle("账户记录");
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
        //recycler
        setItemRecycler();


    }
    // recycler
    private void setItemRecycler(){
        ItemRecycler=findViewById(R.id.itemRecycler);
        WrapContentLinearLayoutManager linearLayoutManager=new WrapContentLinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ItemRecycler.setLayoutManager(linearLayoutManager);
        accountRecodesAdpter=new AccountRecodesAdpter(this,list);
        ItemRecycler.setAdapter(accountRecodesAdpter);
        ItemRecycler.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.HORIZONTAL,1,0xffeeeeee));

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

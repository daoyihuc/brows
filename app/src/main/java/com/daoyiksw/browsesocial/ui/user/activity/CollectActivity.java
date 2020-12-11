package com.daoyiksw.browsesocial.ui.user.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.consts.BaseTitleActivity;
import com.daoyiksw.browsesocial.pub.holder.SpacesItemDecoration;
import com.daoyiksw.browsesocial.pub.holder.WrapContentLinearLayoutManager;
import com.daoyiksw.browsesocial.ui.index.bean.NewBean;
import com.daoyiksw.browsesocial.ui.user.adapter.CollectAdapter;
import com.daoyiksw.browsesocial.untils.MacUtils;
import com.daoyiksw.browsesocial.views.compant.SwipeItemLayout;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/11
 * @details: ''
 * @mails: '1966287146@qq.com'
 */
public class CollectActivity extends BaseTitleActivity {


    private View view;
    private RecyclerView itemRecyclerView;
    private CollectAdapter collectAdapter;

    private List<NewBean> list;

    {
        list=new ArrayList<>();
    }
    public static void start(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity,CollectActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initUI() {
        super.initUI();
        view = getLayoutInflater().inflate(R.layout.activity_collect,relativeLayout,true);
        setItemRecyclerView();

    }

    @Override
    protected void initData() {
        super.initData();
        // 新闻资讯
        for(int i=1;i<4;i++){
            NewBean newBean=new NewBean();
            newBean.setLable("今日头条");
            newBean.setName("全国新型冠状病毒肺炎疫情最新情况");
            newBean.setImage(R.mipmap.tupian);
            newBean.setReadcount("320");
            newBean.setUsername("daoyi");
            newBean.setUserImg(R.mipmap.homebanner);
            list.add(newBean);
        }
    }
    private void setItemRecyclerView(){
        itemRecyclerView=view.findViewById(R.id.itemRecycler);
        WrapContentLinearLayoutManager linearLayoutManager=new WrapContentLinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        itemRecyclerView.setLayoutManager(linearLayoutManager);
        collectAdapter=new CollectAdapter(this,list);
        itemRecyclerView.setAdapter(collectAdapter);
        itemRecyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));
        itemRecyclerView.addItemDecoration(new SpacesItemDecoration(MacUtils.dpto(8)));

    }


    @Override
    protected <T> T Https() {
        return super.Https();
    }

    @Override
    protected void setTitlabar() {
        super.setTitlabar();
        titlabar.setCenterTitle("我的收藏夹");
    }
}

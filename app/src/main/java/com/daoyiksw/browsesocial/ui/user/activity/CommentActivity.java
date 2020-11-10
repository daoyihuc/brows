package com.daoyiksw.browsesocial.ui.user.activity;

import android.app.Activity;
import android.app.WallpaperColors;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.consts.BaseActivity;
import com.daoyiksw.browsesocial.pub.holder.WrapContentLinearLayoutManager;
import com.daoyiksw.browsesocial.ui.user.adapter.CommentAdapter;
import com.daoyiksw.browsesocial.ui.user.bean.CommentBean;
import com.daoyiksw.browsesocial.untils.MacUtils;
import com.daoyiksw.browsesocial.views.compant.Titlabar;

import java.util.ArrayList;
import java.util.List;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/10
 * @details: ''
 * @mails: '1966287146@qq.com'
 */
public class CommentActivity extends BaseActivity {


    private Titlabar titlabar; // 导航
    private RecyclerView CommentRecycler;// 评论
    private CommentAdapter commentAdapter;


    private List<CommentBean> list;// 数据

    public static void star(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity,CommentActivity.class);
        activity.startActivity(intent);
    }

    {
        list=new ArrayList<>();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MacUtils.initWindow(this,0xffffffff,false,null,true);
        setContentView(R.layout.activity_comment);
        initData();
        initUI();

    }

    @Override
    protected void initUI() {

        titlabar=findViewById(R.id.titleBar);

        // 导航栏
        titlabar.setCenterTitle("我的评论");
        titlabar.setCenterColor(0xff000000);
        titlabar.setLeftMargin(MacUtils.dpto(15),0,0,0);
        titlabar.setBackGroundColor(0xffffffff);
        titlabar.setLeftDrawable(R.drawable.ic_baseline_arrow_back_ios_24,0x90000000);
        titlabar.setBackGroundColor(0xffffffff);
        titlabar.addviews();
        titlabar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        CommentRecycler=findViewById(R.id.itemRecycler);
        setCommentRecycler();



    }

    @Override
    protected void initData() {

        for(int i=0;i<3;i++){
            CommentBean commentBean=new CommentBean();
            commentBean.setName("daoyi");
            commentBean.setTime("2020-09-24");
            commentBean.setUserUrl(R.mipmap.tupian);
            commentBean.setCommentUrl(R.mipmap.homebanner);
            commentBean.setDetails("真棒");
            commentBean.setCommentTitel("如果有一天，你成为了一个设计师，你会如 何去...");
            list.add(commentBean);
        }
    }

    private void setCommentRecycler(){
        WrapContentLinearLayoutManager linearLayoutManager=new WrapContentLinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        CommentRecycler.setLayoutManager(linearLayoutManager);
        commentAdapter=new CommentAdapter(this,list);
        CommentRecycler.setAdapter(commentAdapter);

    }


    @Override
    protected <T> T Https() {
        return null;
    }
}

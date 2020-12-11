package com.daoyiksw.browsesocial.ui.user.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daoyiksw.browsesocial.consts.BaseActivity;
import com.daoyiksw.browsesocial.consts.BaseTitleActivity;
import com.daoyiksw.browsesocial.pub.holder.HtmlImageGetter;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/11
 * @details: ''
 * @mails: '1966287146@qq.com'
 */
public class AboutActivity extends BaseTitleActivity {

    private TextView textView;
    private String s="";

    public static void start(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity,AboutActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initUI() {
        super.initUI();
        s="<p style='color:red;'>Hello word</p>";
        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(-1,-2);
        textView=new TextView(this);
        textView.setLayoutParams(layoutParams);
        relativeLayout.addView(textView);
        textView.setText(Html.fromHtml(s,new HtmlImageGetter(this,textView), null));
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
        titlabar.setCenterTitle("关于我们");
    }

}

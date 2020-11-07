package com.daoyiksw.browsesocial.views.compant;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daoyiksw.browsesocial.untils.MacUtils;


/**
 * @author 道翼(yanwen)
 * @description:
 * @date : 2019/9/20 0020 16:50
 */
public class Titlabar extends RelativeLayout implements View.OnClickListener{

    private TextView Right_Textview,Center_Textview,Left_TextView;
    private Context mContext;

    private LayoutParams Right_Textview_params,Left_TextView_params;
    private int match_parent= ViewGroup.LayoutParams.MATCH_PARENT;
    private int wrape_parent= ViewGroup.LayoutParams.WRAP_CONTENT;



    public Titlabar(Context context) {
        super(context);
        mContext=context;
        init();
    }

    public Titlabar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        init();
    }

    public Titlabar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        init();
    }

    //初始化控件
    public void init(){
        Right_Textview=new TextView(mContext);
        Left_TextView=new TextView(mContext);
        Center_Textview=new TextView(mContext);

        //设置组件宽度
        Right_Textview_params=new LayoutParams(wrape_parent,wrape_parent);
        Right_Textview_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        Right_Textview_params.addRule(RelativeLayout.CENTER_VERTICAL);
        Left_TextView_params=new LayoutParams(wrape_parent,wrape_parent);
        Left_TextView_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        Left_TextView_params.addRule(RelativeLayout.CENTER_VERTICAL);
        LayoutParams Center_Textview_params=new LayoutParams(MacUtils.dpto(120),wrape_parent);
        Center_Textview_params.addRule(RelativeLayout.CENTER_IN_PARENT);

        Right_Textview.setLayoutParams(Right_Textview_params);
        Left_TextView.setLayoutParams(Left_TextView_params);
        Center_Textview.setLayoutParams(Center_Textview_params);
        Center_Textview.setEllipsize(TextUtils.TruncateAt.END);
        Center_Textview.setMaxLines(1);
        Center_Textview.setGravity(Gravity.CENTER);


    }
    //加载试图
    public void addviews(){
        this.addView(Right_Textview);
        this.addView(Center_Textview);
        this.addView(Left_TextView);
    }

    //文字设置、
    public void setRightTitle(String text){
        Right_Textview.setText(text);
    }
    public void setLeftTitle(String text){
        Left_TextView.setText(text);
    }
    public void setCenterTitle(String text){
        Center_Textview.setText(text);
    }

    //颜色设置
    public void setRightColor(int color){
        Right_Textview.setTextColor(color);
    }
    public void setLeftColor(int color){
        Left_TextView.setTextColor(color);
    }
    public void setCenterColor(int color){
        Center_Textview.setTextColor(color);
    }

    //字体大小设置
    public void setRightFontSize(int size){
        Right_Textview.setTextSize(size);
    }
    public void setLeftFontSize(int size){
        Left_TextView.setTextSize(size);
    }
    public void setCenterFontSize(int size){
        Center_Textview.setTextSize(size);
    }


    public void setRightFontStyle(int type){
        switch (type){
            case 1:
                Right_Textview.setTypeface(Typeface.DEFAULT_BOLD);
                break;
            case 2:
                Right_Textview.setTypeface(Typeface.SANS_SERIF);
                break;
        }

    }
    public void setLeftFontStyle(int type){
        switch (type){
            case 1:
                Left_TextView.setTypeface(Typeface.DEFAULT_BOLD);
                break;
            case 2:
                Left_TextView.setTypeface(Typeface.SANS_SERIF);
                break;
        }

    }
    public void setCenterFontStyle(int type){
        switch (type){
            case 1:
                Center_Textview.setTypeface(Typeface.DEFAULT_BOLD);
                break;
            case 2:
                Center_Textview.setTypeface(Typeface.SANS_SERIF);
                break;
        }

    }
    //设置背景颜色
    public void setBackGroundColor(int color){
        this.setBackgroundColor(color);
    }

    //设置内边剧
    public void setLeftPadding(int left,int top ,int right,int bottom){
        Left_TextView.setPadding(left,top,right,bottom);
    }
    //设置内边剧
    public void setRightPadding(int left,int top ,int right,int bottom){
        Right_Textview.setPadding(left,top,right,bottom);
    }


    //设置图标
    public void setRightDrawable(Drawable drawable){
        Right_Textview.setCompoundDrawables(drawable,null,null,null);
    }
    public void setRightDrawable(int res,int color){
        Drawable right_drawable = mContext.getResources().getDrawable(res);
        right_drawable.setBounds(0,0,right_drawable.getMinimumWidth(),right_drawable.getMinimumHeight());

        if(color!=0){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                right_drawable.setTint(color);
            }
        }
        Right_Textview.setCompoundDrawables(right_drawable,null,null,null);
    }

    public void setLeftDrawable(Drawable drawable){
        if(drawable!=null){
            drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
            Left_TextView.setCompoundDrawables(drawable,null,null,null);
        }

    }
    public void setLeftDrawable(int res,int color){
        Drawable right_drawable = mContext.getResources().getDrawable(res);
        right_drawable.setBounds(0,0,right_drawable.getMinimumWidth(),right_drawable.getMinimumHeight());
        if(color!=0){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                right_drawable.setTint(color);
            }
        }
        Left_TextView.setCompoundDrawables(right_drawable,null,null,null);

    }
    public void setLeftMargin(int left, int top, int right, int bottom) {

        Left_TextView_params.setMargins(left, top, right, bottom);
        Left_TextView.setLayoutParams(Left_TextView_params);
    }

    //监听设置
    public void setRightOnClickListener(OnClickListener onClickListener){
        Right_Textview.setOnClickListener(onClickListener);
    }

    public void setLeftOnClickListener(OnClickListener onClickListener){
        Left_TextView.setOnClickListener(onClickListener);
    }

    @Override
    public void onClick(View v) {

    }
}

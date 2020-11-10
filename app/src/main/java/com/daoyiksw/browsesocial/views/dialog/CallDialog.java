package com.daoyiksw.browsesocial.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daoyiksw.browsesocial.R;

/**
 * 联系客服弹框
 */
public class CallDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private int layoutResID;//布局文件id
    private int[] listenedItems;//存放控件id的数组
    private RelativeLayout relative_sure, relative_cancle;
    private TextView text_phonenum,text_ok, text_cancel;
    private String phonenum;
    private String string_ok, string_cancel;

    public CallDialog(Context context) {
        super(context, R.style.dialogStyle);
        this.context = context;
    }
    public CallDialog(Context context, String phonenum){
        super(context, R.style.dialogStyle);
        this.context = context;
        this.phonenum=phonenum;
    }
    public CallDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }
    public CallDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
    public CallDialog(Context context, int layoutResID, int[] listenedItems, String phonenum) {
        super(context, R.style.dialogStyle);//这个决定dialog的样式
        this.context = context;
        this.layoutResID = layoutResID;
        this.listenedItems = listenedItems;
        this.phonenum = phonenum;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();//获取一个窗口
        window.setGravity(Gravity.CENTER);//设置窗口的位置
        //window.setWindowAnimations(R.style.bottom_menu_animation);//设置窗口进入时的动画特效
        WindowManager.LayoutParams layoutParams = window.getAttributes();//获取窗口的属性赋值给layoutParams
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);//设置窗口的属性
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.item_layout_call);
        text_phonenum = findViewById(R.id.text_phonenum);
        text_phonenum.setText(phonenum);
        relative_sure = findViewById(R.id.relative_sure);
        relative_sure.setOnClickListener(this);
        text_ok=findViewById(R.id.ok);
        text_ok.setText(string_ok);
        relative_cancle = findViewById(R.id.relative_cancle);
        relative_cancle.setOnClickListener(this);
        text_cancel =findViewById(R.id.text_cancel);


        if(string_cancel !=null && !"".equals(string_cancel))
            text_cancel.setText(string_cancel);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.relative_cancle:
                if(cancelListener !=null)
                    cancelListener.onClick(v);
                dismiss();
                break;
            case R.id.relative_sure:
                if(listener !=null)
                   listener.onClick(v);
                dismiss();
                break;
        }
    }
    @Override
    public void show(){
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);
    }
    //自定义一个监听的接口回调
    private OnClickListener listener, cancelListener;
    public interface OnClickListener{
        void onClick(View view);
    }
    public void setOnClickListener(OnClickListener listener){
        this.listener = listener;
    }
    public void setOnCancelClickListener(OnClickListener listener) {
        this.cancelListener =listener;
    }
    // 设置确定按钮文字
    public void setOk(String os){
        this.string_ok=os;
    }

    // 设置确定按钮和取消按钮文字
    public void setOkAndCancel(String okStr, String cancelStr){
        this.string_ok=okStr;
        this.string_cancel =cancelStr;
    }
    //设置提示信息
    public void setMessage(String text){
        this.phonenum=text;
    }
}
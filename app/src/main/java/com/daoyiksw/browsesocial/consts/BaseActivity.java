package com.daoyiksw.browsesocial.consts;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.daoyiksw.browsesocial.views.dialog.LoadingDialog;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/10/20
 * @details: 'Activity 基础类'
 * @mails: '1966287146@qq.com'
 */

public abstract  class BaseActivity extends AppCompatActivity {


    // 初始化弹窗
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addActivity();
        initDialog();
    }

    // 初始化弹窗
    protected void initDialog(){
        loadingDialog=new LoadingDialog(this);
    }
    // 弹窗显示
    protected void showDialog(String value){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadingDialog.setLoadingText(value);
                if(loadingDialog!=null&&!loadingDialog.isShowing()){
                    loadingDialog.show();
                }

            }
        });
    }
    // 关闭弹窗
    protected void dissMiss(){
     loadingDialog.dismiss();
    }

    // 弹窗修改
    protected void changeDialog(){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadingDialog.setLoadingText("加载完成");
                loadingDialog.stopAnimation();
            }
        });
    }

    // activity 堆栈管理

    protected void addActivity(){
        AppManager.getAppManager().addActivity(this);
    }

    // 绑定服务

    // 该app是否可以使用

    // 内存管理

    // 自定义方法
    protected  abstract void initUI(); //ui 初始化
    protected  abstract void initData(); // 数据初始化
    protected abstract  <T> T  Https(); // http方法


}

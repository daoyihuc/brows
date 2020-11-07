package com.daoyiksw.browsesocial.consts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.daoyiksw.browsesocial.views.dialog.LoadingDialog;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/10/20
 * @details: 'fragment 基础类'
 * @mails: '1966287146@qq.com'
 */
public abstract class BaseFragment extends Fragment {

    // 初始化弹窗
    private LoadingDialog loadingDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        initDialog();
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    // 初始化弹窗
    protected void initDialog(){
        loadingDialog=new LoadingDialog(getActivity());
    }
    // 弹窗显示
    protected void showDialog(String value){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadingDialog.setLoadingText(value);
                loadingDialog.show();
            }
        });
    }
    // 关闭弹窗
    protected void dissMiss(){
        loadingDialog.dismiss();
    }

    // 弹窗修改
    protected void changeDialog(){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadingDialog.setLoadingText("加载完成");
                loadingDialog.stopAnimation();
            }
        });
    }

    // 自定义方法
    protected  abstract void initUI(); //ui 初始化
    protected  abstract void initData(); // 数据初始化
    protected abstract  <T> T  Https(); // http方法



}

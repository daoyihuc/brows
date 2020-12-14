package com.daoyiksw.browsesocial.consts;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.daoyiksw.browsesocial.base.DemoHelper;
import com.daoyiksw.browsesocial.views.dialog.LoadingDialog;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.NetUtils;

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

        EMClient.getInstance().addConnectionListener(new MyConnectionListener());
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
    protected abstract  <T> T  Https(); //

    private class MyConnectionListener implements EMConnectionListener {
        @Override
        public void onConnected(){
//            login_HX("1","123456");
        }

        @Override
        public void onDisconnected(final int error) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if(error == EMError.USER_REMOVED){
                        // 显示帐号已经被移除
                    }else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                        // 显示帐号在其他设备登录
                        Toast.makeText(MyApplication.instance(),"帐号在其他设备登录",Toast.LENGTH_LONG).show();
//                        UserInfoHelper.clearUserLoginData(MyApplication.instance());
//                        EMClient.getInstance().logout(true);

//                        LoginActivity.start(MyApplication.instance());
//                        AppManager.getAppManager().finishAllActivity();
                    } else {
                        if (NetUtils.hasNetwork(MyApplication.instance())){
//                            if(UserInfoHelper.getUniqueId(MyApplication.instance())!=null
//                                    &&!UserInfoHelper.getUniqueId(MyApplication.instance()).equals("")
//                                    && UserInfoHelper.getUserPw(MyApplication.instance())!=null &&
//                                    !UserInfoHelper.getUserPw(MyApplication.instance()).equals("")){
//                                login_HX(UserInfoHelper.getUniqueId(MyApplication.instance()),
//                                        UserInfoHelper.getUserPw(MyApplication.instance()));
//                            }

                        }else{
                            //连接不到聊天服务
                            //当前网络不可用，请检查网络设置
//                            if(UserInfoHelper.getUniqueId(MyApplication.instance())!=null
//                                    &&!UserInfoHelper.getUniqueId(MyApplication.instance()).equals("")
//                                    && UserInfoHelper.getUserPw(MyApplication.instance())!=null &&
//                                    !UserInfoHelper.getUserPw(MyApplication.instance()).equals("")){
//                                login_HX(UserInfoHelper.getUniqueId(MyApplication.instance()),
//                                        UserInfoHelper.getUserPw(MyApplication.instance()));
//                            }

                        }

                    }
                }
            });
        }
    }
    //登录环形
    private void login_HX(String uniqueId,String pwd){
        EMClient.getInstance().login(uniqueId, pwd, new EMCallBack() {
            @Override
            public void onSuccess() {
//
//                String name = UserInfoHelper.getUserNickName(MyApplication.instance());
//                String avatar = UserInfoHelper.getUserAvatar(MyApplication.instance());
                // 将自己服务器返回的环信账号、昵称和头像URL设置到帮助类中。
                DemoHelper.getInstance().setCurrentUserName(uniqueId); // 环信Id
//                DemoHelper.getInstance().getUserProfileManager().updateCurrentUserNickName(name);
//                DemoHelper.getInstance().getUserProfileManager().setCurrentUserAvatar(avatar);
                Log.e("Login","开始跳转");
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();

            }

            @Override
            public void onError(int i, String s) {
                Log.e("Login",s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }


}

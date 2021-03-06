package com.daoyiksw.browsesocial;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.daoyiksw.browsesocial.consts.BaseActivity;
import com.daoyiksw.browsesocial.ui.index.fragment.ChatFragmentParent;
import com.daoyiksw.browsesocial.ui.index.fragment.HomeFragment;
import com.daoyiksw.browsesocial.ui.index.fragment.MyFragment;
import com.daoyiksw.browsesocial.untils.MacUtils;
import com.daoyiksw.browsesocial.views.EasyNavigationBars;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.next.easynavigation.view.EasyNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {


    private EasyNavigationBars easyNavigationBar;
    private String[] tabArr={"首页","聊天室","我的"};
    private int[] iconArr=null;
    private int[] iconArr1=null;
    private List<Fragment> list;
    private int color = 0xffffffff;

    {
        list=new ArrayList<>();
    }

    public static void start(Activity context){
        Intent intent=new Intent();
        intent.setClass(context,MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MacUtils.initWindow(this,0xffffffff,false,null,true);
//        MacUtils.clearStatus(MainActivity.this);

        setContentView(R.layout.activity_main);
        initData();
        initUI();
    }

    @Override
    protected void initUI() {
        easyNavigationBar = findViewById(R.id.EasyNavigation);
        easyNavigationBar.defaultSetting()  //恢复默认配置、可用于重绘导航栏
                .titleItems(tabArr)      //  Tab文字集合  只传文字则只显示文字
                .normalIconItems(iconArr)   //  Tab未选中图标集合
                .selectIconItems(iconArr1)   //  Tab选中图标集合
                .fragmentList(list)       //  fragment集合
                .fragmentManager(getSupportFragmentManager())
                .iconSize(35)     //Tab图标大小
                .tabTextSize(10)   //Tab文字大小
                .tabTextTop(2)     //Tab文字距Tab图标的距离
                .normalTextColor(Color.parseColor("#666666"))   //Tab未选中时字体颜色
                .selectTextColor(Color.parseColor("#333333"))   //Tab选中时字体颜色
                .scaleType(ImageView.ScaleType.CENTER_INSIDE)  //同 ImageView的ScaleType
                .navigationBackground(Color.parseColor("#FFFFFF"))   //导航栏背景色
                .setOnTabClickListener(new EasyNavigationBars.OnTabClickListener() {
                    @Override
                    public boolean onTabSelectEvent(View view, int position) {
                        //Tab点击事件  return true 页面不会切换
                        Log.e("daoyi",""+position);
                        switch (position){
                            case 0:
                                MacUtils.clearStatus(MainActivity.this);
                                ((HomeFragment)list.get(position)).changeStatus();
                                break;
                            case 1:
                                MacUtils.clearStatus(MainActivity.this);
                                ((ChatFragmentParent)list.get(position)).changeStatus();
                                color = 0x00000000;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        int childCount = easyNavigationBar.getNavigationLayout().getChildCount();
                                        LinearLayout navigationLayout = easyNavigationBar.getNavigationLayout();
                                        navigationLayout.setBackgroundColor(0x00000000);
                                        Log.e("导航栏子类",""+childCount);
                                    }
                                });


                                break;
                                case 2:
                                MacUtils.clearStatus(MainActivity.this);
                                ((MyFragment)list.get(position)).changeStatus();
                                break;

                        }
                        return false;
                    }
                    @Override
                    public boolean onTabReSelectEvent(View view, int position) {
                        //Tab重复点击事件
                        return false;
                    }
                })
                .smoothScroll(true)  //点击Tab  Viewpager切换是否有动画
                .canScroll(true)    //Viewpager能否左右滑动
                .mode(EasyNavigationBar.NavigationMode.MODE_NORMAL)   //默认MODE_NORMAL 普通模式  //MODE_ADD 带加号模式
                .navigationHeight(60)  //导航栏高度
//                .lineHeight(10)         //分割线高度  默认1px
//                .lineColor(Color.parseColor("#ff0000"))
                .hasPadding(true)    //true ViewPager布局在导航栏之上 false有重叠
                .centerNormalTextColor(Color.parseColor("#ff0000"))    //加号文字未选中时字体颜色
                .centerSelectTextColor(Color.parseColor("#00ff00"))
                .navigationBackground(color)
                .build();//加号文字选中时字体颜色
    }

    @Override
    protected void initData() {
        this.login_HX("1","123456");
        tabArr=new String[]{"首页","聊天室","我的"};
        iconArr=new int[]{R.mipmap.shouye,R.mipmap.liaotian,R.mipmap.wode};
        iconArr1=new int[]{R.mipmap.shouye1,R.mipmap.liaotian1,R.mipmap.wode1};
//        list.add();
        ChatFragmentParent chatFragmentParent = new ChatFragmentParent();
        list.add(new HomeFragment());
//        list.add(new MessageFragment());
        list.add(chatFragmentParent);
        list.add(new MyFragment());
    }

    @Override
    protected <T> T Https() {
        return null;
    }


    private void login_HX(String uniqueId,String pwd){
        EMClient.getInstance().login(uniqueId, pwd, new EMCallBack() {
            @Override
            public void onSuccess() {
//
//                String name = UserInfoHelper.getUserNickName(MyApplication.instance());
//                String avatar = UserInfoHelper.getUserAvatar(MyApplication.instance());
                // 将自己服务器返回的环信账号、昵称和头像URL设置到帮助类中。
//                DemoHelper.getInstance().setCurrentUserName(uniqueId); // 环信Id
//                DemoHelper.getInstance().getUserProfileManager().updateCurrentUserNickName(name);
//                DemoHelper.getInstance().getUserProfileManager().setCurrentUserAvatar(avatar);
                Log.e("Login","开始跳转");
//                EMClient.getInstance().groupManager().loadAllGroups();
//                EMClient.getInstance().chatManager().loadAllConversations();

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
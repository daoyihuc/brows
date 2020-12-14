package com.daoyiksw.browsesocial.ui.index.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.consts.BaseFragment;
import com.daoyiksw.browsesocial.pub.holder.RecycleViewDivider;
import com.daoyiksw.browsesocial.pub.holder.WrapContentLinearLayoutManager;
import com.daoyiksw.browsesocial.ui.index.adapter.MyMenuAdapter;
import com.daoyiksw.browsesocial.ui.index.bean.MyMenuBean;
import com.daoyiksw.browsesocial.ui.user.activity.AboutActivity;
import com.daoyiksw.browsesocial.ui.user.activity.AccountActivity;
import com.daoyiksw.browsesocial.ui.user.activity.CollectActivity;
import com.daoyiksw.browsesocial.ui.user.activity.CommentActivity;
import com.daoyiksw.browsesocial.ui.user.activity.FaceBackActivity;
import com.daoyiksw.browsesocial.ui.user.activity.InviteActivity;
import com.daoyiksw.browsesocial.ui.user.activity.MemberActivity;
import com.daoyiksw.browsesocial.ui.user.activity.SettingActivity;
import com.daoyiksw.browsesocial.ui.user.activity.UserInfoActivity;
import com.daoyiksw.browsesocial.untils.MacUtils;
import com.daoyiksw.browsesocial.untils.StatusBarUtils_d;
import com.daoyiksw.browsesocial.untils.SystemUtils;
import com.daoyiksw.browsesocial.views.compant.Titlabar;
import com.daoyiksw.browsesocial.views.dialog.CallDialog;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/9
 * @details: '我的'
 * @mails: '1966287146@qq.com'
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {


    private View view;
    private Titlabar titlabar;

    private CoordinatorLayout box;
    private ImageView UserImageView; // 头像
    private RecyclerView menuRecycler; // 菜单
    private TextView username; // 用户名
    private ImageView VipImageView; // 会员

    private List<MyMenuBean> list;
    private MyMenuAdapter myMenuAdapter;// 适配器

    private CallDialog callDialog;// 客服电话

    private Button openVIP; // 开通会员


    {
        list=new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_my,container,false);
        initData();
        initUI();
        return view;
    }

    @Override
    protected void initUI() {
        box=view.findViewById(R.id.box);//

        int statusBarHeight = StatusBarUtils_d.getStatusBarHeight(getActivity());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1,-1);
        layoutParams.setMargins(0,statusBarHeight,0,0);
        box.setLayoutParams(layoutParams);

        UserImageView=view.findViewById(R.id.useriamge);// 头像
        username=view.findViewById(R.id.username);// 用户民
        VipImageView=view.findViewById(R.id.vip_level); // vip等级
        menuRecycler=view.findViewById(R.id.menuList);// 列表功能

        UserImageView.setOnClickListener(this);


        // 导航栏
        titlabar=view.findViewById(R.id.titlebar);
        titlabar.setCenterTitle("个人中心");
        titlabar.setCenterColor(0xffffffff);
        titlabar.setLeftMargin(MacUtils.dpto(15),0,0,0);
//        titlabar.setLeftDrawable(R.drawable.ic_baseline_arrow_back_ios_24,0x90000000);
        titlabar.addviews();
        titlabar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onBackPressed();
            }
        });

        setMenuRecycler();

        callDialog=new CallDialog(getActivity(),"100100");
        callDialog.setOkAndCancel("呼叫","取消");
        callDialog.setOnClickListener(new CallDialog.OnClickListener() {
            @Override
            public void onClick(View view) {
                SystemUtils.callPhone(getActivity(),"100100");
            }
        });

        openVIP=view.findViewById(R.id.openVIP);
        openVIP.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        int[] icons={R.mipmap.zhanghu,R.mipmap.pinglin,R.mipmap.shoucang,R.mipmap.yijian,
                R.mipmap.yaoqing,R.mipmap.guanyu,R.mipmap.kefu,R.mipmap.shezhi};
        String[] name={"账户","我的评论","我的收藏","意见反馈","邀请有礼","关于我们","客服中心","设置"};
        String[] details={"看看谁送了礼物","","","吐吐槽","邀请新人","","联系客服解决问题",""};
        for(int i=0;i<icons.length;i++){
            MyMenuBean myMenuBean=new MyMenuBean();
            myMenuBean.setUrl(icons[i]);
            myMenuBean.setName(name[i]);
            myMenuBean.setDetails(details[i]);
            list.add(myMenuBean);
        }


    }
    private void  setMenuRecycler(){
        WrapContentLinearLayoutManager linearLayoutManager=new WrapContentLinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        menuRecycler.setLayoutManager(linearLayoutManager);
        myMenuAdapter=new MyMenuAdapter(getActivity(),list);
        menuRecycler.setAdapter(myMenuAdapter);
        menuRecycler.addItemDecoration(new RecycleViewDivider(getActivity(),LinearLayoutManager.HORIZONTAL,1,0xffeeeeee));

        myMenuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
               if(list.size()>0&&position<list.size()){
                   switch (position){
                       case 0: // 账户
                           AccountActivity.start(getActivity());
                           break;
                       case 1: // 我的评论
                           CommentActivity.star(getActivity());
                           break;
                       case 2: // 我的收藏
                           CollectActivity.start(getActivity());
                           break;
                       case 3: // 意见反馈
                           FaceBackActivity.start(getActivity());
                           break;
                       case 4:  // 邀请有礼
                           InviteActivity.start(getActivity());
                           break;
                       case 5:// 关于我们
                           AboutActivity.start(getActivity());
                           break;
                       case 6: // 客服中心
                           callDialog.show();
                           break;
                       case 7: // 设置
                           SettingActivity.star(getActivity());
                           break;
                   }
               }
            }
        });
    }
    //状态栏修改
    public void changeStatus(){
        StatusBarUtil.setTranslucentForImageViewInFragment(getActivity(),0,box);
    }


    @Override
    protected <T> T Https() {
        return null;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.useriamge:
                UserInfoActivity.star(getActivity());
                break;
            case R.id.openVIP: // 立即开通
                MemberActivity.start(getActivity());
                break;

        }
    }
}

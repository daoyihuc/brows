package com.daoyiksw.browsesocial.ui.user.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.consts.BaseTitleActivity;
import com.daoyiksw.browsesocial.pub.holder.MultiplePagerScaleInTransformer;
import com.daoyiksw.browsesocial.pub.holder.NpaGridLayoutManager;
import com.daoyiksw.browsesocial.pub.holder.SpacesItemDecoration;
import com.daoyiksw.browsesocial.ui.index.adapter.Home_Banner;
import com.daoyiksw.browsesocial.ui.index.bean.image_banner_home;
import com.daoyiksw.browsesocial.ui.user.adapter.MemberAdapter;
import com.daoyiksw.browsesocial.ui.user.bean.MemberBean;
import com.youth.banner.Banner;
import com.youth.banner.config.BannerConfig;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.transformer.DepthPageTransformer;
import com.youth.banner.util.BannerUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/13
 * @details: ''
 * @mails: '1966287146@qq.com'
 */
public class MemberActivity extends BaseTitleActivity {

    private View view; // root view
    private Banner banner; // banner

    private RecyclerView itemRecyclerView;// 会员权益列表
    private MemberAdapter memberAdapter;// 适配器

    private List<image_banner_home> list;// banner
    private List<MemberBean> list_member;// 会有权益列表

    public static void start(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity,MemberActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view=getLayoutInflater().inflate(R.layout.activity_member,relativeLayout,true);
        banner = view.findViewById(R.id.banners);
        itemRecyclerView=view.findViewById(R.id.itemRecycler);

        setBanner();
        setItemRecyclerView();
    }

    @Override
    protected void initUI() {
        super.initUI();


    }


    //轮播图设置
    private void setBanner() {
        Home_Banner home_banner = new Home_Banner(this, list);
        banner.setAdapter(home_banner);
        banner.setIndicator(new CircleIndicator(this));
        banner.setIndicatorGravity(IndicatorConfig.Direction.RIGHT);
        banner.setDelayTime(3000);
        banner.setPageTransformer(new DepthPageTransformer());
        banner.setPageTransformer(new MultiplePagerScaleInTransformer(1,0.4f));
        banner.setIndicatorMargins(new IndicatorConfig.Margins(0, 0, BannerConfig.INDICATOR_MARGIN, (int) BannerUtils.dp2px(12)));

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {

            }

            public void onBannerChanged(int position) {
            }
        });
        banner.addBannerLifecycleObserver(this);
    }
    // 会员权益列表
    private void setItemRecyclerView(){
        NpaGridLayoutManager npaGridLayoutManager=new NpaGridLayoutManager(this,3);
        itemRecyclerView.setLayoutManager(npaGridLayoutManager);
        memberAdapter=new MemberAdapter(this,list_member);
        itemRecyclerView.setAdapter(memberAdapter);
        itemRecyclerView.addItemDecoration(new SpacesItemDecoration(5));

    }


    @Override
    protected void initData() {
        super.initData();
        list=new ArrayList<>();

        int icons[]={R.mipmap.baiyin,R.mipmap.huangjinhuiyuan,R.mipmap.zuanshihuiyuan};
        for(int i=0;i<icons.length;i++){
            //banner
            image_banner_home im=new image_banner_home();
            im.setUrl(icons[i]);
            list.add(im);
        }

        int[] icons1={R.mipmap.danmu2,R.mipmap.texiao,R.mipmap.shengji,R.mipmap.anmgdan2,
                R.mipmap.huiyuan,R.mipmap.mingpian,R.mipmap.liwu,R.mipmap.xunzhang,
                R.mipmap.huany
        };
        String[] names={"全站弹幕","开通特效","加速升级","榜单隐身","会员弹幕","会员用户名片","会员礼物","会员勋章","进场欢迎"};
        String[] details={"赠送10个全站弹幕","弹幕显示开通特效","送礼物经验加速5%","做一个低调神秘的人","独一无二的悬停弹幕",
                            "会员身份以卡识人","会员专享特别礼物","钻石会员专属身份勋章","进场欢迎会员特效"
        };

        list_member=new ArrayList<>();

        for(int i=0;i<icons1.length;i++){
            MemberBean memberBean=new MemberBean();
            memberBean.setUrl(icons1[i]);
            memberBean.setName(names[i]);
            memberBean.setDetails(details[i]);
            list_member.add(memberBean);
        }


    }

    @Override
    protected <T> T Https() {
        return super.Https();
    }

    @Override
    protected void setTitlabar() {
        super.setTitlabar();
        titlabar.setCenterTitle("VIP会员");
    }
}

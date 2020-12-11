package com.daoyiksw.browsesocial.ui.index.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.consts.BaseFragment;
import com.daoyiksw.browsesocial.pub.holder.ItemOffsetDecoration;
import com.daoyiksw.browsesocial.pub.holder.MultiplePagerScaleInTransformer;
import com.daoyiksw.browsesocial.pub.holder.NpaGridLayoutManager;
import com.daoyiksw.browsesocial.pub.holder.RecycleViewDivider;
import com.daoyiksw.browsesocial.pub.holder.SpacesItemDecoration;
import com.daoyiksw.browsesocial.pub.holder.SpacesItemDecorations;
import com.daoyiksw.browsesocial.pub.holder.WrapContentLinearLayoutManager;
import com.daoyiksw.browsesocial.ui.index.adapter.Home_Banner;
import com.daoyiksw.browsesocial.ui.index.adapter.HotAdapter;
import com.daoyiksw.browsesocial.ui.index.adapter.MenuAdapter;
import com.daoyiksw.browsesocial.ui.index.adapter.NewAdapter;
import com.daoyiksw.browsesocial.ui.index.bean.HotBean;
import com.daoyiksw.browsesocial.ui.index.bean.MenuBean;
import com.daoyiksw.browsesocial.ui.index.bean.NewBean;
import com.daoyiksw.browsesocial.ui.index.bean.image_banner_home;
import com.daoyiksw.browsesocial.untils.MacUtils;
import com.daoyiksw.browsesocial.untils.StatusBarUtils_d;
import com.daoyiksw.browsesocial.views.compant.Titlabar;
import com.daoyiksw.browsesocial.webview.MyWebChromeClient;
import com.jaeger.library.StatusBarUtil;
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
 * @params: 2020/11/7
 * @details: '首页'
 * @mails: '1966287146@qq.com'
 */
public class HomeFragment extends BaseFragment {


    private Titlabar titlabar;
    private View view; // root view
    private Banner banner; // banner
    private CoordinatorLayout box;
    private RecyclerView menuRecycler,hotRecycler,newRecycler;// 菜单 今日热榜
    private MenuAdapter menuAdapter;// 适配器
    private HotAdapter hotAdapter; // 今日热榜适配器
    private NewAdapter newAdapter;// 新闻资讯适配器
    private WebView webView; // 网页展示


    private String url;
    private List<image_banner_home> list;// banner
    private List<MenuBean> list_menu;// 菜单
    private List<HotBean> list_hot;// 今日热榜
    private List<NewBean> list_new;// 新闻资讯


    {
        url = "https://www.baidu.com";
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.changeStatus();

        view=inflater.inflate(R.layout.fragment_home,null,false);

        initData();
        initUI();
        return view;
    }

    @Override
    protected void initUI() {
        banner = view.findViewById(R.id.banner);
        box= view.findViewById(R.id.box);
//        titlabar=view.findViewById(R.id.titlebar);
//        titlabar.setCenterTitle("10");
//        titlabar.addviews();
        setBanner();
        setMenuRecycler();
        setWebView();
        int statusBarHeight = StatusBarUtils_d.getStatusBarHeight(getActivity());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1,-1);
        layoutParams.setMargins(0,statusBarHeight,0,00);
        box.setLayoutParams(layoutParams);
    }

    @Override
    protected void initData() {

        list=new ArrayList<>();
        list_menu=new ArrayList<>();
        list_hot=new ArrayList<>();
        list_new=new ArrayList<>();

        //banner
        image_banner_home im=new image_banner_home();
        im.setUrl(R.mipmap.homebanner);
        list.add(im);

        // 菜单
        int[] icon={R.mipmap.taobao,R.mipmap.xiaoshuo,R.mipmap.duanzi,R.mipmap.xinwen,R.mipmap.tongcheng};
        String[] title={"淘宝","小说","段子","新闻","同城"};
        for(int i=0;i<title.length;i++){
            MenuBean menuBean=new MenuBean();
            menuBean.setUrl(icon[i]);
            menuBean.setName(title[i]);
            list_menu.add(menuBean);
        }

        // 今日热榜
        for(int i=1;i<4;i++){
            HotBean hotBean=new HotBean();
            hotBean.setCount(i);
            hotBean.setDetails("全国新型冠状病毒肺炎疫情最新情况");
            list_hot.add(hotBean);
        }
        // 新闻资讯
        for(int i=1;i<4;i++){
            NewBean newBean=new NewBean();
            newBean.setLable("今日头条");
            newBean.setName("全国新型冠状病毒肺炎疫情最新情况");
            newBean.setImage(R.mipmap.tupian);
            newBean.setReadcount("320");
            newBean.setUsername("daoyi");
            newBean.setUserImg(R.mipmap.homebanner);
            list_new.add(newBean);
        }


    }

    @Override
    protected <T> T Https() {
        return null;
    }
    //轮播图设置
    private void setBanner() {
        Home_Banner home_banner = new Home_Banner(getActivity(), list);
        banner.setAdapter(home_banner);
        banner.setIndicator(new CircleIndicator(getActivity()));
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
        banner.addBannerLifecycleObserver(getActivity());
    }
    // 菜单recycle
    private void setMenuRecycler(){
        menuRecycler=view.findViewById(R.id.menuRecycler);
        NpaGridLayoutManager linearLayoutManager=new NpaGridLayoutManager(getActivity(),5);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        menuRecycler.setLayoutManager(linearLayoutManager);
        menuRecycler.addItemDecoration(new ItemOffsetDecoration(15));
        menuAdapter=new MenuAdapter(getActivity(),list_menu);
        menuRecycler.setAdapter(menuAdapter);
    }
    // webview
    private void  setWebView(){
        webView = view.findViewById(R.id.webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        // 其他细节操作
        settings.setCacheMode(settings.LOAD_NO_CACHE); // 关闭webview中缓存
        settings.setAllowFileAccess(true); // 设置可以访问文件
        settings.setJavaScriptCanOpenWindowsAutomatically(true); // 支持通过JS打开新窗口
        settings.setLoadsImagesAutomatically(true); // 支持自动加载图片
        settings.setDefaultTextEncodingName("utf-8");// 设置编码格式
        webView.setWebChromeClient(new MyWebChromeClient());
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                return super.shouldOverrideUrlLoading(view, request);
                String urls = request.getUrl().toString();
                try {
                    if (urls.startsWith("http:") || urls.startsWith("https:")) {
                        view.loadUrl(urls);
                    } else {
//                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urls));
//                        startActivity(intent);
                    }
                    return true;
                } catch (Exception e){
                    return false;
                }
            }

            //页面加载结束时

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
            //界面开始加载时

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                dailog.show();

            }
            //正在加载时

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
//                dailog.show();

            }
        });
    }

    //状态栏修改
    public void changeStatus(){

//        StatusBarUtil.setColor(getActivity(),0xffffffff,0);
//        StatusBarUtils_d.createStatusView(getActivity());
//        MacUtils.setStatusText(getActivity(),true);
        StatusBarUtil.setTranslucentForImageView(getActivity(),0,box);
    }

}

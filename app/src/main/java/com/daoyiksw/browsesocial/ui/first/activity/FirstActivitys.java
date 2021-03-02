package com.daoyiksw.browsesocial.ui.first.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.daoyiksw.browsesocial.consts.BaseActivity;
import com.daoyiksw.browsesocial.ui.login.activity.LoginIndexActivity;
import com.daoyiksw.browsesocial.untils.MacUtils;
import com.daoyiksw.browsesocial.views.X5WebView;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class FirstActivitys extends BaseActivity implements View.OnClickListener {

    private String mCM;
    private ValueCallback<Uri> uploadFile;
    private ValueCallback<Uri[]> uploadFiles;
    private final static int FCR = 1;

    private static final int REQUEST_STORAGE = 1;
    private static final int REQUEST_LOCATION = 2;
    public ValueCallback<Uri> mUploadMessage;
    public static final int FILE_CHOOSER_RESULT_CODE = 5173;

    private X5WebView webView; // 网页展示

    String selectUrl = "";
    String url = "http://106.52.216.106:8084";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MacUtils.initWindow(this);
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(true);
        Observable<Permission> permissionObservable = rxPermissions.requestEach(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        );
        permissionObservable.subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {
                if (permission.granted) {
                    // 用户已经同意该权限
                    Log.d("daoyi", "text_sms granted");
                    //result.agree(permission);
                } else if (permission.shouldShowRequestPermissionRationale) {
                    // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                    //result.refuse(permission);
                    Log.d("daoyi", "text_sms shouldShowRequestPermissionRationale");
                } else {
                    // 用户拒绝了该权限，并且选中『不再询问』，提醒用户手动打开权限
                    //result.noMoreQuestions(permission);
                    Log.d("daoyi", "text_sms ");
                }
            }
        });
        initUI();
    }

    @Override

    protected void initUI() {

        setWebView();
        setContentView(webView);
    }

    // webview
    private void setWebView() {
        webView = new X5WebView(FirstActivitys.this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, -1);
        webView.setLayoutParams(params);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        // 其他细节操作
        settings.setCacheMode(settings.LOAD_NO_CACHE); // 关闭webview中缓存
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true); // 设置可以访问文件
        settings.setJavaScriptCanOpenWindowsAutomatically(true); // 支持通过JS打开新窗口
        settings.setLoadsImagesAutomatically(true); // 支持自动加载图片
        settings.setDefaultTextEncodingName("utf-8");// 设置编码格式
        webView.setWebChromeClient(new WebChromeClient() {

            // For Android 3.0+
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                uploadFile = uploadMsg;
                openFileChooseProcess();
            }

            // For Android < 3.0
            public void openFileChooser(ValueCallback<Uri> uploadMsgs) {
                uploadFile = uploadMsgs;
                openFileChooseProcess();
            }

            // For Android  > 4.1.1
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                uploadFile = uploadMsg;
                openFileChooseProcess();
            }

            // For Android  >= 5.0
            public boolean onShowFileChooser(WebView webView,
                                             ValueCallback<Uri[]> filePathCallback,
                                             FileChooserParams fileChooserParams) {
                uploadFiles = filePathCallback;
                openFileChooseProcess();
                return true;
            }
    });
//        webView.loadUrl("about:blank");
        //适应屏幕
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.loadUrl(url);
        webView.addJavascriptInterface(new daoyi(),"android");
        webView.setWebViewClient(new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String urls = "";
//                view.loadUrl("about:blank");
//                return super.shouldOverrideUrlLoading(view, request);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    urls = request.getUrl().toString();
                } else {
                    urls = String.valueOf(request.getUrl());
                }

                try {
                    view.loadUrl(urls);
                    if (urls.startsWith("http:") || urls.startsWith("https:")) {

                    } else {
//                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urls));
//                        startActivity(intent);
                    }
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }

            //页面加载结束时

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                selectUrl = url;
                Log.e("webView", "加载完成"+url);
            }
            //界面开始加载时

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                view.loadUrl("about:blank");
                super.onPageStarted(view, url, favicon);
//                dailog.show();

                Log.e("webView", "开始加载");
            }
            //正在加载时

            @Override
            public void onLoadResource(WebView view, String url) {

                super.onLoadResource(view, url);
//                dailog.show();

                Log.e("webView", "正在加载"+url);
            }
        });
    }
    class daoyi{
        @JavascriptInterface
        public void setName(String name){
            selectUrl = name;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected <T> T Https() {
        return null;
    }


    //timer
    public void change() {

        dissMiss();
    }


    @Override
    public void onClick(View view) {
        LoginIndexActivity.start(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        finish();
    }

    private void openFileChooseProcess() {
        Log.e("daoyi","开始选择文件");
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("*/*");
        startActivityForResult(Intent.createChooser(i, "test"), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    if (null != uploadFile) {
                        Uri result = data == null || resultCode != RESULT_OK ? null
                                : data.getData();
                        uploadFile.onReceiveValue(result);
                        uploadFile = null;
                    }
                    if (null != uploadFiles) {
                        Uri result = data == null || resultCode != RESULT_OK ? null
                                : data.getData();
                        uploadFiles.onReceiveValue(new Uri[]{result});
                        uploadFiles = null;
                    }
                    break;
                default:
                    break;
            }
        } else if (resultCode == RESULT_CANCELED) {
            if (null != uploadFile) {
                uploadFile.onReceiveValue(null);
                uploadFile = null;
            }
            if (null != uploadFiles) {
                uploadFiles.onReceiveValue(null);
                uploadFiles = null;
            }

        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        String pattern = ".*home.*";
        boolean isMatch = Pattern.matches(pattern, this.selectUrl);
        if(isMatch){
            super.onBackPressed();
            return;
        }
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }

    }
}


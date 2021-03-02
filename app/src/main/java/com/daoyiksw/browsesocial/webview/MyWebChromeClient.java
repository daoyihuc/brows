package com.daoyiksw.browsesocial.webview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.webkit.ValueCallback;

import com.daoyiksw.browsesocial.MainActivity;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author:"道翼(yanwen)"
 * @params:""
 * @data:2019/9/30
 * @email:1966287146@qq.com
 */
public class MyWebChromeClient extends WebChromeClient {


    private static final int REQUEST_STORAGE = 1;
    private static final int REQUEST_LOCATION = 2;
    public ValueCallback<Uri> mUploadMessage;
    public static final int FILECHOOSER_RESULTCODE = 5173;

    //获取网页进度条并显示
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);

    }
    //获取网页中的标题

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
    }

    //支持javascript警告框
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }


    //支持javascript确认框
    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        return super.onJsConfirm(view, url, message, result);
    }

    //支持javascript输入框

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }

//


}

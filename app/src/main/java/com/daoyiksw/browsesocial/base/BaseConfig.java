package com.daoyiksw.browsesocial.base;

import com.shanyue88.shanyueshenghuo.BuildConfig;

/**
 * @author 道义（daoyi）
 * @version 1.0
 * @date 2020-03-12
 * @params  公共配置类
 **/
public class BaseConfig {

    public static boolean isDebug = BuildConfig.DEBUG;     //DEBUG模式

    public final static String WXCHAT_APPID ="wx8a75530d0e5871e6";                      //微信开放平台appId
    public final static String WXCHAT_SECRET ="4a51b4f5f5a314b10a0ffb9120e73b38";       //微信开放平台secret

    public final static String EASE_APPKEY ="1120200316107492#shanyue";                 //微信开放平台appId
    public final static String EASE_TENANTID ="4a51b4f5f5a314b10a0ffb9120e73b38";       //微信开放平台secret

    public final static String URL_USE_AGREEMENT  ="https://admin.shanyue88.com/agreement?type=use_agreement";
    public final static String URL_PRIVACY_AGREEMENT  ="https://admin.shanyue88.com/agreement?type=privacy_agreement";
    public final static String URL_SHARE  ="file:///android_asset/html/share.html";
    public final static String URL_SHARE_USER  ="file:///android_asset/html/shared_user.html";

    public final static String URL_PUBLISHINGRULES  ="https://admin.shanyue88.com/publishingrules";//交易规则
}

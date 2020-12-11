package com.daoyiksw.browsesocial.untils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


/**
 * @author 道义（daoyi）
 * @version 1.0
 * @date 2020-03-18
 * @params
 **/
public class AppSPUtils {
    private final static String SP_APP="shanyue";
    private Context mcon;
    private final static int MODE_SPEC = android.os.Build.VERSION.SDK_INT <= 10 ? 0 : Context.MODE_MULTI_PROCESS;
    public static String getValueFromPrefrences(Context context,String key, String defaultValue) {
        if(context !=null) {
            SharedPreferences sharedPreferences =context.getSharedPreferences(key, Activity.MODE_PRIVATE);
            String strings = sharedPreferences.getString(key, defaultValue);
            return strings;
        } else{
            return "";
        }
    }
    public static void setValueToPrefrences(Context context,String key, String value) {
        try {
            SharedPreferences preferences = context.getSharedPreferences(key,Activity.MODE_PRIVATE);
            if (null != preferences) {
                preferences.edit().putString(key, value).commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

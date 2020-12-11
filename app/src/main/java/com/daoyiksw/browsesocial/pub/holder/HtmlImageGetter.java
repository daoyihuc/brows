package com.daoyiksw.browsesocial.pub.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.daoyiksw.browsesocial.R;

import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.drawable.BitmapDrawable;
        import android.graphics.drawable.Drawable;
        import android.graphics.drawable.LevelListDrawable;
        import android.os.AsyncTask;
        import android.text.Html;
        import android.util.Log;
        import android.widget.TextView;
        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.io.InputStream;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.security.KeyManagementException;
        import java.security.NoSuchAlgorithmException;
        import java.security.SecureRandom;
        import java.security.cert.X509Certificate;

        import javax.net.ssl.HostnameVerifier;
        import javax.net.ssl.HttpsURLConnection;
        import javax.net.ssl.SSLContext;
        import javax.net.ssl.SSLSession;
        import javax.net.ssl.TrustManager;
        import javax.net.ssl.X509TrustManager;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/11
 * @details: '富文本解析'
 * @mails: '1966287146@qq.com'
 */

public class HtmlImageGetter implements Html.ImageGetter{

    private Context context;
    private final int widthPixels;
    private TextView textView;

    public HtmlImageGetter(Context context, TextView textView){
        this.context = context;
        this.textView = textView;
        widthPixels = context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取图片
     */
    @Override
    public Drawable getDrawable(String source) {
        LevelListDrawable d = new LevelListDrawable();
//        Drawable empty = context.getResources().getDrawable(
//                R.mipmap.img_default);
//        d.addLevel(0, 0, empty);
//        d.setBounds(0, 0,widthPixels,
//                empty.getIntrinsicHeight());
        new LoadImage().execute(source, d);
        return d;
    }

    /**
     * 异步下载图片类
     *
     * @author Ruffian
     * @date 2016年1月15日
     *
     */
    class LoadImage extends AsyncTask<Object, Void, Bitmap> {


        private LevelListDrawable mDrawable;

        @Override
        protected Bitmap doInBackground(Object... params) {
            String source = (String) params[0];
            mDrawable = (LevelListDrawable) params[1];
            //特殊处理，进行来处理https协议进行来显示的图片
            X509TrustManager xtm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    X509Certificate[] x509Certificates = new X509Certificate[0];
                    return x509Certificates;
                }
            };

            SSLContext sslContext = null;
            try {
                sslContext = SSLContext.getInstance("SSL");

                sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
            HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            try {
                URL url = new URL(source);
                HttpsURLConnection httpsURLConnection= (HttpsURLConnection) url.openConnection();
                httpsURLConnection.setHostnameVerifier(DO_NOT_VERIFY);
                httpsURLConnection.setSSLSocketFactory(sslContext.getSocketFactory());
                InputStream is = httpsURLConnection.getInputStream();
                return BitmapFactory.decodeStream(is);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 图片下载完成后执行
         */
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                try{
                    BitmapDrawable d = new BitmapDrawable(bitmap);
                    mDrawable.addLevel(1, 1, d);
                    /**
                     * 适配图片大小 <br/>
                     * 默认大小：bitmap.getWidth(), bitmap.getHeight()<br/>
                     * 适配屏幕：getDrawableAdapter
                     */
                    mDrawable = getDrawableAdapter(context, mDrawable,
                            bitmap.getWidth(), bitmap.getHeight());

                    // mDrawable.setBounds(0, 0, bitmap.getWidth(),
                    // bitmap.getHeight());

                    mDrawable.setLevel(1);

                    /**
                     * 图片下载完成之后重新赋值textView<br/>
                     * mtvActNewsContent:我项目中使用的textView
                     *
                     */
                    textView.invalidate();
                    CharSequence t = textView.getText();
                    textView.setText(t);

                }catch (Exception e){
                    Log.e("onPostExecute", "onPostExecute: "+e.toString() );
                }

            }
        }

        /**
         * 加载网络图片,适配大小
         *
         * @param context
         * @param drawable
         * @param oldWidth
         * @param oldHeight
         * @return
         * @author Ruffian
         * @date 2016年1月15日
         */
        public LevelListDrawable getDrawableAdapter(Context context,
                                                    LevelListDrawable drawable, int oldWidth, int oldHeight) {
            LevelListDrawable newDrawable = drawable;
            long newHeight = 0;// 未知数
            newHeight = (widthPixels * oldHeight) / oldWidth;
            // LogUtils.w("oldWidth:" + oldWidth + "oldHeight:" +
            // oldHeight);
            // LogUtils.w("newHeight:" + newHeight + "newWidth:" +
            // newWidth);
            newDrawable.setBounds(0, 0, widthPixels, (int) newHeight);
            return newDrawable;
        }
    }
}
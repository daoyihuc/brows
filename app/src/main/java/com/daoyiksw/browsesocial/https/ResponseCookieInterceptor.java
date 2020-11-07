package com.daoyiksw.browsesocial.https;

import android.util.Log;

import com.daoyiksw.browsesocial.untils.LogUtils_dy;
import com.daoyiksw.browsesocial.untils.MacUtils;
import com.google.gson.Gson;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author 道义（daoyi）
 * @version 1.0
 * @date 2020-05-05
 * @params 拦截返回数据
 **/
public class ResponseCookieInterceptor implements Interceptor {

    private String TAG=ResponseCookieInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Response response = chain.proceed(request);
        LogUtils_dy.e("HTTP:------>"+response.body());
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();

        if (!bodyEncoded(response.headers())) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    return response;
                }
            }

            if (!isPlaintext(buffer)) {
                return response;
            }

            if (contentLength != 0) {
                String result = buffer.clone().readString(charset);
                Log.e(TAG, " response.url():"+ response.request().url());
                Log.e(TAG, " response.body():" + result);
                //得到所需的string，开始判断是否异常
                //***********************do something*****************************

//                Gson gson=new Gson();
//                BaseBean baseBean = gson.fromJson(result, BaseBean.class);
//                if(baseBean.code==4011){
//                    if(UserInfoHelper.isLogin()){
//                        mainThread("登录过期,请重新登录");
//                    }
//
//
//                }


            }

        }


        return response;
    }


    private static final Charset UTF8 = Charset.forName("UTF-8");

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

    static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    private void mainThread(String data){

        Observable.create(new Observable.OnSubscribe<Object>() {


            @Override
            public void call(Subscriber<? super Object> subscriber) {
                subscriber.onNext(data);
                subscriber.onCompleted();
            }

        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())//指定解绑发生在IO线程
                .observeOn(AndroidSchedulers.mainThread())//指回调发生在主线程
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
//                        MacUtils.ToastShow(application.instance(),o.toString(),-2,0);
//                        LogUtils_dy.e("登录过期");
//                        application.instance().logout();
//                        setLogout();
                    }
                });
    }

    private void setLogout(){





    }

}

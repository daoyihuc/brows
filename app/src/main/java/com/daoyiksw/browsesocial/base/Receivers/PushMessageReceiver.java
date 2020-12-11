package com.daoyiksw.browsesocial.base.Receivers;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.shanyue88.shanyueshenghuo.R;
import com.shanyue88.shanyueshenghuo.base.AppManager;
import com.shanyue88.shanyueshenghuo.base.application;
import com.shanyue88.shanyueshenghuo.pub.rxbus.RxBus;
import com.shanyue88.shanyueshenghuo.pub.rxbus.event.VideoChatEvent;
import com.shanyue88.shanyueshenghuo.thirdparty.network.HttpMethods;
import com.shanyue88.shanyueshenghuo.ui.base.activity.BaseActivity;
import com.shanyue88.shanyueshenghuo.ui.base.response.BaseResponse;
import com.shanyue88.shanyueshenghuo.ui.base.response.RequestParam;
import com.shanyue88.shanyueshenghuo.ui.home.activity.MainActivity;
import com.shanyue88.shanyueshenghuo.ui.home.bean.CustomMessageType;
import com.shanyue88.shanyueshenghuo.ui.messagess.activity.AcceptVideoDialog;
import com.shanyue88.shanyueshenghuo.ui.messagess.activity.MessageSystemActivity;
import com.shanyue88.shanyueshenghuo.ui.messagess.activity.VideoChatActivity;
import com.shanyue88.shanyueshenghuo.ui.messagess.bean.NoticeBean;
import com.shanyue88.shanyueshenghuo.ui.messagess.bean.NoticeType;
import com.shanyue88.shanyueshenghuo.ui.tasks.activity.TaskDetailActivity;
import com.shanyue88.shanyueshenghuo.ui.tasks.utils.TimeUtils;
import com.shanyue88.shanyueshenghuo.ui.user.pub.UserInfoHelper;
import com.shanyue88.shanyueshenghuo.utils.JsonUtils;
import com.shanyue88.shanyueshenghuo.utils.LocationUtils;
import com.shanyue88.shanyueshenghuo.utils.LogUtils;
import com.shanyue88.shanyueshenghuo.utils.LogUtils_dy;
import com.shanyue88.shanyueshenghuo.utils.StringUtils;
import com.shanyue88.shanyueshenghuo.utils.SystemUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.CmdMessage;
import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;
import rx.Subscriber;

public class PushMessageReceiver extends JPushMessageReceiver{
    private static final String TAG = "PushMessageReceiver";
    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        Log.e(TAG,"[onMessage] "+customMessage);
        LogUtils.show("收到极光消息："+customMessage);
        processCustomMessage(context,customMessage);
    }


    //点击通知回调
    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage message) {
        Log.e(TAG,"[onNotifyMessageOpened] "+message);
        LogUtils_dy.e("当前通知标示"+message.notificationExtras);

        NoticeBean noticeBean = null;
        try {
            noticeBean = JsonUtils.gsonToBean(message.notificationExtras, NoticeBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        NoticeType noticeType =NoticeType.TypeOftype(noticeBean.getType());
        if(noticeType.getCode() <300) {   //系统或者用户通知
            MessageSystemActivity.start(context);
        } else if(noticeType.getCode() >300 && noticeType.getCode() <400) {   //任务订单通知

            TaskDetailActivity.start(context, noticeBean.getTaskId());
        }



//        for(NoticeType s:NoticeType.values()){
//            if(noticeBean.getType().equals(s.getType())){
//
//            }else if(noticeBean.getType().equals("qwe")){
//                LogUtils_dy.e("监测到通知类型"+noticeBean.getType());
//            }
//        }



//        try{
//            //打开自定义的Activity
//            Intent i = new Intent(context, MainActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putString(JPushInterface.EXTRA_NOTIFICATION_TITLE,message.notificationTitle);
//            bundle.putString(JPushInterface.EXTRA_ALERT,message.notificationContent);
//            i.putExtras(bundle);
//            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
//            context.startActivity(i);
//        }catch (Throwable throwable){
//
//        }
    }

    //通知的MultiAction回调
    @Override
    public void onMultiActionClicked(Context context, Intent intent) {
        Log.e(TAG, "[onMultiActionClicked] 用户点击了通知栏按钮");
        String nActionExtra = intent.getExtras().getString(JPushInterface.EXTRA_NOTIFICATION_ACTION_EXTRA);
        LogUtils_dy.e("当前通知标示"+nActionExtra);
        //开发者根据不同 Action 携带的 extra 字段来分配不同的动作。
        if(nActionExtra==null){
            Log.d(TAG,"ACTION_NOTIFICATION_CLICK_ACTION nActionExtra is null");
            return;
        }
        if (nActionExtra.equals("my_extra1")) {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮一");
        } else if (nActionExtra.equals("my_extra2")) {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮二");
        } else if (nActionExtra.equals("my_extra3")) {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮三");
        } else {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮未定义");
        }
    }


    //收到通知回调
    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage message) {
        Log.e(TAG,"[onNotifyMessageArrived] "+message);
        LogUtils.show("极光通知："+ JsonUtils.getJsonStr(message));
        playSound(context);
    }


    //清除通知回调
    @Override
    public void onNotifyMessageDismiss(Context context, NotificationMessage message) {
        Log.e(TAG,"[onNotifyMessageDismiss] "+message);
    }


    //注册成功回调
    @Override
    public void onRegister(Context context, String registrationId) {
        Log.e(TAG,"[onRegister] "+registrationId);
    }

    //长链接状态
    @Override
    public void onConnected(Context context, boolean isConnected) {
        Log.e(TAG,"[onConnected] "+isConnected);
    }

    //注册失败
    @Override
    public void onCommandResult(Context context, CmdMessage cmdMessage) {
        Log.e(TAG,"[onCommandResult] "+cmdMessage);
    }

    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
//        TagAliasOperatorHelper.getInstance().onTagOperatorResult(context,jPushMessage);
        super.onTagOperatorResult(context, jPushMessage);
    }
    @Override
    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage){
//        TagAliasOperatorHelper.getInstance().onCheckTagOperatorResult(context,jPushMessage);
        super.onCheckTagOperatorResult(context, jPushMessage);
    }
    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
//        TagAliasOperatorHelper.getInstance().onAliasOperatorResult(context,jPushMessage);
        super.onAliasOperatorResult(context, jPushMessage);
    }

    @Override
    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
//        TagAliasOperatorHelper.getInstance().onMobileNumberOperatorResult(context,jPushMessage);
        super.onMobileNumberOperatorResult(context, jPushMessage);
    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, CustomMessage customMessage) {

        if(customMessage.extra !=null && !"".equals(customMessage.extra)) {

            Map<String, Object> paramsMap =JsonUtils.gsonToBean(customMessage.extra, HashMap.class);
            String typeStr = StringUtils.getValue(paramsMap.get("type"));
            CustomMessageType messageType =CustomMessageType.TypeOfcode(typeStr);
            if(messageType ==CustomMessageType.VIDEO_CALL) {    //请求视频聊天

                String userId =StringUtils.getValue(paramsMap.get("id"));
                String msgKey =messageType.getCode() +userId;
                if(!application.isMessageExist(msgKey)) {

                    application.setMessageKey(msgKey);
                    String time =StringUtils.getValue(paramsMap.get("time"));
                    Date sysDate = TimeUtils.getDate(time);
                    BaseActivity activity = AppManager.getAppManager().findActivity(AcceptVideoDialog.class);
                    BaseActivity videoActivity = AppManager.getAppManager().findActivity(VideoChatActivity.class);
                    if(activity ==null && videoActivity ==null && TimeUtils.getDateDiffMinute(sysDate, new Date()) <2) {   //2分钟以内的请求
                        BaseActivity mainActivity =AppManager.getAppManager().findActivity(MainActivity.class);
                        if(mainActivity !=null) {
                            SystemUtils.Poweropen(mainActivity);
                            AcceptVideoDialog.startForAccepts(mainActivity, userId);
                        } else {
                            try {
                                SystemUtils.Poweropen(context);
                                AcceptVideoDialog.startForAccepts((BaseActivity) context, userId);
                            } catch (Exception e) {}
                        }
                    }
                } else{
                    LogUtils.show("@@@ @@@ 环信已执行，我不执行");
                    application.removeMessageKey(msgKey);
                }
            } else if(messageType ==CustomMessageType.CANCEL_VIDEO) {    //取消视频聊天请求

                String userId =StringUtils.getValue(paramsMap.get("id"));

                String msgKey =messageType.getCode() +userId;
                if(!application.isMessageExist(msgKey)) {

                    application.setMessageKey(msgKey);
                    RxBus.getDefault().post(new VideoChatEvent("type_cancel", userId));
                } else{
                    LogUtils.show("@@@ @@@ 环信已执行，我不执行");
                    application.removeMessageKey(msgKey);
                }
            } else if(messageType ==CustomMessageType.REFUSE_VIDEO) {      //对方拒绝视频聊天

                String userId =StringUtils.getValue(paramsMap.get("id"));

                String msgKey =messageType.getCode() +userId;
                if(!application.isMessageExist(msgKey)) {

                    application.setMessageKey(msgKey);
                    RxBus.getDefault().post(new VideoChatEvent("type_refuse", userId));
                } else{
                    LogUtils.show("@@@ @@@ 环信已执行，我不执行");
                    application.removeMessageKey(msgKey);
                }
            } else if(messageType ==CustomMessageType.ACCEPT_VIDEO) {      //对方接受视频聊天（发两方）

                String chatId =StringUtils.getValue(paramsMap.get("chat_time_id"));

                String msgKey =messageType.getCode() +chatId;
                if(!application.isMessageExist(msgKey)) {

                    application.setMessageKey(msgKey);
                    String userId =StringUtils.getValue(paramsMap.get("user_id"));
                    if(TextUtils.equals(userId, UserInfoHelper.getUserId(context)))   //取对方的id
                        userId =StringUtils.getValue(paramsMap.get("master_id"));
                    String coin =StringUtils.getValue(paramsMap.get("user_coin"));
                    String leftSeconds =StringUtils.getValue(paramsMap.get("left_seconds"));
                    String videoPrice =StringUtils.getValue(paramsMap.get("video_price"));
                    VideoChatEvent event =new VideoChatEvent("type_accept", userId, chatId);
                    event.setRoomId(StringUtils.getValue(paramsMap.get("room_id")));
                    event.setVideoPrice(videoPrice);
                    event.setCoin(coin);
                    event.setLeftSeconds(leftSeconds);
                    RxBus.getDefault().post(event);
                } else{
                    LogUtils.show("@@@ @@@ 环信已执行，我不执行");
                    application.removeMessageKey(msgKey);
                }
            }
        }
    }

    //通知开关回调
    @Override
    public void onNotificationSettingsCheck(Context context, boolean isOn, int source) {
        super.onNotificationSettingsCheck(context, isOn, source);
        Log.e(TAG,"[onNotificationSettingsCheck] isOn:"+isOn+",source:"+source);
    }


    Ringtone mRingtone;

    //播放自定义的声音
    public synchronized void playSound(Context context) {

        if (mRingtone == null) {
            LogUtils.show("----------初始化铃声----------");
            String uri = "android.resource://" + context.getPackageName() + "/" + R.raw.message;
            Uri no = Uri.parse(uri);
            mRingtone = RingtoneManager.getRingtone(context.getApplicationContext(), no);
        }
        if (!mRingtone.isPlaying()) {
            LogUtils.show("--------------播放铃声---------------" + mRingtone.isPlaying());
            mRingtone.play();
        }

    }

    //开始任务，上传我的位置接口
    private void requestUploadLocation(String taskid) {

        String latStr = StringUtils.getValue(LocationUtils.getInstance().getLatitude());
        String lngStr = StringUtils.getValue(LocationUtils.getInstance().getLongitude());

        RequestParam params =new RequestParam.Builder()
                .putParam("task_id", taskid)
                .putParam("longitude", lngStr)
                .putParam("latitude", latStr).build();

        LogUtils.show("请求参数："+JsonUtils.getJsonStr(params.getRequestMap()));

        HttpMethods.getInstance().uploadLocation(new Subscriber<BaseResponse>() {
            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) { }

            @Override
            public void onNext(BaseResponse baseResponse) {

            }
        }, params.getRequest());
    }
}

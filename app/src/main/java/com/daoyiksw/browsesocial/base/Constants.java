package com.daoyiksw.browsesocial.base;
/**
 * 公共常量
 */
public interface Constants {

//    String MAIN_URL = "https://admin.shanyue88.com/api/";    //正式服务器主域名
//    String WEBSOCKET_URL ="wss://admin.shanyue88.com/wss";   //websocket正式地址

//    String MAIN_URL = "https://appoint.jishuzai.top/api/";     //测试服务器主域名
//    String WEBSOCKET_URL ="wss://appoint.jishuzai.top/wss";    //websocket测试地址

    String MAIN_URL = "https://proappoint.jishuzai.top/api/";     //准生产服务器主域名
    String WEBSOCKET_URL ="wss://proappoint.jishuzai.top/wss";    //websocket测试地址

//    String MAIN_URL = "https://iosappoint.jishuzai.top/api/";     //iOs服务器主域名
//    String WEBSOCKET_URL ="wss://iosappoint.jishuzai.top/wss";    //websocket测试地址

    String SIGN_SECRET = "hsjt20190802";//对请求参数签名的字符串秘钥


    public static final boolean DEBUG = true;
    public static final String PACKAGE_NAME = "com.shanyue88.shanyueshenghuo";

    public static final String HELP_URL ="http://s.zhimakf.com/s/20104xi0r";   //客服h5地址

    public static final int HEAD_IMAGE_SIZE =100;      //列表小圆角头像显示图片尺寸
    public static final int ANGLE_HEAD_IMAGE_SIZE =200;      //列表小直角头像显示图片尺寸
    public static final int HOME_HEAD_IMAGE_SIZE =300;  //列表小直角头像显示图片尺寸
    public static final int DYNAMIC_IMAGE_SIZE =300;   //列表动态图片尺寸
    public static final int BUSINSS_IMAGE_SIZE=300;  //商家列表图片尺寸

    //公共token
    public static final String TOKEN ="token";
    //闪约
    public static final String SY_USER_ID ="sy_user_id";                  //用户id
    public static final String SY_USER_ACCOUNT ="sy_user_account";        //用户账号
    public static final String SY_TOKEN ="sy_user_token";                 //用户TOEKN
    public static final String SY_PHONE ="sy_phone";                      //手机
    public static final String SY_SEX ="sy_sex";                          //性别
    public static final String SY_USER_NICKNAME ="sy_user_nickname";      //用户昵称
    public static final String SY_USER_AVATAR ="sy_user_avatar";          //用户头像
    public static final String SY_USER_PASSWORD ="sy_user_password";      //用户密码
    public static final String SY_USER_UNIQUEID ="sy_user_uniqueid";      //环信ID
    public static final String SY_USER_ISAGENT ="sy_user_isagent";        //是否代理
    public static final String SY_USER_ISMEMBER ="sy_user_ismember";      //是否会员
    public static final String SY_USER_ISMASTER ="sy_user_ismaster";      //是否达人
    public static final String SY_THER_UNIQUEID ="sy_ther_uniqueid";      //目标对方uniqueid
    public static final String SY_USER_ISNAMEAUTH ="sy_user_isauth";      //是否实名认证
    public static final String SY_USER_IMPROVE_PERSONAL_INFO ="sy_user_Improve_personal_info";      //是否完善个人资料
    public static final String SY_USER_IS_BIND_CARD ="sy_user_is_bind_card";      //是否绑定银行卡
    public static final String SY_USER_COIN ="sy_user_coin";                  //金币余额
    public static final String SY_USER_CANSEND ="sy_user_cansend";                  //免费次数
    String UPDATE_TIME_SIGN="update_time_sign";//签到弹窗更新时间
    String SY_UPDATE_TIME="sy_user_time";//时间更新
    String SY_UNREAD_MESSAGE="sy_unread_message";//未读消息数量
    String SY_ISLOCATION="sy_islocation";//位置是否可以切换
    String IS_FIRST="is_first";//是否是第一次安装使用
    String IS_MESSAGE_Y="is_message_Y";//聊天提示
    String SY_STAFF_UNIQUE_ID="staff_unique_id";//客服id
    String SY_SAY_HELLO="sy_say_hello";//打招呼时间


    // 环信 存储登录用户信息
    public static final String USER_ID ="login_user_id";//
    public static final String USER_ACCOUNT ="login_user_account";
    public static final String USER_NICKNAME ="login_user_nickname";
    public static final String USER_AVATAR="login_user_avatar";
    public static final String USER_ISAGENT ="login_user_ismember";    //是否代理
    public static final String USER_ISMEMBER ="login_user_ismember";   //是否会员
    public static final String USER_ISMASTER ="login_user_ismaster";   //是否达人

    //一键登录密钥
    String one_key="3YIRk3evopmviVwr9UbBuG+J+fJD4sgG3iA06I8sbcLhnVFFW2epsbC4ttXFy5bFgUYC" +
            "Oi7QkNpnr2pLBoKmXa/RdgiXc1gpfdQ4whj1WW9RCimpvcB3hzUVzGrWZNZKeK7coNY5AQupsFJ" +
            "OglLCPyU0LNZgmjAo4Lmc69gZfzBP1D7JM+giQfU/WNqAs6Vvc6/E+ZKYIreD8emwcvD/76VQp/em9I" +
            "VnVp7feo/Jxn8nN8PbZGzGxmQngC2KViSTAwuZf3Fp4DnlamseseHLW8hEMKiFCyK1YiuRDViMpnY=";
    // 存储
    public static final String USERINFO = "userInfo";
    public static final String ACCOUNT = "account";
    public static final String PWD = "password";
    public static final String ROOM = "room";
    public static final String AUTO_LOGIN = "auto_login";
    public static final String LOGOUT = "logout";
    public static final String ICON_URL = "icon_url";

    public static final String CHAT_INFO = "chatInfo";

    public static final String EXTRA_CHAT_TYPE = "chatType";
    public static final String EXTRA_USER_ID = "userId";
    public static final String MESSAGE_ATTR_IS_VOICE_CALL = "is_voice_call";
    public static final String MESSAGE_ATTR_IS_VIDEO_CALL = "is_video_call";

    public static final String MESSAGE_TYPE_RECALL = "message_recall";

    public static final String MESSAGE_ATTR_IS_BIG_EXPRESSION = "em_is_big_expression";
    public static final String MESSAGE_ATTR_EXPRESSION_ID = "em_expression_id";

    public static final String MESSAGE_ATTR_AT_MSG = "em_at_list";
    public static final String MESSAGE_ATTR_VALUE_AT_MSG_ALL = "ALL";



    public static final int CHATTYPE_SINGLE = 1;
    public static final int CHATTYPE_GROUP = 2;
    public static final int CHATTYPE_CHATROOM = 3;


    public static final String NEW_FRIENDS_USERNAME = "item_new_friends";
    public static final String GROUP_USERNAME = "item_groups";
    public static final String CHAT_ROOM = "item_chatroom";
    public static final String ACCOUNT_REMOVED = "account_removed";
    public static final String ACCOUNT_CONFLICT = "conflict";
    public static final String ACCOUNT_FORBIDDEN = "user_forbidden";
    public static final String ACCOUNT_KICKED_BY_CHANGE_PASSWORD = "kicked_by_change_password";
    public static final String ACCOUNT_KICKED_BY_OTHER_DEVICE = "kicked_by_another_device";
    public static final String CHAT_ROBOT = "item_robots";
    public static final String MESSAGE_ATTR_ROBOT_MSGTYPE = "msgtype";
    public static final String ACTION_GROUP_CHANAGED = "action_group_changed";
    public static final String ACTION_CONTACT_CHANAGED = "action_contact_changed";

    public static final String EXTRA_CONFERENCE_ID = "confId";
    public static final String EXTRA_CONFERENCE_PASS = "password";
    public static final String EXTRA_CONFERENCE_INVITER = "inviter";
    public static final String EXTRA_CONFERENCE_IS_CREATOR = "is_creator";
    public static final String EXTRA_CONFERENCE_GROUP_ID = "group_id";

    public static final String MSG_ATTR_CONF_ID = "conferenceId";
    public static final String MSG_ATTR_CONF_PASS = EXTRA_CONFERENCE_PASS;
    public static final String MSG_ATTR_EXTENSION = "msg_extension";

    public static final String EM_CONFERENCE_OP = "em_conference_op";
    public static final String EM_CONFERENCE_ID = "em_conference_id";
    public static final String EM_CONFERENCE_PASSWORD = "em_conference_password";
    public static final String EM_CONFERENCE_TYPE = "em_conference_type";
    public static final String EM_MEMBER_NAME = "em_member_name";

    public static final String OP_INVITE = "invite";
    public static final String OP_REQUEST_TOBE_SPEAKER = "request_tobe_speaker";
    public static final String OP_REQUEST_TOBE_AUDIENCE = "request_tobe_audience";

    public static final String SP_APP = "shanyue";


    //首页缓存
    public static final String MAIN_BANNER_CACHE ="cache_main_banner";     //首页banner图
    public static final String MAIN_SKILL_CACHE ="cache_main_skill";       //首页技能
    public static final String MAIN_MASTER_CACHE ="cache_main_master";     //首页推荐达人（只保存6条数据）
    public static final String MAIN_CONFIG_CACHE ="cache_main_config";     //首页系统配置

    //任务下单流程
    String[] TASK_PROCESS_COMMONS =new String[]{"发布任务", "支付任务金", "达人报名", "选择达人", "达人确认", "确认赴约", "完成任务", "评价任务"};
    String[] TASK_PROCESS_MASTERS =new String[]{"发布任务", "支付任务金",  "达人确认", "确认赴约", "完成任务", "评价任务"};
}

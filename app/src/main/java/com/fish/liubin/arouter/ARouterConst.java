package com.fish.liubin.arouter;

/**
 * 功能----路径跳转activity/fragment
 * <p>
 * Created by ACChain on 2017/11/24.
 */

public final class ARouterConst {
    /**
     * 无权限
     * 登录
     * 绑定手机
     * 身份认证
     * 交易密码
     */
    public static final int FLAG_NONE = 0x00000000;
    public static final int FLAG_LOGIN = 0x00000003;
    public static final int FLAG_PHONE = 0x00000008;
    public static final int FLAG_VERIFY = 0x00000010;
    public static final int FLAG_PASSWORD = 0x00000020;
//    public static final int FLAG_FORBID_ACCESS = 0x00000040;
//    public static final int FLAG_WIFI_NET = 0x00000080;
//    public static final int FLAG_MOBILE_NET = 0x00000100;
//    public static final int FLAG_ACTIVITY_CLEAR_TOP = 0x00000200;
//    public static final int FLAG_BUSINESS_CARD = 0x00000400;


    public static final String Activity_AActivity = "/activity/AActivity";
    public static final String Activity_BActivity = "/activity/BActivity";
    public static final String Activity_LoginActivity = "/activity/LoginActivity";
    public static final String Activity_PhoneActivity = "/activity/PhoneActivity";
    public static final String Activity_VertifyActivity = "/activity/VertifyActivity ";
    public static final String Activity_PasswordActivity = "/activity/PasswordActivity ";
}

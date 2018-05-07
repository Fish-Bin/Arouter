package com.fish.liubin.arouter;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fish.liubin.BaseApplication;
import com.fish.liubin.L;

/**
 * function : 跳转拦截器(权限拦截)
 * <p>
 * 比较经典的应用就是在跳转过程中处理登陆事件，这样就不需要在目标页重复做登陆检查
 * 拦截器会在跳转之间执行，多个拦截器会按优先级顺序依次执行
 *
 * @author ACChain
 */

@Interceptor(priority = 2, name = "ARouter跳转拦截器")
public class ARouterInterceptor implements IInterceptor {
    @Override
    public void init(Context context) {
        // 拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次
        L.i("拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次");
    }

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        L.i("######界面跳转 ： " + postcard.toString());
        //当前所有的权限
        String[] permissions = new String[]{
                "登录",
                "绑定手机",
                "实名认证",
                "设置交易密码",
        };
        int[] FLAGS_ALL = new int[]{
                ARouterConst.FLAG_LOGIN,
                ARouterConst.FLAG_PHONE,
                ARouterConst.FLAG_VERIFY,
                ARouterConst.FLAG_PASSWORD,
        };

        //当前所有权限对应的boolean值;为false则对应权限设为 ARouterConst.FLAG_NONE
        boolean[] FLAGS_ALL_VALUE = new boolean[]{
                BaseApplication.isLogin,
                BaseApplication.isPhone,
                BaseApplication.isVertify,
                BaseApplication.isPassword
        };

        //目标界面需要的权限
        L.i(Integer.toBinaryString(postcard.getExtra())); //0x00000100
        L.i(Integer.toBinaryString(Integer.MIN_VALUE)); //0x00000003

        int requireFlags = postcard.getExtra() | Integer.MIN_VALUE;  // 0x80000000
        L.i("######目标所需权限 : " + Integer.toBinaryString(requireFlags));

        //如果目标无需任何权限,跳过权限判断逻辑
        if (requireFlags == Integer.MIN_VALUE) {
            callback.onContinue(postcard);
            return;
        }

        //当前所有的权限
        int currentFlags = Integer.MIN_VALUE;
        for (int position = 0; position < FLAGS_ALL.length; position++) {
            currentFlags=currentFlags|(FLAGS_ALL_VALUE[position] ? FLAGS_ALL[position] : ARouterConst.FLAG_NONE);
        }
        L.i("######当前所有权限 : " + Integer.toBinaryString(currentFlags));

        //如果需要的权限都已存在,则直接跳转,不做处理
        if ((requireFlags & currentFlags) == requireFlags) {
            callback.onContinue(postcard);
            return;
        }

        //如果发现不一致,说明某些权限不存在,则需要依次判断哪个权限不存在
        for (int position = 0; position < FLAGS_ALL.length; position++) {
            //requireFlags  -- 1000 0000 0000 0000 0000 0000 0000 0100
            //currentFlags  -- 1000 0000 0000 0000 0000 0000 0000 0000
            if ((requireFlags & FLAGS_ALL[position]) != 0 && (currentFlags & FLAGS_ALL[position]) == 0) {
                boolean consume = false;
                switch (position) {
                    case 0: //未登录
                        consume = dispatchLogin(postcard, callback);
                        break;
                    case 1: //未绑定手机
                        consume = dispatchPhone(postcard, callback);
                        break;
                    case 2: //未实名认证
                        consume = dispatchVertify(postcard, callback);
                        break;
                    case 3://未设置交易密码
                        consume = dispatchPassword(postcard, callback);
                        break;
                    default: {
                        callback.onInterrupt(new RuntimeException("没有 " + permissions[position] + " 权限"));
                    }
                }
//                if (!consume) {
//                    callback.onInterrupt(new RuntimeException("界面无法跳转"));
//                }
                return;
            }
        }
        //权限定义错误
        L.i("未知权限");
    }

    /**
     * 处理未登录操作
     */
    private boolean dispatchLogin(Postcard postcard, InterceptorCallback callback) {
        if (postcard.getPath().equalsIgnoreCase(ARouterConst.Activity_BActivity)) {
            callback.onInterrupt(new RuntimeException(" 你还没有登录"));
            return false;
        }
        replaceDes(postcard, ARouterConst.Activity_LoginActivity);
        process(postcard, callback);
        return true;
    }

    /**
     * 处理未绑定手机
     */
    private boolean dispatchPhone(Postcard postcard, InterceptorCallback callback) {
        replaceDes(postcard, ARouterConst.Activity_PhoneActivity);
        process(postcard, callback);
        return true;
    }

    /**
     * 处理未实名认证
     */
    private boolean dispatchVertify(Postcard postcard, InterceptorCallback callback) {
        replaceDes(postcard, ARouterConst.Activity_VertifyActivity);
        process(postcard, callback);
        return true;
    }

    /**
     * 处理未实名认证
     */
    private boolean dispatchPassword(Postcard postcard, InterceptorCallback callback) {
        replaceDes(postcard, ARouterConst.Activity_PasswordActivity);
        process(postcard, callback);
        return true;
    }

    /**
     * 更换意图的跳转路径
     * 然后进行跳转处理
     *
     * @param postcard 意图
     * @param des      目的 string
     */
    private void replaceDes(Postcard postcard, String des) {
        //动态的修改postcard信息,更换跳转路径
        Postcard newPostcard = ARouter.getInstance().build(des);
        LogisticsCenter.completion(newPostcard);
        postcard.setExtra(newPostcard.getExtra()).setPath(newPostcard.getPath()).setGroup(newPostcard.getGroup()).setDestination(newPostcard.getDestination());
    }
}
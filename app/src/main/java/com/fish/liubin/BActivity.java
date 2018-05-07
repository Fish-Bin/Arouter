package com.fish.liubin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fish.liubin.arouter.ARouterConst;

@Route(path = ARouterConst.Activity_BActivity,extras = ARouterConst.FLAG_PHONE)
public class BActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);//此时开始初始化传过来的参数
        setContentView(R.layout.activitya_b);
    }
}

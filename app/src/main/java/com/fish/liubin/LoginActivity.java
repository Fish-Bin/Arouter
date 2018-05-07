package com.fish.liubin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fish.liubin.arouter.ARouterConst;

@Route(path = ARouterConst.Activity_LoginActivity,extras = ARouterConst.FLAG_VERIFY|ARouterConst.FLAG_PASSWORD)
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitya_login);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseApplication.isLogin=true;
                finish();
            }
        });
    }
}

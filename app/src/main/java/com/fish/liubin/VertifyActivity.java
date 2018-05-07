package com.fish.liubin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fish.liubin.arouter.ARouterConst;

@Route(path = ARouterConst.Activity_VertifyActivity)
public class VertifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitya_vertify);
        findViewById(R.id.vertify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseApplication.isVertify=true;
                finish();
            }
        });
    }
}

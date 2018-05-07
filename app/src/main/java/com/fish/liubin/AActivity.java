package com.fish.liubin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fish.liubin.arouter.ARouterConst;

@Route(path = ARouterConst.Activity_AActivity)
public class AActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitya_a);
        findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip();
            }
        });
        L.i(Integer.toBinaryString(0x00000000));
        L.i(Integer.toBinaryString(0x00000003));
        L.i(Integer.toBinaryString(0x00000004));
        L.i(Integer.toBinaryString(0x00000008));
        L.i(Integer.toBinaryString(0x00000010));
        L.i(Integer.toBinaryString(0x00000020));
        L.i(Integer.toBinaryString(0x00000040));
        L.i(Integer.toBinaryString(0x00000080));
        L.i(Integer.toBinaryString(0x00000100));
        L.i(Integer.toBinaryString(0x00000200));
        L.i(Integer.toBinaryString(0x00000400));
        L.i(Integer.toBinaryString(0x00001000));

        L.i(Integer.toBinaryString(0x80000000));
        int i = 0x00000003 & 0x00000003;
        L.i("i="+i);
    }

    public void skip(){
        // 监听跳转
        ARouter.getInstance().build(ARouterConst.Activity_BActivity).navigation(AActivity.this, new NavCallback() {
            @Override
            public void onArrival(Postcard postcard) {
                L.i("跳转完了");
            }

            @Override
            public void onFound(Postcard postcard) {
                L.i("找到了");
            }
            @Override
            public void onLost(Postcard postcard) {
                L.i("找不到了");
            }
            @Override
            public void onInterrupt(Postcard postcard) {
                L.i("被拦截了");
            }
        });
    }
}

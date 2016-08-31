package com.tc.dekequan.view.activity;

import android.os.Bundle;
import android.os.Handler;

import com.tc.dekequan.R;
import com.tc.dekequan.presenter.BasePresenter;

/**
 * author：   tc
 * date：     2016/8/29 & 22:20
 * version    1.0
 * description 启动页
 * modify by
 */
public class SplashActivity extends BaseActivity {
    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE); //设置无标题
//        getWindow().setFlags(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams
//                .FILL_PARENT);  //设置全屏
        setContentView(R.layout.activity_splash);
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(HomeActivity.class, null);
                finish();
            }
        }, 2 * 1000);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }



    @Override
    public void obtainData() {

    }

    @Override
    public void setContentLayout() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {

    }
}

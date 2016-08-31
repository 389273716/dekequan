package com.tc.dekequan.view.activity;

import android.os.Bundle;

import com.tc.dekequan.presenter.BasePresenter;
import com.tomtop.ttcom.view.activity.StackActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * author：   tc
 * date：     2016/8/16 & 21:46
 * version    1.0
 * description 基类activity
 * modify by
 */
public abstract class BaseActivity<T extends BasePresenter> extends StackActivity {
    protected BaseActivity mBaseViewActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        if (getPresenter() != null) {
            getPresenter().destroy();
        }
        for (T t : getPresenters()) {
            if (t != null) {
                t.destroy();
            }
        }
        super.onDestroy();
    }


    /**
     * 循环销毁presenter，存在多个presenter
     *
     * @return
     */
    protected List<T> getPresenters() {
        List<T> presenters = new ArrayList<>();
        return presenters;
    }

    /**
     * 获取本界面使用的presenter，当本界面只存在一个时使用本方法
     *
     * @return
     */
    public abstract T getPresenter();
}

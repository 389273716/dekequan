package com.tc.dekequan.presenter;


import com.tc.dekequan.presenter.base.IView;
import com.tc.dekequan.view.activity.BaseActivity;

import java.lang.ref.WeakReference;

/**
 * @author tc
 * @version 1.0
 * @date 2016/3/27 10:20
 * @description p逻辑层基类
 */
public class BasePresenter<T extends IView> {
    protected T mIView;
    protected WeakReference<BaseActivity> mActivity;

    public BasePresenter(T t, BaseActivity baseActivity) {
        mActivity = new WeakReference<>(baseActivity);
        this.mIView = t;
    }

    public BasePresenter(WeakReference<BaseActivity> mActivity) {
        this.mActivity = mActivity;
    }

    public BasePresenter(T t) {
        this.mIView = t;
    }


    /**
     * 销毁界面
     */
    public void destroy() {
        mIView = null;
    }
}

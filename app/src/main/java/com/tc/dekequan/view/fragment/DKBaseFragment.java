package com.tc.dekequan.view.fragment;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.res.ResourcesCompat;

import com.tc.dekequan.presenter.BasePresenter;
import com.tc.dekequan.view.activity.BaseActivity;
import com.tomtop.ttcom.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author：   tc
 * date：     2016/8/31 & 21:50
 * version    1.0
 * description
 * modify by
 */
public abstract class DKBaseFragment<T extends BasePresenter> extends BaseFragment {

    protected BaseActivity mBaseViewActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mBaseViewActivity = (BaseActivity) activity;
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
        mBaseViewActivity = null;
        super.onDestroy();
    }

    public float getResourceDimension(@DimenRes int dimenId) {
        return isAdded() ? getResources().getDimension(dimenId) : 0;
    }

    public int getResourceColor(@ColorRes int colorId, @Nullable Resources.Theme theme) {
        return isAdded() ? ResourcesCompat.getColor(getResources(), colorId, null) : 0;
    }

    public String getResourceString(@StringRes int stringId) {
        return isAdded() ? getResources().getString(stringId) : null;
    }

    public String getResourceString(@StringRes int id, Object... formatArgs) {
        return isAdded() ? getResources().getString(id, formatArgs) : null;
    }

    public Drawable getResourceDrawable(@DrawableRes int id) {
        return isAdded() ? ResourcesCompat.getDrawable(getResources(), id, null) : null;
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

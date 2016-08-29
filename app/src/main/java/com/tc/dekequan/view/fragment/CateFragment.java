package com.tc.dekequan.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tc.dekequan.R;
import com.tomtop.ttcom.view.fragment.BaseFragment;

/**
 * author：   tc
 * date：     2016/8/29 & 22:35
 * version    1.0
 * description
 * modify by
 */
public class CateFragment extends BaseFragment {

    public static CateFragment newInstance() {
        CateFragment mainFragment = new CateFragment();
        return mainFragment;
    }


    @Override
    protected View setContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle
            bundle) {
        return layoutInflater.inflate(R.layout.fragment_home_cate, viewGroup, false);
    }

    @Override
    public void obtainData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void bindData() {

    }
}

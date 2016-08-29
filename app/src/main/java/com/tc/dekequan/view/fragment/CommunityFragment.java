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
public class CommunityFragment extends BaseFragment {

    public static CommunityFragment newInstance() {
        CommunityFragment mainFragment = new CommunityFragment();
        return mainFragment;
    }


    @Override
    protected View setContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle
            bundle) {
        return layoutInflater.inflate(R.layout.fragment_home_community, viewGroup, false);
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

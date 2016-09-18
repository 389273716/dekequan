package com.tc.dekequan.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.tc.dekequan.R;
import com.tc.dekequan.entity.request.UserLoginReq;
import com.tc.dekequan.model.UserModel;
import com.tc.dekequan.presenter.BasePresenter;
import com.tc.dekequan.view.fragment.CateFragment;
import com.tc.dekequan.view.fragment.CommunityFragment;
import com.tc.dekequan.view.fragment.SmartFragment;
import com.tc.dekequan.view.fragment.UserCenterFragment;
import com.tomtop.ttcom.bean.json.BaseJson;
import com.tomtop.ttcom.http.volley.ResponseCallback;
import com.tomtop.ttcom.view.fragment.BaseFragment;
import com.tomtop.ttutil.log.LogUtil;

/**
 * author：   tc
 * date：     2016/8/29 & 22:20
 * version    1.0
 * description 首页，此页面加载4个fragment首页
 * modify by
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private LinearLayout mLlSmart;
    private LinearLayout mLlCommunity;
    private LinearLayout mLlCate;
    private LinearLayout mLlUserCenter;
    BaseFragment[] mFragments = new BaseFragment[4];

    private int mCurrentShowIndex = 0;//当前显示页面的索引

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            mFragments[0] = SmartFragment.newInstance();
            mFragments[1] = CommunityFragment.newInstance();
            mFragments[2] = CateFragment.newInstance();
            mFragments[3] = UserCenterFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, mCurrentShowIndex,
                    mFragments[0],
                    mFragments[1],
                    mFragments[2],
                    mFragments[3]);
        } else {
            // 这里库已经做了Fragment恢复工作，不需要额外的处理
            // 这里我们需要拿到mFragments的引用，用下面的方法查找更方便些，也可以通过getSupportFragmentManager.getFragments()
            // 自行进行判断查找(效率更高些)
            mFragments[0] = findFragment(SmartFragment.class);
            mFragments[1] = findFragment(CommunityFragment.class);
            mFragments[2] = findFragment(CateFragment.class);
            mFragments[3] = findFragment(UserCenterFragment.class);
        }
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
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initView() {
        mLlSmart = (LinearLayout) findViewById(R.id.ll_home_smart);
        mLlCommunity = (LinearLayout) findViewById(R.id.ll_home_community);
        mLlCate = (LinearLayout) findViewById(R.id.ll_home_cate);
        mLlUserCenter = (LinearLayout) findViewById(R.id.ll_home_user_center);
    }

    @Override
    protected void initEvent() {
        mLlSmart.setOnClickListener(this);
        mLlCommunity.setOnClickListener(this);
        mLlCate.setOnClickListener(this);
        mLlUserCenter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        LogUtil.e(TAG, "onClick: ");
        switch (v.getId()) {
            case R.id.ll_home_smart:
                UserLoginReq entity=new UserLoginReq();
                entity.setPassword("123456");
                entity.setUserName("123456@qq.com");
                UserModel.login(entity, "tag", new ResponseCallback<BaseJson>() {
                    @Override
                    public void onSuccess(BaseJson baseJson, String s) {
                        LogUtil.e(baseJson.toString());
                    }

                    @Override
                    public void onFailed(int i, String s, VolleyError volleyError) {
                        LogUtil.e(s);
                    }
                });
                showFragment(0);
                break;
            case R.id.ll_home_community:
                showFragment(1);
                break;
            case R.id.ll_home_cate:
                showFragment(2);
                break;
            case R.id.ll_home_user_center:
                showFragment(3);
                break;
            default:
                break;
        }
    }

    private void showFragment(int index) {
        showHideFragment(mFragments[index], mFragments[mCurrentShowIndex]);
        mCurrentShowIndex = index;
    }
}

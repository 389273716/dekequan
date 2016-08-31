package com.tc.dekequan.presenter;

import com.android.volley.VolleyError;
import com.tc.dekequan.presenter.iview.ILoginContact;
import com.tc.dekequan.view.activity.BaseActivity;
import com.tomtop.ttcom.bean.json.BaseJson;
import com.tomtop.ttcom.http.volley.ResponseCallback;

/**
 * author：   tc
 * date：     2016/8/31 & 21:37
 * version    1.0
 * description  登录界面的presenter逻辑层
 * modify by
 */
public class LoginPresenter extends BasePresenter<ILoginContact.ILoginView> implements
        ILoginContact.ILoginPresenter, ResponseCallback<BaseJson> {
    private static final String TAG = LoginPresenter.class.getSimpleName();

    public LoginPresenter(ILoginContact.ILoginView iLoginView, BaseActivity baseActivity) {
        super(iLoginView, baseActivity);
    }

    @Override
    public void login() {

    }

    @Override
    public void onSuccess(BaseJson baseJson, String s) {

    }

    @Override
    public void onFailed(int i, String s, VolleyError volleyError) {

    }
}

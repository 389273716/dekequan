package com.tc.dekequan.view.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tc.dekequan.R;
import com.tc.dekequan.presenter.LoginPresenter;
import com.tc.dekequan.presenter.iview.ILoginContact;
import com.tc.dekequan.util.PhoneNumberUtil;
import com.tomtop.ttcom.bean.json.BaseJson;
import com.tomtop.ttutil.ToastUtil;

/**
 * author：   tc
 * date：     2016/9/18 & 21:35
 * version    1.0
 * description
 * modify by
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginContact
        .ILoginView {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText mEtPassword;
    private EditText mEtUserName;
    private Button mBtSubmit;
    private LoginPresenter mPresenter;

    @Override
    public LoginPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void obtainData() {
        mPresenter = new LoginPresenter(this, this);
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView() {
        mEtUserName = (EditText) findViewById(R.id.et_login_user_name);
        mEtPassword = (EditText) findViewById(R.id.et_login_password);
        mBtSubmit = (Button) findViewById(R.id.bt_login_submit);
    }

    /**
     * 检查输入合法性
     */
    private boolean judgeParam() {
        String username = mEtUserName.getText().toString();
        String password = mEtPassword.getText().toString();
        if (TextUtils.isEmpty(username)) {
            mEtUserName.requestFocus();
            ToastUtil.show(R.string.username_is_empty);
            return false;
        }
        if (!PhoneNumberUtil.judgePhoneNums(username)) {
            mEtUserName.requestFocus();
            ToastUtil.show(R.string.username_is_not_phone_number);
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            mEtPassword.requestFocus();
            ToastUtil.show(R.string.username_is_empty);
            return false;
        }
        if (password.length() <= 6) {
            mEtPassword.requestFocus();
            ToastUtil.show(R.string.username_is_empty);
            return false;
        }
        return true;
    }

    @Override
    protected void initEvent() {
        mBtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (judgeParam()) mPresenter.login();
            }
        });
    }

    @Override
    public String getReqTag() {
        return TAG;
    }

    @Override
    public String getPassword() {
        return mEtPassword.getText().toString();
    }

    @Override
    public String getUserName() {
        return mEtUserName.getText().toString();
    }

    @Override
    public void onSuccess(BaseJson data) {
        startActivity(HomeActivity.class, null);
    }

    @Override
    public void onFailure(String msg) {
        ToastUtil.show(msg);
    }
}

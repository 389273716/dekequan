package com.tc.dekequan.presenter.iview;

import com.tc.dekequan.presenter.base.IView;
import com.tomtop.ttcom.bean.json.BaseJson;

/**
 * author：   tc
 * date：     2016/8/31 & 21:39
 * version    1.0
 * description 登录界面相关的接口
 * modify by
 */
public interface ILoginContact {
    interface ILoginView extends IView {
        /**
         * 网络请求tag，用于取消请求
         * @return
         */
        String getReqTag();

        String getPassword();

        String getUserName();

        void onSuccess(BaseJson data);

        void onFailure(String msg);
    }

    interface ILoginPresenter {
        void login();
    }
}

package com.tc.dekequan.model;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tc.dekequan.common.BizInterface;
import com.tc.dekequan.entity.request.UserLoginReq;
import com.tomtop.ttcom.bean.json.BaseJson;
import com.tomtop.ttcom.http.volley.CustomResponseListenerImpl;
import com.tomtop.ttcom.http.volley.ResponseCallback;
import com.tomtop.ttcom.http.volley.VolleyUtil;

import java.util.Map;

/**
 * author：   tc
 * date：     2016/9/1 & 23:00
 * version    1.0
 * description
 * modify by
 */
public class UserModel {
    public static void login(UserLoginReq entity, String tag, ResponseCallback<BaseJson>
            callback) {
        String url = BizInterface.LOGIN;
        TypeToken<BaseJson> typeToken = new TypeToken<BaseJson>() {
        };
        Map<String, String> headers = BaseModel.getCommonHttpHeaders("token");

        CustomResponseListenerImpl listener = new CustomResponseListenerImpl(callback, typeToken
                .getType());
        VolleyUtil.getInstance().jsonRequest(url, Request.Method.POST, headers, new Gson().toJson
                (entity), tag,
                listener);
    }
}

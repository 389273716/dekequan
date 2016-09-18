package com.tc.dekequan.model;

import android.content.Context;
import android.os.Build;

import com.tc.dekequan.base.BaseApplication;
import com.tc.dekequan.common.GlobalConstants;
import com.tomtop.ttutil.CommonUtil;

import java.util.HashMap;
import java.util.Map;

import test.http.Utils;

/**
 * author：   tc
 * date：     2016/9/1 & 23:05
 * version    1.0
 * description
 * modify by
 */
public class BaseModel {
    public static Map<String, String> getCommonHttpHeaders(String token) {
        Map<String, String> headers = new HashMap<>();
        Context mContext = BaseApplication.getInstance().getApplicationContext();
        headers.put("flat", String.valueOf(GlobalConstants.PLATFORM));
        headers.put("sys_vs", Build.VERSION.RELEASE);
        headers.put("model", Build.MODEL);
        headers.put("Accept-Language", CommonUtil.getLanguageAbbreviation(mContext));
        headers.put("cur_vs", Utils.getVersionName(mContext));
        headers.put("cur_code", String.valueOf(Utils.getVersionCode(mContext)));
//        headers.put("imei", CacheManager.getInstance().getDeviceId());
        headers.put("Charset", "UTF-8");
        headers.put("Content-Type", "application/json;");
        headers.put("Accept-Encoding", "application/json/gzip");
        headers.put("Accept-Language", "zh-CN");
        headers.put("Connection", "Keep-Alive");
//        headers.put("uuid", CacheManager.getInstance().getAccountEntity().getUuid());
//        LogUtil.d("CommonUtils", CacheManager.getInstance().getAccountEntity().getEmail());
        headers.put("token".intern(), token);
        return headers;
    }
}

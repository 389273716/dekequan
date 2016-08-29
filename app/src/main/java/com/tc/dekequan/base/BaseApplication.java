package com.tc.dekequan.base;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.tomtop.ttcom.http.volley.VolleyUtil;
import com.tomtop.ttcom.image.FrescoHelper;
import com.tomtop.ttutil.CrashHandler;
import com.tomtop.ttutil.log.LogUtil;


public class BaseApplication extends Application {

    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        FrescoHelper.getInstance().initializeFresco(this);
        //异常处理
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
        LogUtil.init(true);
        VolleyUtil.getInstance().initialize(this);
    }


}

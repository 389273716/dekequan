package com.tc.dekequan.base;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.volley.VolleyUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.facebook.stetho.Stetho;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;
import com.tomtop.ttcom.http.volley.VolleyUtil;
import com.tomtop.ttutil.CrashHandler;
import com.tomtop.ttutil.log.LogUtil;

import java.io.InputStream;


public class BaseApplication extends Application {

    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    static LiteOrm liteOrm;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
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
        Glide.get(this)
                .register(GlideUrl.class, InputStream.class,new VolleyUrlLoader.Factory
                        (VolleyUtil.getInstance().getRequestQueue()));
        initDB();
    }

    private void initDB() {
        if (liteOrm == null) {
            DataBaseConfig config = new DataBaseConfig(this, "dk_quan.db");
            //"liteorm.db"是数据库名称，名称里包含路径符号"/"则将数据库建立到该路径下，可以使用sd卡路径。 不包含则在系统默认路径下创建DB文件。
            //例如 public static final String DB_NAME = SD_CARD + "/lite/orm/liteorm.db";
            // DataBaseConfig config = new DataBaseConfig(this, DB_NAME);
            config.dbVersion = 1; // set database version
            config.onUpdateListener = null; // set database update listener
            //独立操作，适用于没有级联关系的单表操作，
//            liteOrm = LiteOrm.newSingleInstance(config);
            //级联操作,适用于多表级联操作
            liteOrm = LiteOrm.newCascadeInstance(config);
        }
        liteOrm.setDebugged(true); // open the log
    }


}

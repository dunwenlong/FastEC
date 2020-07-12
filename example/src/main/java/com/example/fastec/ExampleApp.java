package com.example.fastec;

import android.app.Application;

import com.example.latte_core.app.Latte;
import com.example.latte_core.net.interceptors.DebugInterceptor;
import com.example.latte_ec.database.DatabaseManager;
import com.facebook.stetho.Stetho;

/**
 * @author Dun Wenlong
 * @time 2020/7/8 16:01
 */
public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://127.0.0.1")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
        DatabaseManager.getInstance().init(this);
        initStetho();
    }
    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}

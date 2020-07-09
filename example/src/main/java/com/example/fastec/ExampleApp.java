package com.example.fastec;

import android.app.Application;

import com.example.latte_core.app.Latte;

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
                .configure();
    }
}

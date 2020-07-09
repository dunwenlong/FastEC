package com.example.latte_core.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * @author Dun Wenlong
 * @time 2020/7/8 16:04
 */
public class Latte {
    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static WeakHashMap<String, Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplicationContext(){
//        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
        return (Context) Configurator.getInstance().getConfiguration(ConfigType.APPLICATION_CONTEXT);
    }
}

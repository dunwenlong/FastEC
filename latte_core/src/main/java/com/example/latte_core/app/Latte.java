package com.example.latte_core.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * @author Dun Wenlong
 * @time 2020/7/8 16:04
 */
public class Latte {
    public static Configurator init(Context context){
        getConfigurations().put(Configkeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator(){
        return Configurator.getInstance();
    }
    public static WeakHashMap<Object, Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplicationContext(){
//        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
        return (Context) Configurator.getInstance().getConfiguration(Configkeys.APPLICATION_CONTEXT);
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }
}

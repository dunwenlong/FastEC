package com.example.latte_core.app;

import java.util.ArrayList;
import java.util.WeakHashMap;

import okhttp3.Interceptor;

/**
 * @author Dun Wenlong
 * @time 2020/7/8 16:05
 * 进行配置文件存储和获取
 */
public class Configurator {
    private static final WeakHashMap<Object, Object> LATTE_CONFIGS = new WeakHashMap<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    private Configurator() {
        LATTE_CONFIGS.put(Configkeys.CONFIG_READY, false);
    }

    private static class ConfiguratorHolder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return ConfiguratorHolder.INSTANCE;
    }

    final WeakHashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public final void configure() {
        LATTE_CONFIGS.put(Configkeys.CONFIG_READY, true);
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(Configkeys.API_HOST, host);
        return this;
    }
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(Configkeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(Configkeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(Configkeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready, call configure");
        }

    }
    final <T> T getConfiguration(Object key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key);
    }

}

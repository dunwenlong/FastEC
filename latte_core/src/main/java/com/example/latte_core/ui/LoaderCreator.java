package com.example.latte_core.ui;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * @author Dun Wenlong
 * @time 2020/7/9 15:29
 */
public final class LoaderCreator {
    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();
    // 已缓存的方式创建loader
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    static AVLoadingIndicatorView create(String type, Context context) throws ClassNotFoundException {
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if(LOADING_MAP.get(type) == null){
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static Indicator getIndicator(String name) {
        if(name == null || name.isEmpty()){
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        //name包含点说明传入的为类名
        if(!name.contains(".")){
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName).append(".indicators").append(".");
        }
        drawableClassName.append(name);
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }
}

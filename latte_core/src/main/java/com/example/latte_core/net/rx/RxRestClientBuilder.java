package com.example.latte_core.net.rx;

import android.content.Context;

import com.example.latte_core.net.RestClient;
import com.example.latte_core.net.RestCreator;
import com.example.latte_core.net.callback.IError;
import com.example.latte_core.net.callback.IFailure;
import com.example.latte_core.net.callback.IRequest;
import com.example.latte_core.net.callback.ISuccess;
import com.example.latte_core.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author Dun Wenlong
 * @time 2020/7/8 21:05
 */
public class RxRestClientBuilder {
    private String mUrl;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private RequestBody mBody;
    private Context mContext;
    private LoaderStyle mLoaderStyle;
    private File mFile;

    RxRestClientBuilder() {
    }

    public final RxRestClientBuilder url(String mUrl) {
        this.mUrl = mUrl;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(raw, MediaType.parse("application/json;charset = UTF-8"));
        return this;
    }

    public final RxRestClientBuilder loader(Context context, LoaderStyle style) {
        this.mLoaderStyle = style;
        this.mContext = context;
        return this;
    }

    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }


    public final RxRestClientBuilder loader(Context context) {
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        this.mContext = context;
        return this;
    }

    public final RxRestClient build() {
        return new RxRestClient(mUrl, PARAMS, mBody,
                mLoaderStyle, mContext, mFile);
    }
}

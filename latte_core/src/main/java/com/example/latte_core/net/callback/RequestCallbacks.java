package com.example.latte_core.net.callback;

import android.os.Handler;

import com.example.latte_core.ui.LatteLoader;
import com.example.latte_core.ui.LoaderStyle;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Dun Wenlong
 * @time 2020/7/9 9:51
 */
public class RequestCallbacks implements Callback<String> {
    private final IRequest REQUEST;
    private final IError ERROR;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER = new Handler();
    public RequestCallbacks(IRequest request, IError error, ISuccess success, IFailure failure, LoaderStyle loader_style) {
        REQUEST = request;
        ERROR = error;
        SUCCESS = success;
        FAILURE = failure;
        LOADER_STYLE = loader_style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
            if(call.isExecuted()){
                if(SUCCESS!=null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if(ERROR!=null){
                ERROR.onError(response.code(), response.message());
            }
        }
        stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if(FAILURE!=null){
            FAILURE.onFailure();
        }
        if(REQUEST!=null){
            REQUEST.onRequestEnd();
        }
        stopLoading();
    }

    private void stopLoading(){
        if(LOADER_STYLE!=null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },2000);
        }
    }
}

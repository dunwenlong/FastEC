package com.example.latte_ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.latte_core.app.AccountManager;
import com.example.latte_core.app.IUserChecker;
import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_core.ui.launcher.ILauncherListener;
import com.example.latte_core.ui.launcher.OnLauncherFinishTag;
import com.example.latte_core.ui.launcher.ScrollLauncherTag;
import com.example.latte_core.util.LattePreference;
import com.example.latte_core.util.timer.BaseTimerTask;
import com.example.latte_core.util.timer.ITimerListener;
import com.example.latte_ec.R;
import com.example.latte_ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Dun Wenlong
 * @time 2020/7/10 9:29
 */
public class LauncherDelegate extends LatteDelegate implements ITimerListener {

    private int mCount = 5;
    private Timer mTimer = null;
    private ILauncherListener myILauncherListener = null;
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            chechIsShowScroll();
        }
    }
    private  void initTimer(){
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ILauncherListener){
            myILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) throws ClassNotFoundException {
        initTimer();
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mTvTimer!=null){
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            chechIsShowScroll();
                        }
                    }
                }
            }
        });
    }
    //判断是否启动滑动启动页
    private void chechIsShowScroll(){
        if(!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else{
            //检查用户是否登录APP
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if(myILauncherListener!=null){
                        myILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if(myILauncherListener!=null){
                        myILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }
}

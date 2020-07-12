package com.example.latte_core.util.timer;

import java.util.TimerTask;

/**
 * @author Dun Wenlong
 * @time 2020/7/10 9:31
 */
public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}

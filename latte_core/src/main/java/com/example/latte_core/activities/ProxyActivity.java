package com.example.latte_core.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.ContentFrameLayout;

import com.example.latte_core.R;
import com.example.latte_core.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportActivityDelegate;

/**
 * @author Dun Wenlong
 * @time 2020/7/8 16:32
 */
public abstract class ProxyActivity extends SupportActivity {
    private final SupportActivityDelegate DELEGATE = new SupportActivityDelegate(this);
    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DELEGATE.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState){
      @SuppressLint("RestrictedApi") final ContentFrameLayout container = new ContentFrameLayout(this);
       container.setId(R.id.delegate_container);
       setContentView(container);
       if(savedInstanceState == null){
           DELEGATE.loadRootFragment(R.id.delegate_container, setRootDelegate());
       }
    }

    @Override
    protected void onDestroy() {
        DELEGATE.onDestroy();
        super.onDestroy();
        System.gc();
    }
}

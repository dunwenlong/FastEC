package com.example.latte_core.delegates;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.latte_core.activities.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * @author Dun Wenlong
 * @time 2020/7/8 16:34
 */
public abstract class BaseDelegate extends SwipeBackFragment {

    private Unbinder mUnbinder = null;
    //让子类传入布局，可以为View,也可以为layout的ID
    public abstract Object setLayout();
    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView) throws ClassNotFoundException;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((int) setLayout(), container, false);
        } else if(setLayout() instanceof  View){
            rootView = (View) setLayout();
        }
        if(rootView!=null){
            mUnbinder = ButterKnife.bind(this, rootView);
            try {
                onBindView(savedInstanceState, rootView);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return rootView;
//        final View rootView;
//        if (setLayout() instanceof Integer) {
//            rootView = inflater.inflate((int) setLayout(), container, false);
//        } else if (setLayout() instanceof View) {
//            rootView = (View) setLayout();
//        } else {
//            throw new ClassCastException("type of setLayout() must be int or View!");
//        }
//        mUnbinder = ButterKnife.bind(this, rootView);
//        try {
//            onBindView(savedInstanceState, rootView);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return rootView;
    }
    public final ProxyActivity getProxyActivity() {
        return (ProxyActivity) _mActivity;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mUnbinder!=null){
            mUnbinder.unbind();
        }
    }
}

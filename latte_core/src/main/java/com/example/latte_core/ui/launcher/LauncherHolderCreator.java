package com.example.latte_core.ui.launcher;

import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.example.latte_core.R;

/**
 * @author Dun Wenlong
 * @time 2020/7/10 10:53
 */
public class LauncherHolderCreator implements CBViewHolderCreator{
    @Override
    public Holder createHolder(View itemView) {
        return new LauncherHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_banner;
    }
}

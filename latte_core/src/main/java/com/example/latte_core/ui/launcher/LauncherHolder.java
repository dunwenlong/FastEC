package com.example.latte_core.ui.launcher;

import android.view.View;
import android.widget.ImageView;
import com.example.latte_core.R;
import com.bigkoo.convenientbanner.holder.Holder;

/**
 * @author Dun Wenlong
 * @time 2020/7/10 10:54
 */
public class LauncherHolder extends Holder<Integer> {
    private ImageView mImageView;
    public LauncherHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        mImageView = itemView.findViewById(R.id.iv_banner1);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public void updateUI(Integer data) {
        mImageView.setBackgroundResource(data);
    }
}

package com.yushilei.ptr;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.LevelListDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.logging.Level;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * @author by  yushilei.
 * @time 2016/9/29 -11:13.
 * @Desc
 */
public class PtrUIHeader extends FrameLayout implements PtrUIHandler {
    String TAG = "PtrUIHeader";
    private ImageView pullImg;
    private View mHeader;
    private ImageView refreshImg;
    private LevelListDrawable pullAnimDrawable;
    private AnimationDrawable refAnimDrawable;

    public PtrUIHeader(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mHeader = LayoutInflater.from(context).inflate(R.layout.ptr_ui_header, this, false);

        pullImg = ((ImageView) mHeader.findViewById(R.id.header_pull));
        refreshImg = ((ImageView) mHeader.findViewById(R.id.header_refreshing));

        refAnimDrawable = ((AnimationDrawable) refreshImg.getDrawable());
        pullAnimDrawable = ((LevelListDrawable) pullImg.getDrawable());

        addView(mHeader);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        Log.d(TAG, "onUIReset");
        pullAnimDrawable.setLevel(0);
        refAnimDrawable.stop();
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

        Log.d(TAG, "onUIRefreshPrepare");
        refreshImg.setVisibility(GONE);
        pullImg.setVisibility(VISIBLE);
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        Log.d(TAG, "onUIRefreshBegin");
        refreshImg.setVisibility(VISIBLE);
        pullImg.setVisibility(GONE);
        refAnimDrawable.start();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        refAnimDrawable.stop();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

        float currentPercent = ptrIndicator.getCurrentPercent();
        if (currentPercent >= 1) {
            refreshImg.setVisibility(VISIBLE);
            pullImg.setVisibility(GONE);
        } else {
            int v = (int) (currentPercent * 10) / 2;
            pullAnimDrawable.setLevel(v);
            refreshImg.setVisibility(GONE);
            pullImg.setVisibility(VISIBLE);
        }

        Log.d(TAG, "onUIPositionChange =" + currentPercent);
    }
}

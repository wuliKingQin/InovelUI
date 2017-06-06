package com.tv.inovelui.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.tv.framework.activity.AbsBaseActivity;
import com.tv.inovelui.R;

public class MainActivity extends AbsBaseActivity {

    //时间背景视图
    private View mTimeBackgroundView;

    @Override
    public void onCreateView() {
        setContentView(R.layout.activity_main);
        mTimeBackgroundView = findViewById(R.id.fl_time);
    }

    @Override
    public void onInitObjects() {

    }

    @Override
    public void onSetListeners() {

    }

    @Override
    public void onInitData(Bundle bundle) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 设置时间背景颜色
     * @param color 颜色值
     */
    public void setTimeBackgroundColor(int color) {
        mTimeBackgroundView.setBackgroundColor(color);
    }
}

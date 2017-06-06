package com.tv.inovelui.ui.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.tv.inovelui.R;

import java.util.Calendar;

/**
 * 功能描述：时间视图
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/5/17- 10:23
 */

public class TimeView extends FrameLayout {

    //日历
    private Calendar mCalendar;
    //显示分秒
    private TextView mMinuteSecondTv;
    //显示月天
    private TextView mMouthDayTv;

    public TimeView(Context context) {
        this(context, null);
    }

    public TimeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_time,this);
        mMinuteSecondTv = (TextView) findViewById(R.id.tv_mts);
        mMouthDayTv = (TextView) findViewById(R.id.tv_ytd);
        showCurrentTime(System.currentTimeMillis());
    }


    /**
     * 显示当前时间
     * @param timeMillis 毫秒时间
     */
    private void showCurrentTime(long timeMillis) {
        mMinuteSecondTv.setText(getHourMinuteStr(timeMillis));
        mMouthDayTv.setText(getMouthDayStr(timeMillis));
    }

    /**
     * 获取时分时间
     * @return 时分时间字符串
     */
    private String getHourMinuteStr(long currentMillis) {
        mCalendar = Calendar.getInstance();
        final StringBuilder builder = new StringBuilder();
        mCalendar.setTimeInMillis(currentMillis);
        builder.append(mCalendar.get(Calendar.HOUR_OF_DAY));
        builder.append(":");
        builder.append(mCalendar.get(Calendar.MINUTE));
        return builder.toString();
    }

    /**
     * 获取月日字符串
     * @param currentMillis 当前时间
     * @return 获取月日字符串
     */
    private String getMouthDayStr(long currentMillis) {
        mCalendar = Calendar.getInstance();
        final StringBuilder builder = new StringBuilder();
        mCalendar.setTimeInMillis(currentMillis);
        builder.append(mCalendar.get(Calendar.MONTH) + 1);
        builder.append("月");
        builder.append(mCalendar.get(Calendar.DAY_OF_MONTH));
        builder.append("日  ");
        builder.append(getWeekStr(currentMillis));
        return builder.toString();
    }

    /**
     * 获取周几
     * @param currentMillis 当前毫秒时间
     * @return 周几
     */
    private String getWeekStr(long currentMillis) {
        String weekStr;
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(currentMillis);
        int week = mCalendar.get(Calendar.DAY_OF_WEEK);
        switch (week) {
            case Calendar.SUNDAY:
                weekStr = getString(R.string.week_seven);
                break;
            case Calendar.MONDAY:
                weekStr = getString(R.string.week_one);
                break;
            case Calendar.TUESDAY:
                weekStr = getString(R.string.week_two);
                break;
            case Calendar.WEDNESDAY:
                weekStr = getString(R.string.week_three);
                break;
            case Calendar.THURSDAY:
                weekStr = getString(R.string.week_four);
                break;
            case Calendar.FRIDAY:
                weekStr = getString(R.string.week_five);
                break;
            case Calendar.SATURDAY:
            default:
                weekStr = getString(R.string.week_six);
                break;
        }
        return weekStr;
    }

    /**
     * 获取资源字符串
     * @param resId 资源Id
     * @return 字符串
     */
    private String getString(int resId) {
        return getResources().getString(resId);
    }


    /**
     * 注册广播
     */
    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_TIME_TICK);
        filter.addAction(Intent.ACTION_TIME_CHANGED);
        filter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        getContext().registerReceiver(mIntentReceiver,filter);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        registerReceiver();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getContext().unregisterReceiver(mIntentReceiver);
    }

    /**
     * 接收时间改变广播
     */
    private final BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (Intent.ACTION_TIMEZONE_CHANGED.equals(action) ||
                    Intent.ACTION_TIME_TICK.equals(action)||
                    Intent.ACTION_TIME_CHANGED.equals(action)) {
                showCurrentTime(System.currentTimeMillis());
            }
        }
    };


}

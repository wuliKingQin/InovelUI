package com.tv.inovelui;

import android.app.Application;

import com.pgyersdk.crash.PgyCrashManager;
import com.tv.framework.AppHelper;

/**
 * 功能描述：UIApp
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/5/27- 11:43
 */

public class UIApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PgyCrashManager.register(this);
        AppHelper.instance().initCoreApp(this);
    }
}

package com.tv.inovelui.model;

import android.graphics.drawable.Drawable;

/**
 * 功能描述：已安装app
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/5/12- 10:23
 */

public class InstalledApp extends Model {

    //app图标
    private Drawable appIcon;
    //包名
    private String packageName;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }
}

package com.tv.inovelui.model;

import android.content.ComponentName;

/**
 * 功能描述：多媒体
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/4/20- 9:18
 */

public class Media extends Model {

    //包名
    private static final String PACKAGE_NAME = "com.tv.filemanager";
    //要启动的类名
    private static final String CLASS_NAME = "com.tv.filemanager.activity.MainActivity";

    public ComponentName getMediaComponent() {
        return new ComponentName(PACKAGE_NAME,CLASS_NAME);
    }
}

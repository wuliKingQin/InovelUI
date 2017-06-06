package com.tv.inovelui.ui.view;

import android.view.View;

/**
 * 功能描述：构建视图
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/4/20- 11:34
 */

public interface TitleBuilder {

    /**
     * 在搜索右边添加其他视图
     * @param view 需要添加的视图
     */
    void addSearchBadgeView(View view);

    /**
     * 移除搜索右边的视图
     * @param view 需要移除的视图
     */
    void removeSearchBadgeView(View view);

    /**
     * 在标题上方添加其他视图
     * @param view 需要添加的视图
     */
    void addTitleBadgeView(View view);

    /**
     * 移除标题右边的视图
     * @param view 需要移除的视图
     */
    void removeTitleBadgeView(View view);

    /**
     * 设置搜索边缘背景颜色
     * @param color 颜色值
     */
    void setSearchBadgeBackgroundColor(int color);
}

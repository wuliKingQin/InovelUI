package com.tv.inovelui.model;

/**
 * 功能描述：数据模型
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/4/19- 11:34
 */

public interface IModel {

    /**
     * 设置标题字符串
     * @param title 标题
     */
    void setTitle(String title);

    /**
     * 获取标题信息
     * @return 标题
     */
    String getTitle();

    /**
     * 设置描述信息
     * @param description 描述值
     */
    void setDescription(String description);

    /**
     * 获取描述信息
     * @return 描述信息
     */
    String getDescription();

    /**
     * 设置模型界面宽度大小
     * @param width 宽度
     */
    void setWidth(int width);

    /**
     * 获取模型界面宽度大小
     * @return 宽度值
     */
    int getWidth();

    /**
     * 设置模型界面高度大小
     * @param height 高度值
     */
    void setHeight(int height);

    /**
     * 获取模型界面高度值
     * @return 高度值
     */
    int getHeight();

    /**
     * 默认图标资源
     * @return 返回资源ID
     */
    int getDefaultResId();

    /**
     * 设置默认图标资源
     * @param defaultResId 默认资源Id
     */
    void setDefaultResId(int defaultResId);
}

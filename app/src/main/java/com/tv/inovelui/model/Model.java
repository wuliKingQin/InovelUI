package com.tv.inovelui.model;

/**
 * 功能描述：数据模型
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/4/19- 11:52
 */

public class Model implements IModel {

    //模型标题
    private String title;
    //模型描述
    private String description;
    //宽度
    private int width = 440;
    //高度
    private int height = 254;
    //默认资源图片Id
    private int defaultResId;

    public Model() {
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getDefaultResId() {
        return defaultResId;
    }

    @Override
    public void setDefaultResId(int defaultResId) {
        this.defaultResId = defaultResId;
    }
}

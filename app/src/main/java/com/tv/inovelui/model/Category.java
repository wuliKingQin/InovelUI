package com.tv.inovelui.model;

import java.util.List;

/**
 * 功能描述：种类数据
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/4/19- 11:28
 */

public class Category<T extends IModel> {
    //类名
    private String name;
    //保存类别数据
    private List<T> data;

    public Category() {
    }

    public Category(List<T> data) {
        this(null, data);
    }

    public Category(String name,List<T> data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

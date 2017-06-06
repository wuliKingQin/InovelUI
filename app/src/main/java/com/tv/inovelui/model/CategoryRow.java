package com.tv.inovelui.model;

import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ObjectAdapter;

/**
 * 功能描述：分类列表
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/4/19- 14:05
 */

public class CategoryRow extends ListRow {

    //分类
    private Category category;

    public CategoryRow(HeaderItem header, ObjectAdapter adapter, Category category) {
        super(header, adapter);
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

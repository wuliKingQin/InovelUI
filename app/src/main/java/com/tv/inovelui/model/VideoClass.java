package com.tv.inovelui.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 功能描述：视频分类
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/5/15- 17:41
 */

public class VideoClass {
    //查询Id
    @SerializedName("id")
    private String id;
    //布局文件地址
    @SerializedName("layoutFile")
    private String layoutFile;
    //分类视频
    @SerializedName("itemList")
    private List<Video> itemList;

    public VideoClass() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLayoutFile() {
        return layoutFile;
    }

    public void setLayoutFile(String layoutFile) {
        this.layoutFile = layoutFile;
    }

    public List<Video> getItemList() {
        return itemList;
    }

    public void setItemList(List<Video> itemList) {
        this.itemList = itemList;
    }
}

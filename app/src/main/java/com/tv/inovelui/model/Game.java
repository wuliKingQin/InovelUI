package com.tv.inovelui.model;

import com.google.gson.annotations.SerializedName;

/**
 * 功能描述：游戏
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/5/11- 19:31
 */

public class Game extends Model {

    //索引
    @SerializedName("index")
    private int index;
    //图片地址
    @SerializedName("bgimage")
    private String bgimage;
    //节点类型
    @SerializedName("nodeType")
    private String nodeType;
    //视图类型
    @SerializedName("viewType")
    private String viewType;
    //返回类型
    @SerializedName("returnType")
    private String returnType;
    //内容ID
    @SerializedName("contentId")
    private String contentId;

    public Game() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getBgimage() {
        return bgimage;
    }

    public void setBgimage(String bgimage) {
        this.bgimage = bgimage;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
}

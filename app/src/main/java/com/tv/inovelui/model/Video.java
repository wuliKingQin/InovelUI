package com.tv.inovelui.model;

import com.google.gson.annotations.SerializedName;
/**
 * 功能描述：视频
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/5/11- 18:58
 */
public class Video extends Model{

    //视频获取id
    @SerializedName("id")
    private String id;
    //图片
    @SerializedName("image")
    private String image;
    //名字
    @SerializedName("name")
    private String name;
    //子名字
    @SerializedName("subName")
    private String subName;
    //活动
    @SerializedName("action")
    private String action;
    //活动参数
    @SerializedName("actionParam")
    private ActionParam actionParam;
    //描述信息
    @SerializedName("descInfo")
    private String descInfo;
    //H5地址
    @SerializedName("h5Url")
    private String h5Url;

    public Video() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ActionParam getActionParam() {
        return actionParam;
    }

    public void setActionParam(ActionParam actionParam) {
        this.actionParam = actionParam;
    }

    public String getDescInfo() {
        return descInfo;
    }

    public void setDescInfo(String descInfo) {
        this.descInfo = descInfo;
    }

    public String getH5Url() {
        return h5Url;
    }

    public void setH5Url(String h5Url) {
        this.h5Url = h5Url;
    }

    /**
     * 跳转参数
     */
    public class ActionParam {
        //是否被选择
        @SerializedName("select")
        private String select;

        public ActionParam() {
        }

        public String getSelect() {
            return select;
        }

        public void setSelect(String select) {
            this.select = select;
        }
    }
}

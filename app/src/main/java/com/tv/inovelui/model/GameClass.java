package com.tv.inovelui.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 功能描述：游戏JSON请求
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/5/16- 16:36
 */

public class GameClass {

    //状态
    @SerializedName("status")
    private int status;
    //游戏数据
    @SerializedName("data")
    private List<Game> data;
    //请求信息
    @SerializedName("msg")
    private String msg;

    public GameClass() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Game> getData() {
        return data;
    }

    public void setData(List<Game> data) {
        this.data = data;
    }
}

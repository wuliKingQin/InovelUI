package com.tv.inovelui.other.menu;

import android.app.Fragment;
import android.support.v17.leanback.widget.HeaderItem;

import static android.view.View.NO_ID;

/**
 * 功能描述：菜单项
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/4/18- 17:09
 */

public class MenuItem extends HeaderItem {

    //菜单左图标资源Id
    private int leftIconResId;
    //菜单项对应的碎片
    private Fragment mFragment;

    public MenuItem(String name) {
        this(System.currentTimeMillis(),name);
    }

    public MenuItem(long id, String name) {
        this(id, name,NO_ID);
    }

    public MenuItem(long id, String name,int resId) {
        this(id,name,resId,null);
    }

    public MenuItem(long id, String name, Fragment fragment) {
       this(id, name,NO_ID,fragment);
    }

    public MenuItem(long id, String name, int resId, Fragment fragment) {
        super(id,name);
        leftIconResId = resId;
        mFragment = fragment;
    }

    public int getLeftIconResId() {
        return leftIconResId;
    }

    public void setLeftIconResId(int leftIconResId) {
        this.leftIconResId = leftIconResId;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public void setFragment(Fragment fragment) {
        this.mFragment = fragment;
    }
}

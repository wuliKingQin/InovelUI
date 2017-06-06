package com.tv.inovelui.other.menu;

import android.app.Fragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.DividerRow;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.PageRow;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/4/18- 17:04
 */

public class Menu {

    //适配器
    private ArrayObjectAdapter mAdapter;
    //保存菜单项
    private final List<MenuItem> mMenuItems = new ArrayList<>();

    public Menu(Presenter presenter) {
        mAdapter = new ArrayObjectAdapter(presenter);
    }

    /**
     * 添加菜单项
     * @param name 菜单名
     */
    public void putMenuItem(String name) {
        putMenuItem(System.currentTimeMillis(),name);
    }

    /**
     * 添加菜单项
     * @param id 菜单Id
     * @param name 菜单名
     */
    public void putMenuItem(long id, String name) {
        putMenuItem(id,name,-1);
    }

    /**
     * 添加菜单项
     * @param id 菜单Id
     * @param name 菜单名
     * @param resId 菜单左图标资源Id
     */
    public void putMenuItem(long id, String name, int resId) {
        putMenuItem(id,name,resId,null);
    }

    /**
     * 添加菜单项
     * @param id 菜单Id
     * @param name 菜单名
     * @param fragment 对应的碎片
     */
    public void putMenuItem(long id, String name,Fragment fragment) {
        putMenuItem(id,name,-1,fragment);
    }

    /**
     * 添加菜单项
     * @param name 菜单名
     * @param fragment 对应的碎片
     */
    public void putMenuItem(String name,Fragment fragment) {
        putMenuItem(name,-1,fragment);
    }

    /**
     * 添加菜单项
     * @param name 菜单名
     * @param resId 菜单左图标资源Id
     * @param fragment 对应的碎片
     */
    public void putMenuItem(String name, int resId, Fragment fragment) {
        putMenuItem(System.currentTimeMillis(),name,resId,fragment);
    }

    /**
     * 添加菜单项
     * @param id 菜单Id
     * @param name 菜单名
     * @param resId 菜单左图标资源Id
     * @param fragment 对应的碎片
     */
    public void putMenuItem(long id, String name, int resId, Fragment fragment) {
        if(!contains(name)) {
            final MenuItem menuItem = new MenuItem(id,name,resId,fragment);
            mMenuItems.add(menuItem);
            mAdapter.add(new PageRow(menuItem));
            mAdapter.add(new DividerRow());
        }
    }

    /**
     * 获取菜单项，根据行
     * @param rowItem 行
     * @return 菜单对应的碎片
     */
    public Fragment getItemFragment(Row rowItem) {
        final MenuItem menuItem = getMenuItem(rowItem);
        if(menuItem != null) {
            return menuItem.getFragment();
        }
        return null;
    }


    /**
     * 获取菜单项
     * @param rowItem 行
     * @return 菜单项实例
     */
    public MenuItem getMenuItem(Row rowItem) {
        return getMenuItem(rowItem.getHeaderItem().getName());
    }

    /**
     * 获取菜单项
     * @param name 菜单名
     * @return 菜单项实例
     */
    public MenuItem getMenuItem(String name) {
        for (MenuItem menuItem : mMenuItems) {
            if(TextUtils.equals(menuItem.getName(),name)) {
                return menuItem;
            }
        }
        return null;
    }

    /**
     * 获取菜单数据
     * @return 菜单数据
     */
    public List<MenuItem> getMenu() {
        return mMenuItems;
    }

    /**
     * 判断是否包含菜单项，根据项的名字
     * @param menuItemName 菜单项的名字
     * @return true表示存在，否则不存在
     */
    private boolean contains(String menuItemName) {
        boolean isExist = false;
        for (MenuItem menuItem : mMenuItems) {
            if(TextUtils.equals(menuItem.getName(),menuItemName)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    /**
     * 移除单个菜单
     * @param menuItem 菜单项实例
     */
    public void remove(Object menuItem) {
        if(mMenuItems.contains(menuItem)) {
            mMenuItems.remove(menuItem);
        }
    }

    /**
     * 获取菜单适配器
     * @return 菜单适配器实例
     */
    public ArrayObjectAdapter getAdapter() {
        return mAdapter;
    }

    /**
     * 清除数据
     */
    public void clear() {
        mMenuItems.clear();
    }
}

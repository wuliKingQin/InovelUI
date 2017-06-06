package com.tv.inovelui.other.menu;

import android.app.Fragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.Row;
import java.util.List;

/**
 * 功能描述：菜单管理器
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/4/18- 17:53
 */
public class MenuManager {

    //菜单实例
    private final Menu mMenu;
    //保存菜单管理器实例
    private static MenuManager ourInstance = new MenuManager();
    //获取菜单管理器实例
    public static MenuManager instance() {
        return ourInstance;
    }

    private MenuManager() {
        mMenu = new Menu(new ListRowPresenter());
    }

    /**
     * 添加菜单项
     * @param name 菜单名
     */
    public void putMenuItem(String name) {
        mMenu.putMenuItem(name);
    }

    /**
     * 添加菜单项
     * @param name 菜单名
     * @param fragment 对应的碎片
     */
    public void putMenuItem(String name,Fragment fragment) {
        mMenu.putMenuItem(name,fragment);
    }

    /**
     * 添加菜单项
     * @param name 菜单名
     * @param resId 菜单左图标资源Id
     * @param fragment 对应的碎片
     */
    public void putMenuItem(String name, int resId, Fragment fragment) {
        mMenu.putMenuItem(name,resId,fragment);
    }

    /**
     * 添加菜单项
     * @param id 菜单Id
     * @param name 菜单名
     */
    public void putMenuItem(long id, String name) {
        mMenu.putMenuItem(id,name);
    }

    /**
     * 添加菜单项
     * @param id 菜单Id
     * @param name 菜单名
     * @param resId 菜单左图标资源Id
     */
    public void putMenuItem(long id, String name, int resId) {
        mMenu.putMenuItem(id,name,resId);
    }

    /**
     * 添加菜单项
     * @param id 菜单Id
     * @param name 菜单名
     * @param fragment 对应的碎片
     */
    public void putMenuItem(long id, String name,Fragment fragment) {
        mMenu.putMenuItem(id,name,fragment);
    }

    /**
     * 添加菜单项
     * @param id 菜单Id
     * @param name 菜单名
     * @param resId 菜单左图标资源Id
     * @param fragment 对应的碎片
     */
    public void putMenuItem(long id, String name, int resId, Fragment fragment) {
        mMenu.putMenuItem(id,name,resId,fragment);
    }

    /**
     * 获取菜单项，根据行
     * @param rowItem 行
     * @return 菜单对应的碎片
     */
    public Fragment getItemFragment(Row rowItem) {
        return mMenu.getItemFragment(rowItem);
    }


    /**
     * 获取菜单项
     * @param rowItem 行
     * @return 菜单项实例
     */
    public MenuItem getMenuItem(Row rowItem) {
        return mMenu.getMenuItem(rowItem);
    }

    /**
     * 获取菜单项
     * @param name 菜单名
     * @return 菜单项实例
     */
    public MenuItem getMenuItem(String name) {
        return mMenu.getMenuItem(name);
    }

    /**
     * 获取菜单数据
     * @return 菜单数据
     */
    public List<MenuItem> getMenu() {
        return mMenu.getMenu();
    }

    /**
     * 获取适配器
     * @return 适配器实例
     */
    public ArrayObjectAdapter getAdapter() {
        return mMenu.getAdapter();
    }
}

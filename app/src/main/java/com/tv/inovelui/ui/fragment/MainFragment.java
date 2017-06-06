package com.tv.inovelui.ui.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v17.leanback.app.*;
import android.support.v17.leanback.widget.PageRow;
import android.support.v17.leanback.widget.Row;
import com.tv.inovelui.R;
import com.tv.inovelui.other.menu.MenuManager;
import com.tv.inovelui.ui.activity.MainActivity;
import com.tv.inovelui.ui.view.TvBrowseFragment;

/**
 * 功能描述：主程序界面
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/5/11- 13:54
 */

public class MainFragment extends TvBrowseFragment {

    //菜单
    private MenuManager mMenuManager;
    //背景管理器
    private BackgroundManager mBackgroundMgr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupUi();
        loadData();
        mBackgroundMgr = BackgroundManager.getInstance(getActivity());
        mBackgroundMgr.attach(getActivity().getWindow());
        getMainFragmentRegistry().registerFragment(PageRow.class,
                new MainFragmentFactory(mBackgroundMgr));
    }

    @Override
    public void onResume() {
        super.onResume();
        mBackgroundMgr.setDrawable(getResources().getDrawable(R.mipmap.ic_background));
    }

    /**
     * 初始化UI
     */
    private void setupUi() {
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);
        setBrandColor(getResources().getColor(R.color.setting_background));

        setBrowseTransitionListener(new BrowseTransitionListener() {
            @Override
            public void onHeadersTransitionStart(boolean withHeaders) {
                ((MainActivity)getActivity()).setTimeBackgroundColor(Color.TRANSPARENT);
            }

            @Override
            public void onHeadersTransitionStop(boolean withHeaders) {
                if(withHeaders) {
                    ((MainActivity)getActivity()).setTimeBackgroundColor(getResources().getColor(R.color.setting_background));
                }
                getRowsFragment().setSelectedPosition(0,true);
            }
        });
    }

    /**
     * 导入数据
     */
    private void loadData() {
        mMenuManager = MenuManager.instance();
        setAdapter(mMenuManager.getAdapter());
        createMenu();
        startEntranceTransition();
    }

    /**
     * 创建菜单
     */
    private void createMenu() {
        mMenuManager.putMenuItem("推荐",new RecommendFragment());
        mMenuManager.putMenuItem("视频",new VideoFragment());
        mMenuManager.putMenuItem("游戏",new GameFragment());
        mMenuManager.putMenuItem("应用",new InstalledAppFragment());
        mMenuManager.putMenuItem("多媒体",new MediaFragment());
        mMenuManager.putMenuItem("设置",new SettingFragment());
    }

    /**
     * 用于生成各个碎片界面的工厂
     */
    private class MainFragmentFactory extends TvBrowseFragment.FragmentFactory {

        //背景管理器
        private BackgroundManager mBgManager;

        public MainFragmentFactory(BackgroundManager bgManager) {
            mBgManager = bgManager;
        }

        @Override
        public Fragment createFragment(Object rowObj) {
            mBgManager.setDrawable(getResources().getDrawable(R.mipmap.ic_background));
            return mMenuManager.getItemFragment((Row) rowObj);
        }
    }
}

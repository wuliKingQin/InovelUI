package com.tv.inovelui.ui.fragment;

import android.content.Intent;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;

import com.tv.framework.utils.ToastUtil;
import com.tv.inovelui.R;
import com.tv.inovelui.model.Category;
import com.tv.inovelui.model.IModel;
import com.tv.inovelui.model.Setting;
import com.tv.inovelui.presenter.SettingPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：设置碎片
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/4/19- 10:04
 */

public class SettingFragment extends AbsBaseRowsFragment {


    @Override
    public int getNumRows() {
        return 2;
    }

    @Override
    protected void getCategories(List<Category> categories) {
        if(categories.isEmpty()) {
            Setting setting;
            final List<IModel> settings = new ArrayList<>();
            setting = new Setting();
            setting.setTitle(getString(R.string.setting));
            setting.setDefaultResId(R.mipmap.ic_setting);
            settings.add(setting);

            setting = new Setting();
            setting.setTitle("Wifi");
            setting.setDefaultResId(R.mipmap.ic_wifi);
            settings.add(setting);

            setting = new Setting();
            setting.setTitle(getString(R.string.setting_ble));
            setting.setDefaultResId(R.mipmap.ic_setting_ble);
            settings.add(setting);

            setting = new Setting();
            setting.setTitle(getString(R.string.setting_rectify));
            setting.setDefaultResId(R.mipmap.ic_rectfy);
            settings.add(setting);

            setting = new Setting();
            setting.setTitle(getString(R.string.setting_time));
            setting.setDefaultResId(R.mipmap.ic_settings_time);
            settings.add(setting);

            setting = new Setting();
            setting.setTitle("投影设置");
            setting.setDefaultResId(R.mipmap.ic_projection);
            settings.add(setting);

            setting = new Setting();
            setting.setTitle("未知");
            setting.setDefaultResId(R.mipmap.ic_launcher);
            settings.add(setting);

            setting = new Setting();
            setting.setTitle("未知");
            setting.setDefaultResId(R.mipmap.ic_launcher);
            settings.add(setting);

            categories.add(new Category(settings));
        }
    }

    @Override
    protected Presenter getModelPresenter() {
        return new SettingPresenter(getActivity());
    }

    @Override
    public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        startActivity((Setting) item);
    }

    /**
     * 启动设置各个界面
     * @param setting 设置信息
     */
    private void startActivity(Setting setting) {
        try {
            final Intent setActivity = new Intent();
            setActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            setActivity.setAction(setting.getAction());
            startActivity(setActivity);
        } catch (Exception e) {
            ToastUtil.toast(getActivity(),"接口为对接");
        }
    }

}

package com.tv.inovelui.presenter;

import android.content.Context;
import android.graphics.Color;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tv.inovelui.R;
import com.tv.inovelui.model.InstalledApp;

/**
 * 功能描述：已经安装的app视图控制器
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/5/12- 10:35
 */

public class InstalledAppPresenter extends AbsBasePresenter<InstalledApp> {

    public InstalledAppPresenter(Context context) {
        super(context);
        setItemLayoutResId(R.layout.listview_app_item);
    }

    @Override
    protected ViewHolder onCreateViewHolder(View itemView) {
        return new AppViewHolder(itemView);
    }

    @Override
    protected void onBindViewData(ViewHolder viewHolder, InstalledApp item) {
        final AppViewHolder holder = ((AppViewHolder) viewHolder);
        holder.appIcon.setBackground(item.getAppIcon());
        holder.appName.setText(item.getTitle());
    }

    @Override
    protected void onUnBindViewData(ViewHolder viewHolder) {

    }

    /**
     * 视图支持器
     */
    class AppViewHolder extends Presenter.ViewHolder {

        //app图标
        ImageView appIcon;
        //app名
        TextView appName;

        public AppViewHolder(View view) {
            super(view);
            appIcon = (ImageView) view.findViewById(R.id.iv_app_icon);
            appName = (TextView) view.findViewById(R.id.tv_app_name);
        }
    }
}

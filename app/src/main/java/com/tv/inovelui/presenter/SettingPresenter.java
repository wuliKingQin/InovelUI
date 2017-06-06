package com.tv.inovelui.presenter;

import android.content.Context;
import android.graphics.Color;
import android.support.v17.leanback.widget.Presenter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.tv.inovelui.R;
import com.tv.inovelui.model.Setting;

/**
 * 功能描述：
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/4/19- 17:00
 */

public class SettingPresenter extends AbsBasePresenter<Setting> {

    public SettingPresenter(Context context) {
        super(context);
        setItemLayoutResId(R.layout.listview_app_item);
    }

    @Override
    protected ViewHolder onCreateViewHolder(final View itemView) {
        itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    itemView.setBackgroundColor(getContext().getResources().getColor(R.color.setting_background_focused));
                } else {
                    itemView.setBackgroundColor(getContext().getResources().getColor(R.color.setting_background));
                }
            }
        });
        itemView.setBackgroundColor(getContext().getResources().getColor(R.color.setting_background));
        return new SetViewHolder(itemView);
    }

    @Override
    protected void onBindViewData(ViewHolder viewHolder, Setting item) {
        final SetViewHolder holder = ((SetViewHolder) viewHolder);
        holder.appIcon.setBackgroundResource(item.getDefaultResId());
        holder.appName.setText(item.getTitle());
    }

    @Override
    protected void onUnBindViewData(ViewHolder viewHolder) {
    }

    @Override
    protected int getItemSelectedBackgroundColor(Context context) {
        return Color.TRANSPARENT;
    }

    @Override
    protected int getItemDefaultBackgroundColor(Context context) {
        return Color.TRANSPARENT;
    }

    /**
     * 视图支持器
     */
    class SetViewHolder extends Presenter.ViewHolder {
        //app图标
        ImageView appIcon;
        //app名
        TextView appName;

        public SetViewHolder(View view) {
            super(view);
            appIcon = (ImageView) view.findViewById(R.id.iv_app_icon);
            appName = (TextView) view.findViewById(R.id.tv_app_name);
        }
    }
}

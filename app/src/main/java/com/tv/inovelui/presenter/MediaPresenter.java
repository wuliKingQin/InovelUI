package com.tv.inovelui.presenter;

import android.content.Context;
import android.support.v17.leanback.widget.ImageCardView;
import android.view.View;

import com.tv.inovelui.R;
import com.tv.inovelui.model.Media;

/**
 * 功能描述：
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/4/20- 9:27
 */

public class MediaPresenter extends AbsBasePresenter<Media>{

    public MediaPresenter(Context context) {
        super(context, R.style.RecomCardTheme);
    }

    @Override
    protected ViewHolder onCreateViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    protected void onBindViewData(ViewHolder viewHolder, Media item) {
        final ImageCardView cardView = ((ImageCardView)viewHolder.view);
        cardView.setTitleText(item.getTitle());
        if(item.getWidth() > 0 && item.getHeight() > 0) {
            cardView.setMainImageDimensions(item.getWidth(),item.getHeight());
        }
        cardView.getMainImageView().setBackgroundResource(item.getDefaultResId());
    }

    @Override
    protected void onUnBindViewData(ViewHolder viewHolder) {

    }
}

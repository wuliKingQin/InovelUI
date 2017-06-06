package com.tv.inovelui.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v17.leanback.widget.ImageCardView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.tv.inovelui.R;
import com.tv.inovelui.model.Video;

/**
 * 功能描述：视频视图控制器
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/5/11- 18:58
 */

public class VideoPresenter extends AbsBasePresenter<Video> {

    public VideoPresenter(Context context) {
        super(context, R.style.RecomCardTheme);
    }

    @Override
    protected ViewHolder onCreateViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    protected void onBindViewData(ViewHolder viewHolder, Video item) {
        final ImageCardView cardView = ((ImageCardView) viewHolder.view);
        cardView.setTitleText(item.getTitle());
        cardView.setContentText(item.getDescription());
        if(item.getWidth() > 0 && item.getHeight() > 0) {
            cardView.setMainImageDimensions(item.getWidth(),item.getHeight());
        }
        if(item.getImage() != null) {
            Glide.with(viewHolder.view.getContext())
                    .load(item.getImage())
                    .centerCrop()
                    .placeholder(item.getDefaultResId())
                    .into(cardView.getMainImageView());
        } else {
            cardView.getMainImageView().setBackgroundResource(item.getDefaultResId());
        }
    }

    @Override
    protected void onUnBindViewData(ViewHolder viewHolder) {

    }
}

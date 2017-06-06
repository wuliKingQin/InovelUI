package com.tv.inovelui.presenter;

import android.content.Context;
import android.support.v17.leanback.widget.ImageCardView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.tv.inovelui.R;
import com.tv.inovelui.model.Recommend;

/**
 * 功能描述：推荐视图控制器
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/5/11- 16:51
 */

public class RecommendPresenter extends AbsBasePresenter<Recommend> {

    public RecommendPresenter(Context context) {
        super(context, R.style.RecomCardTheme);
    }

    @Override
    protected ViewHolder onCreateViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    protected void onBindViewData(ViewHolder viewHolder, Recommend item) {
        final ImageCardView cardView = ((ImageCardView) viewHolder.view);
        cardView.setTitleText(item.getTitle());
        cardView.setContentText(item.getDescription());
        if(item.getWidth() > 0 && item.getHeight() > 0) {
            cardView.setMainImageDimensions(item.getWidth(),item.getHeight());
        }
        if(item.getImage() != null) {
            Glide.with(viewHolder.view.getContext())
                    .load(item.getImage())
                    .fitCenter()
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

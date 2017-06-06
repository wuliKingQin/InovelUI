package com.tv.inovelui.ui.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;

import com.tv.framework.utils.ToastUtil;
import com.tv.inovelui.R;
import com.tv.inovelui.model.Category;
import com.tv.inovelui.model.IModel;
import com.tv.inovelui.model.Media;
import com.tv.inovelui.model.Video;
import com.tv.inovelui.presenter.MediaPresenter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：多媒体
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/4/19- 10:04
 */

public class MediaFragment extends AbsBaseRowsFragment {

    @Override
    protected void getCategories(List<Category> categories) {
        if(categories.isEmpty()) {
            List<IModel> medias = new ArrayList<>();
            Media media = new Media();
            media.setTitle("视频");
            media.setWidth(600);
            media.setHeight(738);
            media.setDefaultResId(R.mipmap.ic_media_video);
            medias.add(media);

            media = new Media();
            media.setTitle("音乐");
            media.setWidth(600);
            media.setHeight(738);
            media.setDefaultResId(R.mipmap.ic_media_music);
            medias.add(media);

            media = new Media();
            media.setTitle("图片");
            media.setWidth(600);
            media.setHeight(738);
            media.setDefaultResId(R.mipmap.ic_media_photo);
            medias.add(media);
            categories.add(new Category(medias));
        }
    }

    @Override
    protected Presenter getModelPresenter() {
        return new MediaPresenter(getActivity());
    }

    @Override
    public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        try {
            openFileMgrActivity((Media)item);
        } catch (Exception e) {
            ToastUtil.toast(getActivity(),"打开失败，未安装文件管理app");
        }
    }

    /**
     * 打开文件管理器界面
     * @param media 媒体类型
     */
    protected void openFileMgrActivity(Media media) {
        final Intent tvActivity = new Intent();
        tvActivity.setComponent(media.getMediaComponent());
        tvActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        tvActivity.putExtra("menuName", media.getTitle());
        startActivity(tvActivity);
    }
}

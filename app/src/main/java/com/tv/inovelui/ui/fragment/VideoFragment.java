package com.tv.inovelui.ui.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.text.TextUtils;
import android.util.Log;
import com.tv.framework.utils.ToastUtil;
import com.tv.inovelui.R;
import com.tv.inovelui.http.Api;
import com.tv.inovelui.http.ApiManager;
import com.tv.inovelui.model.Category;
import com.tv.inovelui.model.Video;
import com.tv.inovelui.model.VideoClass;
import com.tv.inovelui.presenter.VideoPresenter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 功能描述：视频碎片
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/5/11- 17:14
 */

public class VideoFragment extends AbsBaseRowsFragment {

    //保存视频
    protected List<Video> mVideos = new ArrayList<>();

    /**
     * 请求网络数据
     */
    protected void requestNetData(int menuId) {
        final Map<String,Object> param = new HashMap<>();
        param.put(Api.TEMPLATE_ID_PARAM,Api.TEMPLATE_ID_VALUE);
        param.put(Api.MENU_ID_PARAM, menuId);
        ApiManager.getInstance().getVideoApi()
                .getVideoList(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideoClass>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                    }

                    @Override
                    public void onNext(VideoClass videoClass) {
                        try {
                            updateDate(videoClass);
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.d("data",throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 更新数据
     * @param videoClass 数据
     */
    protected void updateDate(VideoClass videoClass) throws Exception {
        Video temp;
        final List<Video> data = videoClass.getItemList();
        for(Video video:mVideos) {
            temp = getVideo(data,video.getTitle());
            if(temp != null) {
                video.setDescInfo(temp.getDescInfo());
                video.setAction(temp.getAction());
                video.setActionParam(temp.getActionParam());
                video.setId(temp.getId());
                video.setImage(temp.getImage());
            }
        }
        isRequestSuccess = true;
        getAdapter().notifyItemRangeChanged(0,2);
    }

    /**
     * 寻找对应的视频
     * @param videos 网络请求下来的视频
     * @param title 主题
     * @return 返回主题对应的视图
     */
    private Video getVideo(List<Video> videos,String title) {
        for(Video video:videos) {
            if(TextUtils.equals(video.getDescInfo(), title)) {
                return video;
            }
        }
        return null;
    }

    @Override
    protected void getCategories(List<Category> categories) {
        if(categories.isEmpty()) {
            List<Video> models = new ArrayList<>();
            Video video = new Video();
            video.setWidth(365);
            video.setHeight(454);
            video.setTitle("电影");
            video.setDefaultResId(R.mipmap.ic_video_img_3);
            models.add(video);

            video = new Video();
            video.setWidth(365);
            video.setHeight(454);
            video.setTitle("微电影");
            video.setDefaultResId(R.mipmap.ic_video_img_12);
            models.add(video);

            video = new Video();
            video.setWidth(365);
            video.setHeight(454);
            video.setTitle("动漫");
            video.setDefaultResId(R.mipmap.ic_video_img_7);
            models.add(video);

            video = new Video();
            video.setWidth(365);
            video.setHeight(454);
            video.setTitle("综艺");
            video.setDefaultResId(R.mipmap.ic_video_img_6);
            models.add(video);

            video = new Video();
            video.setWidth(365);
            video.setHeight(454);
            video.setTitle("汽车频道");
            video.setDefaultResId(R.mipmap.ic_video_img_5);
            models.add(video);

            video = new Video();
            video.setWidth(365);
            video.setHeight(454);
            video.setTitle("专题");
            video.setDefaultResId(R.mipmap.ic_video_img_9);
            models.add(video);

            video = new Video();
            video.setWidth(365);
            video.setHeight(454);
            video.setTitle("资讯");
            video.setDefaultResId(R.mipmap.ic_video_img_8);
            models.add(video);
            mVideos.addAll(models);
            categories.add(new Category(models));

            models = new ArrayList<>();
            video = new Video();
            video.setWidth(540);
            video.setHeight(354);
            video.setTitle("电视剧");
            video.setDefaultResId(R.mipmap.ic_video_img_1);
            models.add(video);

            video = new Video();
            video.setWidth(540);
            video.setHeight(354);
            video.setTitle("少儿");
            video.setDefaultResId(R.mipmap.ic_video_img_2);
            models.add(video);

            video = new Video();
            video.setWidth(540);
            video.setHeight(354);
            video.setTitle("纪录片");
            video.setDefaultResId(R.mipmap.ic_video_img_11);
            models.add(video);

            video = new Video();
            video.setWidth(540);
            video.setHeight(354);
            video.setTitle("禅文化");
            video.setDefaultResId(R.mipmap.ic_video_img_4);
            models.add(video);

            video = new Video();
            video.setWidth(540);
            video.setHeight(354);
            video.setTitle("教育");
            video.setDefaultResId(R.mipmap.ic_video_img_10);
            models.add(video);
            mVideos.addAll(models);
            categories.add(new Category(models));
        }
        if(!isRequestSuccess) {
            requestNetData(2);
        }
    }

    @Override
    protected Presenter getModelPresenter() {
        return new VideoPresenter(getActivity());
    }

    @Override
    public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        final Video video = (Video) item;
        if(!TextUtils.isEmpty(video.getDescInfo())) {
            try {
                startPlayActivity(video);
            } catch (Exception e) {
                ToastUtil.toast(getActivity(), "请先安装中华云TV app");
            }
        } else {
            ToastUtil.toast(getActivity(),"网络未连接，请先连接网络");
        }
    }

    /**
     * 打开播放界面
     * @param video 视频
     */
    protected void startPlayActivity(Video video) {
        final Intent tvActivity = new Intent();
        tvActivity.setComponent(new ComponentName("cn.cibntv.ott","cn.cibntv.ott.Bootloader"));
        tvActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        tvActivity.putExtra("action", video.getAction());
        final Map<String,String> actionParam = new HashMap<>();
        actionParam.put("id",video.getId());
        tvActivity.putExtra("actionParam", new JSONObject(actionParam).toString());
        startActivity(tvActivity);
    }
}

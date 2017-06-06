package com.tv.inovelui.ui.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.text.TextUtils;
import com.tv.framework.utils.ToastUtil;
import com.tv.inovelui.R;
import com.tv.inovelui.model.Category;
import com.tv.inovelui.model.Recommend;
import com.tv.inovelui.model.Video;
import com.tv.inovelui.model.VideoClass;
import com.tv.inovelui.presenter.RecommendPresenter;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：推荐碎片
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/5/11- 15:44
 */

public class RecommendFragment extends VideoFragment {

    @Override
    protected void updateDate(VideoClass videoClass) throws Exception {
        Video temp;
        final List<Video> data = videoClass.getItemList();
        int index = 0;
        int[] indexs = new int[]{3,4,6,17};
        for(Video video:mVideos) {
            temp = data.get(indexs[index]);
            video.setDescInfo(temp.getDescInfo());
            video.setAction(temp.getAction());
            video.setActionParam(temp.getActionParam());
            video.setId(temp.getId());
            video.setImage(temp.getImage());
            index += 1;
        }
        isRequestSuccess = true;
        getAdapter().notifyItemRangeChanged(1,1);
    }

    @Override
    protected void getCategories(List<Category> categories) {
        if(categories.isEmpty()) {
            mVideos.clear();
            List<Recommend> models = new ArrayList<>();
            Recommend recommend = new Recommend();
            recommend.setWidth(540);
            recommend.setHeight(354);
            recommend.setTitle("影视卡槽1");
            recommend.setDefaultResId(R.mipmap.ic_recom_img_1);
            models.add(recommend);

            recommend = new Recommend();
            recommend.setWidth(540);
            recommend.setHeight(354);
            recommend.setTitle("影视卡槽2");
            recommend.setDefaultResId(R.mipmap.ic_recom_img_2);
            models.add(recommend);

            recommend = new Recommend();
            recommend.setWidth(540);
            recommend.setHeight(354);
            recommend.setTitle("影视卡槽3");
            recommend.setDefaultResId(R.mipmap.ic_recom_img_3);
            models.add(recommend);

            recommend = new Recommend();
            recommend.setWidth(540);
            recommend.setHeight(354);
            recommend.setTitle("影视卡槽4");
            recommend.setDefaultResId(R.mipmap.ic_recom_img_4);
            models.add(recommend);
            mVideos.addAll(models);
            categories.add(new Category(models));

            models = new ArrayList<>();
            recommend = new Recommend();
            recommend.setWidth(600);
            recommend.setHeight(354);
            recommend.setTitle("HDMI");
            recommend.setDefaultResId(R.mipmap.ic_hdmi);
            models.add(recommend);

            recommend = new Recommend();
            recommend.setWidth(600);
            recommend.setHeight(354);
            recommend.setTitle("同屏");
            recommend.setDefaultResId(R.mipmap.ic_lebo);
            models.add(recommend);

            recommend = new Recommend();
            recommend.setWidth(600);
            recommend.setHeight(354);
            recommend.setPackageName("com.tv.filemanager");
            recommend.setTitle("文件管理器");
            recommend.setDefaultResId(R.mipmap.ic_file_manager);
            models.add(recommend);
            categories.add(new Category(models));
        }
        if(!isRequestSuccess) {
            requestNetData(1);
        }
    }

    @Override
    protected Presenter getModelPresenter() {
        return new RecommendPresenter(getActivity());
    }

    @Override
    public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        final Recommend video = (Recommend) item;
        if(!TextUtils.isEmpty(video.getDescInfo()) && !TextUtils.isEmpty(video.getId())) {
            try {
                startPlayActivity(video);
            } catch (Exception e) {
                ToastUtil.toast(getActivity(), "请先安装中华云TV app");
            }
        } else if(TextUtils.equals(video.getTitle(),"HDMI")){
            ToastUtil.toast(getActivity(),"接口未对接");
        } else if(TextUtils.equals(video.getTitle(),"同屏")) {
            ToastUtil.toast(getActivity(),"接口未对接");
        } else if(TextUtils.equals(video.getTitle(),"文件管理器")) {
            openApp(video);
        } else {
            ToastUtil.toast(getActivity(),"网络未连接，请先连接网络");
        }
    }

    /**
     * 打开app
     * @param recommend 推荐信息
     */
    private void openApp(Recommend recommend) {
        try {
            PackageManager packageManager = getActivity().getPackageManager();
            Intent intent = packageManager.getLaunchIntentForPackage(recommend.getPackageName());
            startActivity(intent);
        } catch (Exception e) {
            ToastUtil.toast(getActivity(),"打开失败");
        }
    }
}

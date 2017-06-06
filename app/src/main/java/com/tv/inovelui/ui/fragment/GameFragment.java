package com.tv.inovelui.ui.fragment;

import android.content.Intent;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.text.TextUtils;

import com.tv.framework.utils.ToastUtil;
import com.tv.inovelui.R;
import com.tv.inovelui.http.Api;
import com.tv.inovelui.http.ApiManager;
import com.tv.inovelui.model.Category;
import com.tv.inovelui.model.Game;
import com.tv.inovelui.model.GameClass;
import com.tv.inovelui.model.Video;
import com.tv.inovelui.model.VideoClass;
import com.tv.inovelui.presenter.GamePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.SerializedObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * 功能描述：游戏碎片
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/5/11- 17:16
 */

public class GameFragment extends AbsBaseRowsFragment {

    //请求数据是否成功
    private boolean isRequestSuccess = false;
    //游戏
    private List<Game> mGames = new ArrayList<>();

    @Override
    protected void getCategories(List<Category> categories) {
       if(categories.isEmpty()) {
           List<Game> models = new ArrayList<>();
           Game game = new Game();
           game.setIndex(2);
           game.setWidth(295);
           game.setHeight(454);
           game.setTitle("游戏卡槽2");
           game.setDefaultResId(R.mipmap.ic_game_img_2);
           models.add(game);

           game = new Game();
           game.setIndex(3);
           game.setWidth(295);
           game.setHeight(454);
           game.setTitle("游戏卡槽3");
           game.setDefaultResId(R.mipmap.ic_game_img_3);
           models.add(game);

           game = new Game();
           game.setIndex(4);
           game.setWidth(295);
           game.setHeight(454);
           game.setTitle("游戏卡槽4");
           game.setDefaultResId(R.mipmap.ic_game_img_4);
           models.add(game);

           game = new Game();
           game.setIndex(5);
           game.setWidth(295);
           game.setHeight(454);
           game.setTitle("游戏卡槽5");
           game.setDefaultResId(R.mipmap.ic_game_img_5);
           models.add(game);

           game = new Game();
           game.setIndex(1);
           game.setWidth(916);
           game.setHeight(454);
           game.setTitle("游戏卡槽1");
           game.setDefaultResId(R.mipmap.ic_game_img_1);
           models.add(game);
           mGames.addAll(models);
           categories.add(new Category(models));

           models = new ArrayList<>();
           game = new Game();
           game.setWidth(600);
           game.setHeight(354);
           game.setTitle("排行榜");
           game.setViewType("2");
           game.setReturnType("back");
           game.setNodeType("RANKTHEME");
           game.setContentId("0");
           game.setDefaultResId(R.mipmap.ic_game_img_rank);
           models.add(game);

           game = new Game();
           game.setWidth(600);
           game.setHeight(354);
           game.setTitle("专区");
           game.setViewType("2");
           game.setReturnType("back");
           game.setNodeType("THEME");
           game.setContentId("0");
           game.setDefaultResId(R.mipmap.ic_game_img_area);
           models.add(game);

           game = new Game();
           game.setWidth(600);
           game.setHeight(354);
           game.setTitle("全部游戏");
           game.setViewType("0");
           game.setReturnType("back");
           game.setNodeType("HOTGAMETHEME");
           game.setContentId("0");
           game.setDefaultResId(R.mipmap.ic_game_img_all_game);
           models.add(game);
           categories.add(new Category(models));
       }
        if(!isRequestSuccess) {
            requestData();
        }
    }

    /**
     * 获取网络数据
     */
    private void requestData() {
        final Map<String,Object> param = new HashMap<>();
        param.put(Api.CHANNE_ID_PARAM,Api.CHANNE_ID_VALUE);
        param.put(Api.FORMAT_PARAM, Api.FORMAT_VALUE);
        param.put(Api.RECOMMEND_ID_PARAM, Api.RECOMMEND_ID_VALUE);
        ApiManager.getInstance().getGameApi()
                .getGameList(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GameClass>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(GameClass gameClass) {
                        try {
                            updateDate(gameClass);
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 更新数据
     * @param gameClass 数据
     */
    protected void updateDate(GameClass gameClass) throws Exception {
        Game temp;
        final List<Game> data = gameClass.getData();
        for(Game game:mGames) {
            temp = getGame(data,game.getIndex());
            if(temp != null) {
                game.setIndex(temp.getIndex());
                game.setBgimage(temp.getBgimage());
                game.setContentId(temp.getContentId());
                game.setNodeType(temp.getNodeType());
                game.setReturnType(temp.getReturnType());
                game.setViewType(temp.getViewType());
            }
        }
        isRequestSuccess = true;
        getAdapter().notifyItemRangeChanged(0,2);
    }

    /**
     * 寻找对应的游戏信息
     * @param games 网络请求下来的游戏信息
     * @param index 索引
     * @return 返回主题对应的视图
     */
    private Game getGame(List<Game> games,int index) {
        for(Game game:games) {
            if(game.getIndex() == index) {
                return game;
            }
        }
        return null;
    }

    @Override
    protected Presenter getModelPresenter() {
        return new GamePresenter(getActivity());
    }

    @Override
    public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        final Game game = (Game) item;
        if(!TextUtils.isEmpty(game.getContentId())) {
            openGame(game);
        } else {
            ToastUtil.toast(getActivity(),"网络未连接，请先连接网络");
        }
    }

    /**
     * 打开游戏界面
     * @param game 参数值
     */
    private void openGame(Game game) {
        try {
            final StringBuilder builder = new StringBuilder();
            builder.append("viewType=");
            builder.append(game.getViewType());
            builder.append("&nodeType=");
            builder.append(game.getNodeType());
            builder.append("&returnType=");
            builder.append(game.getReturnType());
            builder.append("&contentId=");
            builder.append(game.getContentId());
            final Intent gameActivity = new Intent();
            gameActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            gameActivity.putExtra("param", builder.toString());
            gameActivity.setAction("com.tea.tv.room");
            startActivity(gameActivity);
        } catch (Exception e) {
            ToastUtil.toast(getActivity(), "未安装游戏茶餐厅tv app");
        }
    }
}

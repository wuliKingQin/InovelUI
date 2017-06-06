package com.tv.inovelui.http;

import com.tv.inovelui.model.GameClass;
import com.tv.inovelui.model.VideoClass;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * 功能描述：网络请求地址及参数
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/5/15- 13:26
 */

public interface Api {

    //根目录基地址，用于请求CIBN视频信息
    String CIBN_URL = "http://s.epg.ott.cibntv.net/epg/web/v40/";
    //参数模板Id
    String TEMPLATE_ID_PARAM = "templateId";
    //模板id值
    String TEMPLATE_ID_VALUE = "00080000000000000000000000000050";
    //参数菜单Id
    String MENU_ID_PARAM = "menuId";

    //游戏茶餐厅获取地址
    String GAME_URL = "http://portal.tvgame.cibntv.net:8080/";
    //获取游戏请求参数
    String CHANNE_ID_PARAM = "channelid";
    //获取游戏请求参数
    String FORMAT_PARAM = "format";
    //获取游戏请求参数
    String RECOMMEND_ID_PARAM = "recommendid";
    //渠道Id值
    String CHANNE_ID_VALUE = "10183";
    //请格式为json
    String FORMAT_VALUE = "json";
    //推荐Id值
    String RECOMMEND_ID_VALUE = "5653cd65665ed418943b2423";

    /**
     * 请求视频列表
     * @param params 参数
     * @return 返回Observable实例
     */
    @GET("program!getIndexList.action")
    Observable<VideoClass> getVideoList(@QueryMap Map<String, Object> params);

    /**
     * 请求游戏列表
     * @return 返回Observable实例
     */
    @GET("tea_rec_manage/info/get_content_list.action")
    Observable<GameClass> getGameList(@QueryMap Map<String, Object> params);
}

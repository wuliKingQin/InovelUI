package com.tv.inovelui.http;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 功能描述：api管理器，主要创建请求实例
 * 开发状况：正在开发中
 * 开发作者：黎丝军
 * 开发时间：2017/5/15- 17:15
 */
public class ApiManager {

    //api
    private Api mApi;
    //OkHttp客户端
    private OkHttpClient mOkHttpClient;
    //保存唯一的单列实例
    private static ApiManager ourInstance = new ApiManager();
    //获取单列实例
    public static ApiManager getInstance() {
        return ourInstance;
    }

    private ApiManager() {
        initHttp();
    }

    /**
     * 初始化请求Http以及Api实例
     */
    private void initHttp() {
        mOkHttpClient = new OkHttpClient.Builder().build();
    }

    /**
     * 获取API实例
     * @return api实例
     */
    public Api getApi(String rootUrl) {
       final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(rootUrl)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApi = retrofit.create(Api.class);
        return mApi;
    }

    /**
     * 获取当前api
     * @return Api
     */
    public Api getCurrentApi() {
        if(mApi == null) {
            return getApi(Api.CIBN_URL);
        }
        return mApi;
    }

    /**
     * 获取视频Api
     * @return Api
     */
    public Api getVideoApi() {
        return getApi(Api.CIBN_URL);
    }

    /**
     * 获取游戏Api
     * @return Api
     */
    public Api getGameApi() {
        return getApi(Api.GAME_URL);
    }
}

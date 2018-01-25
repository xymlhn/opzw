package com.opzw.service;

import com.opzw.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 文 件 名: ApiManager
 * 创 建 人: Cartman
 * 创建日期: 2017/10/27 16:12
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：API管理类
 */


public class ApiManager {
    private IService mUserApi;

    private static ApiManager sApiManager;
    public static ApiManager getInstence() {
        if (sApiManager == null) {
            synchronized (ApiManager.class) {
                if (sApiManager == null) {
                    sApiManager = new ApiManager();
                }
            }
        }
        return sApiManager;
    }

    /**
     * 封装配置API
     * @return
     */
    public IService getService(){
        if (mUserApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BuildConfig.SERVER_URL)
                    .addConverterFactory(CGsonConverterFactory.create())
                    .build();

            mUserApi = retrofit.create(IService.class);
        }
        return mUserApi;
    }
}

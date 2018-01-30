package com.opzw.service;


import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * 文 件 名: CallbackWrapper
 * 创 建 人: Cartman
 * 创建日期: 2017/10/30 09:12
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public abstract class CallbackWrapper<T> extends DisposableObserver<T> {



    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable t) {
        if(t instanceof ApiException){
            onFail(t.getMessage());
        }else if (t instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) t).response().errorBody();
            onFail(getErrorMessage(responseBody));
        } else if (t instanceof SocketTimeoutException) {
            onFail("连接超时");
        } else if (t instanceof IOException) {
            onFail("连接超时");
        }else {
            onFail("服务器发生未知错误");
        }
    }

    @Override
    public void onComplete() {
    }

    protected abstract void onSuccess(T t);

    protected abstract void onFail(String t);

    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}


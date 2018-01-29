package com.opzw.login.model;

import com.google.gson.Gson;
import com.opzw.App;
import com.opzw.bean.Result;
import com.opzw.login.model.bean.Token;
import com.opzw.login.model.bean.User;
import com.opzw.service.ApiManager;
import com.opzw.service.CallbackWrapper;
import com.opzw.utils.LogUtils;
import com.opzw.utils.SharedPrefUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 文 件 名: UserModel
 * 创 建 人: Cartman
 * 创建日期: 2017/10/31 13:30
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class UserModel implements IUserModel {
    public UserModel() {
    }

    @Override
    public void login(final Map<String, Object> param, CallbackWrapper<Result<Token>> callbackWrapper) {
        //先请求获取token然后获取user
        ApiManager.getInstence().getService().login(param)
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<Result<Token>>() {
                    @Override
                    public void accept(Result<Token> tokenResult) throws Exception {
                        //缓存token
                        SharedPrefUtils.setParam(App.getContext(),SharedPrefUtils.TOKEN, new Gson().toJson(tokenResult.getData()));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(callbackWrapper);
    }

}
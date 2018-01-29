package com.opzw.login.presenter;


import com.opzw.base.IBasePresenter;
import com.opzw.bean.Result;
import com.opzw.login.model.bean.Token;
import com.opzw.login.view.ILoginView;
import com.opzw.login.model.IUserModel;
import com.opzw.login.model.UserModel;
import com.opzw.login.model.bean.User;
import com.opzw.service.CallbackWrapper;

import java.io.File;
import java.util.Map;


/**
 * 文 件 名: UserPresenter
 * 创 建 人: Cartman
 * 创建日期: 2017/10/31 13:41
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class UserPresenter implements IBasePresenter {
    private ILoginView iLoginView;
    private IUserModel userModel;

    public UserPresenter(ILoginView iLoginView) {
        userModel = new UserModel();
        this.iLoginView = iLoginView;
    }

    public void login(Map<String,Object> params){
        iLoginView.showLoading();
        userModel.login(params, new CallbackWrapper<Result<Token>>(iLoginView) {
            @Override
            protected void onSuccess(Result<Token> tokenResult) {
                iLoginView.onLoginSuccess(tokenResult.getData());
                iLoginView.hideLoading();
            }
        });
    }

}

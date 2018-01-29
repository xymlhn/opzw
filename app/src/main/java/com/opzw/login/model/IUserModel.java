package com.opzw.login.model;

import com.opzw.bean.Result;
import com.opzw.login.model.bean.Token;
import com.opzw.login.model.bean.User;
import com.opzw.service.CallbackWrapper;

import java.io.File;
import java.util.Map;

/**
 * 文 件 名: IUserModel
 * 创 建 人: Cartman
 * 创建日期: 2017/10/31 13:28
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public interface IUserModel {
    void login(Map<String, Object> param, CallbackWrapper<Result<Token>> callbackWrapper);
}

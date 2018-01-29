package com.opzw.login.view;


import com.opzw.base.IBaseView;
import com.opzw.login.model.bean.Token;
import com.opzw.login.model.bean.User;

/**
 * 文 件 名: ILoginView
 * 创 建 人: Cartman
 * 创建日期: 2017/10/31 14:35
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public interface ILoginView extends IBaseView {
    void onLoginSuccess(Token user);
}

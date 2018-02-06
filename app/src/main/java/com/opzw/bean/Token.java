package com.opzw.bean;

/**
 * 文 件 名: Token
 * 创 建 人: Cartman
 * 创建日期: 2017/10/31 09:02
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class Token{
    private String token;

    public Long getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Long expireAt) {
        this.expireAt = expireAt;
    }

    private Long expireAt;

    public String getToken() {

        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

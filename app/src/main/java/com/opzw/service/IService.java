package com.opzw.service;



import com.opzw.bean.CarModel;
import com.opzw.bean.Company;
import com.opzw.bean.Parts;
import com.opzw.bean.Result;
import com.opzw.bean.Token;
import com.opzw.bean.User;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 文 件 名: IService
 * 创 建 人: Cartman
 * 创建日期: 2017/10/27 14:52
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public interface IService {
    @POST("/isp/port/user-login-new/")
    @FormUrlEncoded
    Observable<Result<Token>> login(@FieldMap Map<String,Object> map);

    @GET("/isp/port/get-user-details-new/")
    Observable<Result<User>> getUser();

    @GET("/isp/port/get-company-details-new/")
    Observable<Result<Company>> getCompany();

    @GET("/isp/port/new/get-parts-filter/")
    Observable<Result<Parts>> getPartFilter();

    @POST("/isp/port/new/isp-to-do3/")
    Observable<Result<Parts>> payNotice(@FieldMap Map<String,Object> map);
}

package com.opzw.bean;

import java.util.List;

/**
 * 文 件 名: Parts
 * 创 建 人: Cartman
 * 创建日期: 2018/2/8 14:28
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class Parts {
    private Integer c1;
    private Integer c2;
    private Integer c3;
    private List<CarModel> carCustomerList;

    public Integer getC1() {
        return c1;
    }

    public void setC1(Integer c1) {
        this.c1 = c1;
    }

    public Integer getC2() {
        return c2;
    }

    public void setC2(Integer c2) {
        this.c2 = c2;
    }

    public Integer getC3() {
        return c3;
    }

    public void setC3(Integer c3) {
        this.c3 = c3;
    }

    public List<CarModel> getCarCustomerList() {
        return carCustomerList;
    }

    public void setCarCustomerList(List<CarModel> carCustomerList) {
        this.carCustomerList = carCustomerList;
    }
}

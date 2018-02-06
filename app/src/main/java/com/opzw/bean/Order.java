package com.opzw.bean;

import java.util.Date;

/**
 * 文 件 名: Order
 * 创 建 人: Cartman
 * 创建日期: 2018/2/2 09:33
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class Order {

    private String purOrderCode;

    private String orderCode;

    //item.deliveryMethod == 30? '现货' : '期货'
    private Integer deliveryMethod;

    //(item.purOrderStatus == 20 || item.purOrderStatus == 30 || item.purOrderStatus == 40)?
    // '已生效' : item.purOrderStatus == 50? '已发货' : item.purOrderStatus == 90? '已撤销' : '待生效'
    // <!-- 状态传参 code: '10', name:'待生效';code: '20', name:'已生效';code: '30', name:'发货完成';code: '40', name:'已撤销' -->
    private Integer purOrderStatus;

    private Date createDatetime;

    private Double purOrderWeight;

    private Integer status;

    private Double totalWeight;

    private Double orderAmt;

    private String providerName;

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public Double getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(Double orderAmt) {
        this.orderAmt = orderAmt;
    }

    public String getPurOrderCode() {
        return purOrderCode;
    }

    public void setPurOrderCode(String purOrderCode) {
        this.purOrderCode = purOrderCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(Integer deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public Integer getPurOrderStatus() {
        return purOrderStatus;
    }

    public void setPurOrderStatus(Integer purOrderStatus) {
        this.purOrderStatus = purOrderStatus;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Double getPurOrderWeight() {
        return purOrderWeight;
    }

    public void setPurOrderWeight(Double purOrderWeight) {
        this.purOrderWeight = purOrderWeight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }
}

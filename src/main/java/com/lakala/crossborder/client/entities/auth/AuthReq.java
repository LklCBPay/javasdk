package com.lakala.crossborder.client.entities.auth;

import java.io.Serializable;

/**
 * Created by jiang on 2017/1/3.
 * 认证请求参数
 */
public class AuthReq implements Serializable {
    private static final long serialVersionUID = 6764623440299162418L;

    /**
     * 商户订单号,不可重复
     */
    private String orderNo;

    /**
     * 认证类型
     * 00：身份认证
     * 01：银行卡三要素认证
     * 02：银行卡四要素认证
     */
    private String bizType;

    /**
     * 姓名
     */
    private String name;

    /**
     * 证件号
     */
    private String certNo;

    /**
     * 证件类型:目前支持00-身份证
     */
    private String certType="00";

    /**
     * 银行卡号,bizType为01,02时必填
     */
    private String bankCardNo;

    /**
     * 银行预留手机号,bizType为01,02时必填
     */
    private String bankMobile;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankMobile() {
        return bankMobile;
    }

    public void setBankMobile(String bankMobile) {
        this.bankMobile = bankMobile;
    }

}



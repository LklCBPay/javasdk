package com.lakala.crosspay.client.entities;

import java.io.Serializable;

/**
 * <p>
 * <b>拉卡拉网络支付请求超类</b>
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class LklCrossPaySuperReq implements Serializable {
    private static final long serialVersionUID = 2755358811399044775L;

    /**
     * 报文协议版本号,默认为1.0.0
     */
    private String ver = "1.0.0";
    /**
     * 商户号
     */
    private String merId;
    /**
     * 1.0.0版本报文-支付类型
     */
    private String payTypeId;

    /**
     * 请求业务类型
     */
    private String reqType;

    /**
     * 时间戳，yyyyMMddHHmmss+6位不重复随机数或序列
     */
    private String ts;

    public String getPayTypeId() {
        return payTypeId;
    }

    public void setPayTypeId(String payTypeId) {
        this.payTypeId = payTypeId;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }
}

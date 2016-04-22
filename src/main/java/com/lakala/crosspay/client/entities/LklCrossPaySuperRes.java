package com.lakala.crosspay.client.entities;

import java.io.Serializable;

/**
 * 拉卡拉网络支付响应超类
 * Created by jiang on 16/4/11.
 */
public class LklCrossPaySuperRes implements Serializable {

    private static final long serialVersionUID = -4816678203922035419L;

    private String ver;

    private String merId;

    private String payTypeId;

    private String reqType;

    private String retCode;

    private String retMsg;

    private String ts;

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getPayTypeId() {
        return payTypeId;
    }

    public void setPayTypeId(String payTypeId) {
        this.payTypeId = payTypeId;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LklCrossPaySuperRes{");
        sb.append("ver='").append(ver).append('\'');
        sb.append(", merId='").append(merId).append('\'');
        sb.append(", payTypeId='").append(payTypeId).append('\'');
        sb.append(", reqType='").append(reqType).append('\'');
        sb.append(", retCode='").append(retCode).append('\'');
        sb.append(", retMsg='").append(retMsg).append('\'');
        sb.append(", ts='").append(ts).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

package com.lakala.crossborder.client.entities.batchTrade;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 批量交易查询请求参数
 * Created by jiang on 2016/11/1.
 */
public class BatchBizQueryReq implements Serializable {

    private static final long serialVersionUID = 4246061372832751309L;

    /**
     * 批量交易令牌
     */
    @SerializedName("token")
    private String bizToekn;

    /**
     * 批量文件名。文件名与令牌二者可任选其一,或都填写
     */
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBizToekn() {
        return bizToekn;
    }

    public void setBizToekn(String bizToekn) {
        this.bizToekn = bizToekn;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BatchBizQueryReq{");
        sb.append("bizToekn='").append(bizToekn).append('\'');
        sb.append(", fileName='").append(fileName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

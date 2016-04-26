package com.lakala.crossborder.client.entities.query;

import com.lakala.crossborder.client.enums.CertType;

import java.io.Serializable;

/**
 * <p>
 * 签约查询请求参数
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class SignQueryReq implements Serializable {

    private static final long serialVersionUID = 2846694541428300315L;

    /**
     * 签约卡号
     */
    private String cardNo;

    /**
     * 持卡人姓名
     */
    private String clientName;

    /**
     * 证件类型 {@link CertType}
     */
    private String certType;

    /**
     * 证件号
     */
    private String clientId;

    /**
     * 银行预留手机号
     */
    private String mobile;

    private String ext1;

    private String ext2;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SignQueryReq{");
        sb.append("cardNo='").append(cardNo).append('\'');
        sb.append(", clientName='").append(clientName).append('\'');
        sb.append(", certType='").append(certType).append('\'');
        sb.append(", clientId='").append(clientId).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", ext1='").append(ext1).append('\'');
        sb.append(", ext2='").append(ext2).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

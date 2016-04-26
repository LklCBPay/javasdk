package com.lakala.crossborder.client.entities;

/**
 * <p>
 * 拉卡拉跨境支付加密请求报文对象
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class LklCrossPayEncryptReq extends LklCrossPaySuperReq {

    private static final long serialVersionUID = 1182200158599407155L;

    /**
     * 对称密钥
     */
    private String encKey;

    /**
     * 加密业务数据
     */
    private String encData;

    private String mac;


    public String getEncKey() {
        return encKey;
    }

    public void setEncKey(String encKey) {
        this.encKey = encKey;
    }

    public String getEncData() {
        return encData;
    }

    public void setEncData(String encData) {
        this.encData = encData;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LklCrossPayEncryptReq{");
        sb.append("encKey='").append(encKey).append('\'');
        sb.append(", encData='").append(encData).append('\'');
        sb.append(", mac='").append(mac).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

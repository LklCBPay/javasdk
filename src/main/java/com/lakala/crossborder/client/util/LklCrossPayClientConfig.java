package com.lakala.crossborder.client.util;

/**
 * <p>
 * 拉卡拉跨境支付客户端sdkconfig。配置环境对应的server、port以及配套密钥
 * </p>
 */
public class LklCrossPayClientConfig {

    /**
     * 服务器
     */
    private String server;
    /**
     * 端口
     */
    private String port;

    /**
     * 公钥
     */
    private String publicKey;
    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 拉卡拉注册商户商户号
     */
    private String merId;


    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LklCrossPayClientConfig{");
        sb.append("server='").append(server).append('\'');
        sb.append(", port='").append(port).append('\'');
        sb.append(", publicKey='").append(publicKey).append('\'');
        sb.append(", privateKey='").append(privateKey).append('\'');
        sb.append(", merId='").append(merId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

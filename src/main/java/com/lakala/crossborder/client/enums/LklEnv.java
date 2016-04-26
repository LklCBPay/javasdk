package com.lakala.crossborder.client.enums;

/**
 * <p>
 * 拉卡拉跨境支付环境枚举
 * </p>
 */
public enum LklEnv {

    SANDBOX("sandbox"),
    LIVE("live");

    LklEnv(String env) {
        this.env = env;
    }

    private String env;

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }
}

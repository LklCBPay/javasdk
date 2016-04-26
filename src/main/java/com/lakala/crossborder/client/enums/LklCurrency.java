package com.lakala.crossborder.client.enums;

/**
 * <p>
 * 拉卡拉跨境支付币种定义
 * </p>
 */
public enum LklCurrency {
    USD("USD", "美元"),
    HKD("HKD", "港币"),
    EUR("EUR", "欧元"),
    JPY("JPY", "日元"),
    GBP("GBP", "英镑"),
    CHF("CHF", "瑞士法郎"),
    SGD("SGD", "新加坡元"),
    AUD("AUD", "澳元"),
    CAD("CAD", "加元"),
    KRW("KRW", "韩元"),
    MYR("MYR", "马来西亚林吉特"),
    NZD("NZD", "新西兰"),
    RUB("RUB", "卢布"),
    THB("THB", "泰铢"),
    TWD("TWD", "新台币"),
    CNY("CNY", "人民币");

    LklCurrency(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;

    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

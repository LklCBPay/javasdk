package com.lakala.crossborder.client.enums;

/**
 * <p>
 * 跨境业务类型
 * </p>
 */
public enum CrossBorderBizType {
    HOTEL("223029", "酒店住宿、旅游服务"),
    AIRPORT_TICKET("222024", "航空机票"),
    CARGO_TRADE("121010", "货物贸易"),
    EXPO("228025", "国际展览"),
    STUDY_ABROAD_YEAR_BELOW("223023", "留学教育（一年及以下）"),
    STUDY_ABROAD_YEAR_ABOVE("223022", "留学教育（一年以上）");

    CrossBorderBizType(String code, String desc) {
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

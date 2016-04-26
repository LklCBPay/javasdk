package com.lakala.crossborder.client.enums;

/**
 * <p>
 * 证件类型
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public enum CertType {

    ID("01", "身份证"),
    PASSPORT("03", "护照"),
    HUIXIANGZHENG("04", "港澳回乡证"),
    TAIBAOZHENG("05", "台胞证"),
    HUKOUBU("07", "户口簿"),
    JUNGUANZHENG("27", "军官证"),
    ORG_CODE("51", "组织机构代码"),
    BIZ_LICENCE("55", "企业法人营业执照");


    CertType(String code, String desc) {
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

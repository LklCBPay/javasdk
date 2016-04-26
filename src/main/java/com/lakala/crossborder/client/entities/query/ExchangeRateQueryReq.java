package com.lakala.crossborder.client.entities.query;

import com.lakala.crossborder.client.enums.LklCurrency;

import java.io.Serializable;

/**
 * <p>拉卡拉跨境支付-汇率查询请求业务参数</p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class ExchangeRateQueryReq implements Serializable {

    private static final long serialVersionUID = 5304840602533342810L;

    /**
     * 币种 {@link LklCurrency}
     */
    private String currency;

    private String ext1;

    private String ext2;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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
        final StringBuffer sb = new StringBuffer("ExchangeRateQueryReq{");
        sb.append("currency='").append(currency).append('\'');
        sb.append(", ext1='").append(ext1).append('\'');
        sb.append(", ext2='").append(ext2).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

package com.lakala.crosspay.client.entities.query;

import com.lakala.crosspay.client.enums.LklCurrency;

import java.io.Serializable;

/**
 * <p>
 * 拉卡拉跨境支付-商户代付账户余额查询请求参数
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class AgentPayAcctBalanceQueryReq implements Serializable {

    private static final long serialVersionUID = -6981545435426441159L;

    /**
     * 币种，目前只支持人民币 {@link com.lakala.crosspay.client.enums.LklCurrency}
     */
    private String currency = LklCurrency.CNY.getCode();

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

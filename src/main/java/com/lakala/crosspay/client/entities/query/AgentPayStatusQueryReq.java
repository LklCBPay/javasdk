package com.lakala.crosspay.client.entities.query;

import java.io.Serializable;

/**
 * <p>
 * 拉卡拉跨境支付-单笔实时代付状态查询请求参数
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class AgentPayStatusQueryReq implements Serializable {

    /**
     * 商户订单号
     */
    private String merOrderId;

    public String getMerOrderId() {
        return merOrderId;
    }

    public void setMerOrderId(String merOrderId) {
        this.merOrderId = merOrderId;
    }
}

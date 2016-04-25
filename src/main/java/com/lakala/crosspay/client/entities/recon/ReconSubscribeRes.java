package com.lakala.crosspay.client.entities.recon;

import com.lakala.crosspay.client.entities.LklCrossPaySuperRes;

/**
 * <p>
 * 拉卡拉跨境支付-对账文件预约响应参数
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class ReconSubscribeRes extends LklCrossPaySuperRes {

    private static final long serialVersionUID = -3576788035004252867L;

    /**
     * 开始日期,格式yyyyMMdd
     */
    private String startDate;

    /**
     * 结束日期，格式yyyyMMdd
     */
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ReconSubscribeRes{");
        sb.append("super=").append(super.toString());
        sb.append("startDate='").append(startDate).append('\'');
        sb.append(", endDate='").append(endDate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

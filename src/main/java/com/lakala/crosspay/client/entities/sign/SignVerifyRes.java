package com.lakala.crosspay.client.entities.sign;

import com.lakala.crosspay.client.entities.LklCrossPaySuperRes;

/**
 * <p>
 * 拉卡拉跨境支付-签约验证响应参数
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class SignVerifyRes extends LklCrossPaySuperRes {

    private static final long serialVersionUID = -159828328952989472L;

    /**
     * 银行预留手机
     */
    private String mobile;

    /**
     * 签约状态
     * <ul>
     * <li>1-已签约或签约成功</li>
     * <li>2-审批中</li>
     * <li>9-签约失败</li>
     * <li>0-已解约</li>
     * </ul>
     * retCode为非0000，agstat返回1时，表示用户已签约
     * retCode为0000，agstat返回1时，表示用户签约成功
     */
    private String agstat;

    private String ext1;

    private String ext2;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAgstat() {
        return agstat;
    }

    public void setAgstat(String agstat) {
        this.agstat = agstat;
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
        final StringBuffer sb = new StringBuffer("SignVerifyRes{");
        sb.append("super=").append(super.toString());
        sb.append("mobile='").append(mobile).append('\'');
        sb.append(", agstat='").append(agstat).append('\'');
        sb.append(", ext1='").append(ext1).append('\'');
        sb.append(", ext2='").append(ext2).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

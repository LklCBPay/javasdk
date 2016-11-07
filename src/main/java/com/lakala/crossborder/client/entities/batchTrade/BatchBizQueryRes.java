package com.lakala.crossborder.client.entities.batchTrade;

import com.lakala.crossborder.client.entities.LklCrossPaySuperRes;

/**
 * Created by jiang on 2016/11/1.
 */
public class BatchBizQueryRes extends LklCrossPaySuperRes {

    private static final long serialVersionUID = -5349190787591915374L;

    /**
     * 00-商户送盘成功,交易已受理
     * 01-交易处理中
     * 02-已生成回盘文件,无状态未知交易
     * 03-已生成回盘文件,但补回盘文件未生成,存在状态未知交易
     * 04-全部回盘文件生成成功
     * 05-商户送盘文件名错误
     * 06-商户送盘文件头格式错误
     * 07-商户送盘文件体格式错误
     * 08-重复的交易,该文件名已经存在
     * 09-商户送盘文件摘要未通过验证
     * 10-文件内容有误-交易总金额不正确
     * 11-文件内容有误-交易总笔数不正确
     * 12-ip异常
     * 99-系统异常
     */
    private String bizResult;


    public String getBizResult() {
        return bizResult;
    }

    public void setBizResult(String bizResult) {
        this.bizResult = bizResult;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BatchBizQueryRes{");
        sb.append("super='").append(super.toString()).append('\'');
        sb.append(", bizResult='").append(bizResult).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

package com.lakala.crossborder.client.entities.batchTrade;

import com.lakala.crossborder.client.entities.LklCrossPaySuperRes;

/**
 * 批量文件上传响应参数
 * Created by jiang on 2016/10/25.
 */
public class BatUploadFileRes extends LklCrossPaySuperRes {

    private static final long serialVersionUID = 6474794926023025286L;

    private String sign;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BatUploadFileRes{");
        sb.append("sign='").append(sign).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

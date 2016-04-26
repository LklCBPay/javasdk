package com.lakala.crossborder.client.util.webhook;

import com.lakala.crossborder.client.entities.LklCrossPayEncryptReq;
import com.lakala.crossborder.client.entities.LklCrossPayEncryptRes;

/**
 * <p>
 * 拉卡拉跨境支付webhook接口
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public interface LklWebHookIntf<T extends LklCrossPayEncryptRes, E extends LklCrossPayEncryptReq> {

    /**
     * 响应拉卡拉回调
     *
     * @param notify 拉卡拉回调参数
     * @return
     */
    T proceed(E notify);
}

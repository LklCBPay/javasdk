package com.lakala.crossborder.client.util.webhook;


/**
 * 处理拉卡拉通知<em>商户需根据自己业务实现该接口</em>
 *
 * @param <T>
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public interface WebHookHandler<T extends SuperWebHookRequest> {

    /**
     * 处理拉卡拉通知
     *
     * @param notifyMsg
     * @return
     * @throws Exception
     */
    void handle(T notifyMsg) throws Exception;
}

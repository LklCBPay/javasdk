package com.lakala.crossborder.client.util.webhook.payresult;

import com.lakala.crossborder.client.entities.LklCrossPayEncryptReq;
import com.lakala.crossborder.client.entities.LklCrossPayEncryptRes;
import com.lakala.crossborder.client.entities.notify.PayResultNotify;
import com.lakala.crossborder.client.entities.notify.PayResultNotifyResponse;
import com.lakala.crossborder.client.util.DateUtil;
import com.lakala.crossborder.client.util.webhook.LklWebHookIntf;
import com.lakala.crossborder.client.util.webhook.WebHookHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * <em>处理拉卡拉支付结果回调</em>
 * </p>
 */
@RestController
public class PayResultWebHook implements LklWebHookIntf<LklCrossPayEncryptRes, LklCrossPayEncryptReq> {

    private static final Logger logger = LoggerFactory.getLogger(PayResultWebHook.class);

    @Autowired(required = false)
    @Qualifier("payResultHandle")
    private WebHookHandler<PayResultNotifyResponse, PayResultNotify> webHookIntf;

    /**
     * 响应拉卡拉支付通知回调
     *
     * @param notify 拉卡拉支付结果通知
     * @return
     */
    @RequestMapping(value = "/payResult/handle", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public LklCrossPayEncryptRes proceed(@RequestBody LklCrossPayEncryptReq notify) {
        logger.debug("entering method proceed,req={}", notify.toString());
        PayResultNotify payResultNotify = null;
        LklCrossPayEncryptRes res = new LklCrossPayEncryptRes();

        try {
            webHookIntf.handle(payResultNotify);
            res.setMerId("111111、1");
            res.setTs(DateUtil.getCurrentTime());
            res.setReqType("B0005");
            res.setRetCode("0000");
            res.setVer("1.0.0");

            logger.debug("exiting method proceed,res ={}", res.toString());
            return res;
        } catch (Exception e) {
            logger.error("payResult error", e);
        }

        return null;
    }
}

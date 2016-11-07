package com.lakala.crossborder.client.util.webhook.payresult;

import com.lakala.crossborder.client.entities.LklCrossPayEncryptReq;
import com.lakala.crossborder.client.entities.LklCrossPayEncryptRes;
import com.lakala.crossborder.client.entities.notify.PayResultNotify;
import com.lakala.crossborder.client.entities.notify.PayResultNotifyResponse;
import com.lakala.crossborder.client.exception.LklCommonException;
import com.lakala.crossborder.client.exception.LklEncryptException;
import com.lakala.crossborder.client.util.LklMsgUtil;
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
    @Qualifier("lklpayResultHandle")
    private WebHookHandler<PayResultNotify> webHookIntf;

    /**
     * 响应拉卡拉支付通知回调
     *
     * @param notify 拉卡拉支付结果通知
     * @return
     */
    @RequestMapping(value = "/lklpayResult/handle", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public LklCrossPayEncryptRes proceed(@RequestBody LklCrossPayEncryptReq notify) {
        logger.debug("entering method proceed,req={}", notify.toString());
        PayResultNotify payResultNotify = null;
        LklCrossPayEncryptRes res = new LklCrossPayEncryptRes();
        res.setMerId(notify.getMerId());
        res.setTs(notify.getTs());
        res.setReqType("B0005");
        res.setRetCode("0000");
        res.setVer("1.0.0");

        try {
            payResultNotify = LklMsgUtil.decryptMsgFromLkl(notify, PayResultNotify.class);
            webHookIntf.handle(payResultNotify);
            PayResultNotifyResponse response = new PayResultNotifyResponse();
            response.setMerOrderId(payResultNotify.getMerOrderId());
            response.setTransactionId(payResultNotify.getTransactionId());

            res = LklMsgUtil.encryptWebHookMsg(response, res);
        } catch (LklCommonException e) {
            logger.error("lakala batch trade error", e);
            res.setRetCode("9999");
            res.setRetMsg(e.getMessage());
        } catch (LklEncryptException e) {
            logger.error("lakala batch trade error", e);
            res.setRetCode("9999");
            res.setRetMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("lakala batch trade error", e);
            res.setRetCode("9999");
            res.setRetMsg("系统异常");
        }
        logger.debug("exiting method proceed,res ={}", res.toString());
        return res;
    }
}

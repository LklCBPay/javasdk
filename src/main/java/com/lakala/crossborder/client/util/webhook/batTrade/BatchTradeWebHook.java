package com.lakala.crossborder.client.util.webhook.batTrade;

import com.lakala.crossborder.client.entities.LklCrossPayEncryptReq;
import com.lakala.crossborder.client.entities.LklCrossPayEncryptRes;
import com.lakala.crossborder.client.entities.webHook.BatchTradeNotify;
import com.lakala.crossborder.client.entities.webHook.BatchTradeNotifyRes;
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
 * 批量交易webhook
 * Created by jiang on 2016/10/25.
 */
@RestController
public class BatchTradeWebHook implements LklWebHookIntf<LklCrossPayEncryptRes, LklCrossPayEncryptReq> {
    private static final Logger logger = LoggerFactory.getLogger(BatchTradeWebHook.class);

    @Autowired(required = false)
    @Qualifier("lklBatTradeWebHook")
    private WebHookHandler<BatchTradeNotify> webHookIntf;


    /**
     * 响应拉卡拉批量交易回调
     *
     * @param notify 拉卡拉回调参数
     * @return
     */
    @RequestMapping(value = "/lklBatTrade/webHook", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public LklCrossPayEncryptRes proceed(@RequestBody LklCrossPayEncryptReq notify) {
        logger.debug("entering method proceed,req={}", notify.toString());
        //BatchTradeNotify为解密后的回调报文
        BatchTradeNotify batchTradeNotify = null;
        //LklCrossPayEncryptRes 为响应拉卡拉回调的密文报文类
        LklCrossPayEncryptRes res = new LklCrossPayEncryptRes();
        //BatchTradeNotifyRes 为响应拉卡拉回调的明文报文类
        BatchTradeNotifyRes response = new BatchTradeNotifyRes();

        res.setMerId(notify.getMerId());
        res.setTs(notify.getTs());
        res.setReqType("B0005");
        res.setRetCode("0000");
        res.setVer("3.0.0");

        try {
            //解密拉卡拉回调报文，生成明文报文对象
            batchTradeNotify = LklMsgUtil.decryptMsgFromLkl(notify, BatchTradeNotify.class);
            logger.info("decrypted msgis {}", batchTradeNotify.toString());
            if (null != batchTradeNotify.getNonce() && !"".equals(batchTradeNotify.getNonce())) {
                //该消息为注册回调地址时发送的消息，不进行业务处理，返回随机数即可
                String nonce = batchTradeNotify.getNonce();
                response.setNonce(nonce);
            }
            //否则需根据消息内容处理自己的业务
            else {
                batchTradeNotify.setMerchantId(notify.getMerId());
                webHookIntf.handle(batchTradeNotify);
            }
            logger.info("response is {}", response.toString());
            //加密明文响应报文
            res = LklMsgUtil.encryptWebHookMsg(response, res);
        } catch (LklCommonException e) {
            logger.error("lakala batch trade webhook error", e);
            res.setRetCode("9999");
            res.setRetMsg(e.getMessage());
        } catch (LklEncryptException e) {
            logger.error("lakala batch trade webhook error", e);
            res.setRetCode("9999");
            res.setRetMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("lakala batch trade webhook error", e);
            res.setRetCode("9999");
            res.setRetMsg("系统异常");
        }

        logger.debug("exiting method proceed,res ={}", res.toString());
        return res;
    }

}

package com.lakala.crossborder.client.util.webhook.customAuthResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lakala.crossborder.client.entities.LklCrossPayEncryptReq;
import com.lakala.crossborder.client.entities.LklCrossPayEncryptRes;
import com.lakala.crossborder.client.entities.webHook.CustomAuthNotify;
import com.lakala.crossborder.client.entities.webHook.CustomAuthResultNotifyRes;
import com.lakala.crossborder.client.enums.LklEnv;
import com.lakala.crossborder.client.exception.LklCommonException;
import com.lakala.crossborder.client.exception.LklEncryptException;
import com.lakala.crossborder.client.util.LklCrossPayEnv;
import com.lakala.crossborder.client.util.LklMsgUtil;
import com.lakala.crossborder.client.util.webhook.LklWebHookIntf;
import com.lakala.crossborder.client.util.webhook.WebHookHandler;

@RestController
public class CustomAuthResultWebHook {

	private static final Logger logger = LoggerFactory.getLogger(CustomAuthResultWebHook .class);
    
    @Autowired
    private LklCrossPayEnv lklCrossPayEnv;
    
    @RequestMapping(value = "lklCustomAuthResult/handle")
	public LklCrossPayEncryptRes proceed(@RequestBody LklCrossPayEncryptReq notify) {
        //注册应用环境
        String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDPg0O4rPQJL1O+jqJ4rBjFVNRAuDmBSoii9pYfPQBaescCVY0irkWWoLyfTT65TjvnPpOx+IfNzBTlB13qCEFm7algREoeUHjFgFNHiXJ2LK/R0+VWgXe5+EDFfbrFCPnmLKG3OcKDGQszP0VOf6VVTM1t56CpgaRMm1/+Tzd2TQIDAQAB";
        String privKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIbyEihcDZbLfFhVpRU0knOtlzBhuWEjLBN4zd8dVzqAq52zWR04ybgZjjtlw+R9ICeJigmw2Dt3yy8SjpyMu5rYurTg/V0rGwayEuuE7aazi50nhZ/GYFRUVGIG0BoCI+73vsUmCO5EaUIJP/E0ew1bLMV7SMPynoQ8G9jXj8rXAgMBAAECgYAmfpFNcAz0UjGzZSMFbIzGcONrCsV9/zGIkHJxzgXfC2tpPgsSuetZF/kp2nrKCCOPA74by5WzSRXt5KZH5CFzuwwqIBv5cn6AiKiVmro3AnHV+qqNtfGXwLottu0VYcBEY2IPKb7SQ4pP5I0H973hnxR4qRzMAMUmEBVMiuUR+QJBAObLUBTjd7AE4Sls3/vBsYsYLYFfmw/dRHaW6jnuNxPTfVtPTGjY3V/672Vs01/f6QdtHHpMN+2xUk+acErAJTUCQQCVrvakm5R2sQwPycSO4mZW5sHKGbBosAkcY4llISVtxzSjgkPtBPVukCdNSGTuJX7+Ci8KolilrCc2XQCkH21bAkEAlcUAXd3TEL3J5BkMLRLgBTSWaytAtAXR5OdAboGA6nPHGJcYLb31wtBTxEzfyorCbRhIb7DAZpY4pQHCty+DtQJATFwCdPztYxN03MUIof+7R4/WwpwSU4WiUDozCEU9i+A46UT2E/8YmbuuYQ2Sd67nNv/I+brSUEofgus0/YUOywJBAMPu5o9guMsAjVqvXuxbkFpblYGZO/BrwiuLGDWEj4DZFqrIDmqgA6edy3HSoZ7U69BJZLTPb9DeebEiebmJZUI=";
        lklCrossPayEnv.registerEnv(LklEnv.SANDBOX, "RZCP161200000", privKey, pubKey);
    	logger.debug("entering method proceed,req={}", notify.toString());
    	 CustomAuthNotify customAuthNotify = null;
         LklCrossPayEncryptRes res = new LklCrossPayEncryptRes();
         res.setMerId(notify.getMerId());
         res.setTs(notify.getTs());
         res.setRetMsg("通知成功");
         res.setRetCode("0000");
         res.setVer("3.0.0");
         res.setEncKey(notify.getEncKey());

         try {
        	 customAuthNotify= LklMsgUtil.decryptMsgFromLklCustomAuth(notify, CustomAuthNotify.class);
            // 得到通知的结果进行业务处理
             CustomAuthResultNotifyRes response = new CustomAuthResultNotifyRes();
             response.setAmount(customAuthNotify.getAmount());
             response.setBgUrl(customAuthNotify.getBgUrl());
             response.setBizTypeCode(customAuthNotify.getBizTypeCode());
             response.setCbpName(customAuthNotify.getCbpName());
             response.setClientId(customAuthNotify.getClientId());
             response.setCuId(customAuthNotify.getCuId());
             response.setCustomcomCode(customAuthNotify.getCustomcomCode());
             response.setGoodsFee(customAuthNotify.getGoodsFee());
             response.setMobile(customAuthNotify.getMobile());
             response.setName(customAuthNotify.getName());
             response.setOrderNo(customAuthNotify.getOrderNo());
             response.setOrderNote(customAuthNotify.getOrderNote());
             response.setPayerMail(customAuthNotify.getPayerMail());
             response.setPayOrderId(customAuthNotify.getPayOrderId());
             response.setTaxFee(customAuthNotify.getTaxFee());
             res = LklMsgUtil.encryptWebHookMsgCustomAuth(response, res);
         } catch (LklCommonException e) {
             logger.error("lakala custom auth error", e);
             res.setRetCode("9999");
             res.setRetMsg(e.getMessage());
         } catch (LklEncryptException e) {
             logger.error("lakala custom auth error", e);
             res.setRetCode("9999");
             res.setRetMsg(e.getMessage());
         } catch (Exception e) {
             logger.error("lakala custom auth error", e);
             res.setRetCode("9999");
             res.setRetMsg("系统异常");
         }
         logger.debug("exiting method proceed,res ={}", res.toString());
         return res;
	}

}

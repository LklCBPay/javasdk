package com.lakala.crossborder.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lakala.crossborder.client.entities.LklCrossPayEncryptReq;
import com.lakala.crossborder.client.entities.LklCrossPayEncryptRes;
import com.lakala.crossborder.client.entities.LklCrossPaySuperReq;
import com.lakala.crossborder.client.entities.auth.CustomAuthReq;
import com.lakala.crossborder.client.entities.auth.CustomAuthRes;
import com.lakala.crossborder.client.entities.query.CustomAuthQueryReq;
import com.lakala.crossborder.client.entities.query.CustomAuthQueryRes;
import com.lakala.crossborder.client.exception.LklClientException;
import com.lakala.crossborder.client.util.LklCrossPayRestfulClent;
import com.lakala.crossborder.client.util.LklMsgUtil;

@Component
public class CustomAuthClient {
    private static final Logger logger=LoggerFactory.getLogger(CustomAuthClient.class);
    
    @Autowired
    private LklCrossPayRestfulClent payRestfulClent;
    
    /**
     * 海关认证请求
     * @param customAuthReq
     * @param dataHead
     * @return
     */
    public CustomAuthRes customAuth(CustomAuthReq customAuthReq,LklCrossPaySuperReq dataHead){
    	 CustomAuthRes customAuthRes=null;
    	 LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(customAuthReq, dataHead);
         try {
             LklCrossPayEncryptRes encryptRes = payRestfulClent.doPost(LklCrossPayEncryptRes.class, req, "/gate/customAuth");

             if ("0000".equals(encryptRes.getRetCode())) {
            	 customAuthRes = LklMsgUtil.decrypt(encryptRes, CustomAuthRes.class);
             } else {
                 customAuthRes = new CustomAuthRes();
             }
             customAuthRes.setRetCode(encryptRes.getRetCode());
             customAuthRes.setRetMsg(encryptRes.getRetMsg());
             customAuthRes.setVer(encryptRes.getVer());
             customAuthRes.setPayTypeId(encryptRes.getPayTypeId());
             customAuthRes.setReqType(encryptRes.getReqType());
             customAuthRes.setTs(encryptRes.getTs());
             customAuthRes.setMerId(encryptRes.getMerId());
         } catch (Exception e) {
             logger.error("拉卡拉认证 error", e);
             throw new LklClientException(e.getMessage(), e);
         }
         return customAuthRes;
    }
    
    /**
     * 根据订单号查询海关认证订单
     * @param customAuthQueryReq
     * @param dataHead
     * @return
     */
    public CustomAuthQueryRes queryCustomAuth(CustomAuthQueryReq customAuthQueryReq,LklCrossPaySuperReq dataHead){
    	CustomAuthQueryRes customAuthQueryRes=null;
    	 LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(customAuthQueryReq, dataHead);
         try {
             LklCrossPayEncryptRes encryptRes = payRestfulClent.doPost(LklCrossPayEncryptRes.class, req, "/gate/queryCustomAuth");

             if ("0000".equals(encryptRes.getRetCode())) {
            	 customAuthQueryRes = LklMsgUtil.decrypt(encryptRes, CustomAuthQueryRes.class);
             } else {
            	 customAuthQueryRes = new CustomAuthQueryRes();
             }
             customAuthQueryRes.setRetCode(encryptRes.getRetCode());
             customAuthQueryRes.setRetMsg(encryptRes.getRetMsg());
             customAuthQueryRes.setVer(encryptRes.getVer());
             customAuthQueryRes.setPayTypeId(encryptRes.getPayTypeId());
             customAuthQueryRes.setReqType(encryptRes.getReqType());
             customAuthQueryRes.setTs(encryptRes.getTs());
             customAuthQueryRes.setMerId(encryptRes.getMerId());
         } catch (Exception e) {
             logger.error("拉卡拉认证 error", e);
             throw new LklClientException(e.getMessage(), e);
         }
         return customAuthQueryRes;
    }
}

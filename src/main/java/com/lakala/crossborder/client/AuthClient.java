package com.lakala.crossborder.client;

import com.lakala.crossborder.client.entities.LklCrossPayEncryptReq;
import com.lakala.crossborder.client.entities.LklCrossPayEncryptRes;
import com.lakala.crossborder.client.entities.LklCrossPaySuperReq;
import com.lakala.crossborder.client.entities.auth.AuthReq;
import com.lakala.crossborder.client.entities.auth.AuthRes;
import com.lakala.crossborder.client.exception.LklClientException;
import com.lakala.crossborder.client.util.LklCrossPayRestfulClent;
import com.lakala.crossborder.client.util.LklMsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 拉卡拉认证接口
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 *         Created by jiang on 2017/1/3.
 */
@Component
public class AuthClient {
    private static final Logger logger = LoggerFactory.getLogger(LklCbPayQueryClient.class);
    @Autowired
    private LklCrossPayRestfulClent payRestfulClent;

    /**
     * 拉卡拉认证接口
     *
     * @param authReq      认证请求参数
     * @param dataHead
     * @return
     * @throws LklClientException
     */
    public AuthRes auth(AuthReq authReq, LklCrossPaySuperReq dataHead) throws LklClientException {
        AuthRes authRes = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(authReq, dataHead);
        try {
            LklCrossPayEncryptRes encryptRes = payRestfulClent.doPost(LklCrossPayEncryptRes.class, req, "/gate/auth");

            if ("0000".equals(encryptRes.getRetCode())) {
                authRes = LklMsgUtil.decrypt(encryptRes, AuthRes.class);
            } else {
                authRes = new AuthRes();
            }
            authRes.setRetCode(encryptRes.getRetCode());
            authRes.setRetMsg(encryptRes.getRetMsg());
            authRes.setVer(encryptRes.getVer());
            authRes.setPayTypeId(encryptRes.getPayTypeId());
            authRes.setReqType(encryptRes.getReqType());
            authRes.setTs(encryptRes.getTs());
            authRes.setMerId(encryptRes.getMerId());
        } catch (Exception e) {
            logger.error("拉卡拉认证 error", e);
            throw new LklClientException(e.getMessage(), e);
        }
        return authRes;
    }
}

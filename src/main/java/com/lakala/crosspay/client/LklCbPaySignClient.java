package com.lakala.crosspay.client;

import com.lakala.crosspay.client.entities.LklCrossPayEncryptReq;
import com.lakala.crosspay.client.entities.LklCrossPayEncryptRes;
import com.lakala.crosspay.client.entities.LklCrossPaySuperReq;
import com.lakala.crosspay.client.entities.query.ExchangeRateQueryRes;
import com.lakala.crosspay.client.entities.sign.SignReq;
import com.lakala.crosspay.client.entities.sign.SignRes;
import com.lakala.crosspay.client.entities.sign.SignVerifyReq;
import com.lakala.crosspay.client.entities.sign.SignVerifyRes;
import com.lakala.crosspay.client.exception.LklClientException;
import com.lakala.crosspay.client.util.LklCrossPayRestfulClent;
import com.lakala.crosspay.client.util.LklMsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 拉卡拉跨境支付-签约类接口
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
@Component
public class LklCbPaySignClient {
    private static final Logger logger = LoggerFactory.getLogger(LklCbPaySignClient.class);
    @Autowired
    private LklCrossPayRestfulClent payRestfulClent;

    /**
     * <p>
     * 拉卡拉跨境支付-用户签约
     * </p>
     *
     * @param signReq
     * @param dataHead
     * @return
     * @throws LklClientException
     */
    public SignRes sign(SignReq signReq, LklCrossPaySuperReq dataHead) throws LklClientException {
        SignRes res = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(signReq, dataHead);
        try {
            LklCrossPayEncryptRes encryptRes = payRestfulClent.doPost(LklCrossPayEncryptRes.class, req, "/ppayGate/merCrossBorderAction.do");

            if ("0000".equals(encryptRes.getRetCode())) {
                res = LklMsgUtil.decrypt(encryptRes, SignRes.class);
            } else {
                res = new SignRes();
            }
            res.setRetCode(encryptRes.getRetCode());
            res.setRetMsg(encryptRes.getRetMsg());
            res.setVer(encryptRes.getVer());
            res.setPayTypeId(encryptRes.getPayTypeId());
            res.setReqType(encryptRes.getReqType());
            res.setTs(encryptRes.getTs());
            res.setMerId(encryptRes.getMerId());
        } catch (Exception e) {
            logger.error("拉卡拉跨境支付-用户签约 error", e);
            throw new LklClientException(e.getMessage(), e);
        }
        return res;
    }

    /**
     * <p>
     * 拉卡拉跨境支付-用户签约验证
     * </p>
     *
     * @param signVerifyReq
     * @param dataHead
     * @return
     * @throws LklClientException
     */
    public SignVerifyRes signVerify(SignVerifyReq signVerifyReq, LklCrossPaySuperReq dataHead) throws LklClientException {
        SignVerifyRes res = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(signVerifyReq, dataHead);
        try {
            LklCrossPayEncryptRes encryptRes = payRestfulClent.doPost(LklCrossPayEncryptRes.class, req, "/ppayGate/merCrossBorderAction.do");

            if ("0000".equals(encryptRes.getRetCode())) {
                res = LklMsgUtil.decrypt(encryptRes, SignVerifyRes.class);
            } else {
                res = new SignVerifyRes();
            }
            res.setRetCode(encryptRes.getRetCode());
            res.setRetMsg(encryptRes.getRetMsg());
            res.setVer(encryptRes.getVer());
            res.setPayTypeId(encryptRes.getPayTypeId());
            res.setReqType(encryptRes.getReqType());
            res.setTs(encryptRes.getTs());
            res.setMerId(encryptRes.getMerId());
        } catch (Exception e) {
            logger.error("拉卡拉跨境支付-用户签约 error", e);
            throw new LklClientException(e.getMessage(), e);
        }
        return res;
    }

}

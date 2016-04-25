package com.lakala.crosspay.client;

import com.lakala.crosspay.client.entities.LklCrossPayEncryptReq;
import com.lakala.crosspay.client.entities.LklCrossPayEncryptRes;
import com.lakala.crosspay.client.entities.LklCrossPaySuperReq;
import com.lakala.crosspay.client.entities.query.*;
import com.lakala.crosspay.client.exception.LklClientException;
import com.lakala.crosspay.client.util.LklCrossPayRestfulClent;
import com.lakala.crosspay.client.util.LklMsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 拉卡拉跨境支付-查询类接口
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
@Component
public class LklCbPayQueryClient {

    private static final Logger logger = LoggerFactory.getLogger(LklCbPayQueryClient.class);
    @Autowired
    private LklCrossPayRestfulClent payRestfulClent;


    /**
     * <p>
     * 拉卡拉跨境支付-汇率查询
     * </p>
     *
     * @param exchangeRateQueryReq
     * @param dataHead
     * @return
     * @throws LklClientException
     */
    public ExchangeRateQueryRes exchangeRateQuery(ExchangeRateQueryReq exchangeRateQueryReq, LklCrossPaySuperReq dataHead) throws LklClientException {
        ExchangeRateQueryRes res = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(exchangeRateQueryReq, dataHead);
        try {
            LklCrossPayEncryptRes encryptRes = payRestfulClent.doPost(LklCrossPayEncryptRes.class, req, "/ppayGate/merCrossBorderAction.do");

            if ("0000".equals(encryptRes.getRetCode())) {
                res = LklMsgUtil.decrypt(encryptRes, ExchangeRateQueryRes.class);
            } else {
                res = new ExchangeRateQueryRes();
            }
            res.setRetCode(encryptRes.getRetCode());
            res.setRetMsg(encryptRes.getRetMsg());
            res.setVer(encryptRes.getVer());
            res.setPayTypeId(encryptRes.getPayTypeId());
            res.setReqType(encryptRes.getReqType());
            res.setTs(encryptRes.getTs());
            res.setMerId(encryptRes.getMerId());
        } catch (Exception e) {
            logger.error("拉卡拉跨境支付-汇率查询 error", e);
            throw new LklClientException(e.getMessage(), e);
        }
        return res;
    }

    /**
     * <p>
     * 拉卡拉跨境支付-订单查询
     * </p>
     *
     * @param orderQueryReq
     * @param dataHead
     * @return
     * @throws LklClientException
     */
    public OrderQueryRes orderQuery(OrderQueryReq orderQueryReq, LklCrossPaySuperReq dataHead) throws LklClientException {
        OrderQueryRes res = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(orderQueryReq, dataHead);
        try {
            LklCrossPayEncryptRes encryptRes = payRestfulClent.doPost(LklCrossPayEncryptRes.class, req, "/ppayGate/merCrossBorderAction.do");

            if ("0000".equals(encryptRes.getRetCode())) {
                res = LklMsgUtil.decrypt(encryptRes, OrderQueryRes.class);
            } else {
                res = new OrderQueryRes();
            }
            res.setRetCode(encryptRes.getRetCode());
            res.setRetMsg(encryptRes.getRetMsg());
            res.setVer(encryptRes.getVer());
            res.setPayTypeId(encryptRes.getPayTypeId());
            res.setReqType(encryptRes.getReqType());
            res.setTs(encryptRes.getTs());
            res.setMerId(encryptRes.getMerId());
        } catch (Exception e) {
            logger.error("拉卡拉跨境支付-订单查询 error", e);
            throw new LklClientException(e.getMessage(), e);
        }
        return res;
    }

    /**
     * <p>拉卡拉跨境支付-签约查询</p>
     *
     * @param signQueryReq
     * @param dataHead
     * @return
     * @throws LklClientException
     */
    public SignQueryRes signQuery(SignQueryReq signQueryReq, LklCrossPaySuperReq dataHead) throws LklClientException {
        SignQueryRes res = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(signQueryReq, dataHead);
        try {
            LklCrossPayEncryptRes encryptRes = payRestfulClent.doPost(LklCrossPayEncryptRes.class, req, "/ppayGate/merCrossBorderAction.do");

            if ("0000".equals(encryptRes.getRetCode())) {
                res = LklMsgUtil.decrypt(encryptRes, SignQueryRes.class);
            } else {
                res = new SignQueryRes();
            }
            res.setRetCode(encryptRes.getRetCode());
            res.setRetMsg(encryptRes.getRetMsg());
            res.setVer(encryptRes.getVer());
            res.setPayTypeId(encryptRes.getPayTypeId());
            res.setReqType(encryptRes.getReqType());
            res.setTs(encryptRes.getTs());
            res.setMerId(encryptRes.getMerId());
        } catch (Exception e) {
            logger.error("拉卡拉跨境支付-签约查询 error", e);
            throw new LklClientException(e.getMessage(), e);
        }
        return res;
    }

    /**
     * <p>
     * 拉卡拉跨境支付-单笔试试代付状态查询
     * </p>
     *
     * @param queryReq
     * @param dataHead
     * @return
     * @throws LklClientException
     */
    public AgentPayStatusQueryRes agentPayStatusQuery(AgentPayStatusQueryReq queryReq, LklCrossPaySuperReq dataHead) throws LklClientException {
        AgentPayStatusQueryRes res = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(queryReq, dataHead);
        try {
            LklCrossPayEncryptRes encryptRes = payRestfulClent.doPost(LklCrossPayEncryptRes.class, req, "/gate/queryStatus");

            if ("0000".equals(encryptRes.getRetCode())) {
                res = LklMsgUtil.decrypt(encryptRes, AgentPayStatusQueryRes.class);
            } else {
                res = new AgentPayStatusQueryRes();
            }
            res.setRetCode(encryptRes.getRetCode());
            res.setRetMsg(encryptRes.getRetMsg());
            res.setVer(encryptRes.getVer());
            res.setPayTypeId(encryptRes.getPayTypeId());
            res.setReqType(encryptRes.getReqType());
            res.setTs(encryptRes.getTs());
            res.setMerId(encryptRes.getMerId());
        } catch (Exception e) {
            logger.error("拉卡拉跨境支付-签约查询 error", e);
            throw new LklClientException(e.getMessage(), e);
        }
        return res;
    }

    /**
     * <p>
     * 拉卡拉跨境支付-商户代付账户余额查询
     * </p>
     *
     * @param balanceQueryReq
     * @param dataHead
     * @return
     * @throws LklClientException
     */
    public AgentPayAcctBalanceQueryRes agentPayAcctBalanceQuery(AgentPayAcctBalanceQueryReq balanceQueryReq, LklCrossPaySuperReq dataHead) throws LklClientException {
        AgentPayAcctBalanceQueryRes res = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(balanceQueryReq, dataHead);
        try {
            LklCrossPayEncryptRes encryptRes = payRestfulClent.doPost(LklCrossPayEncryptRes.class, req, "/gate/balanceSearch");

            if ("0000".equals(encryptRes.getRetCode())) {
                res = LklMsgUtil.decrypt(encryptRes, AgentPayAcctBalanceQueryRes.class);
            } else {
                res = new AgentPayAcctBalanceQueryRes();
            }
            res.setRetCode(encryptRes.getRetCode());
            res.setRetMsg(encryptRes.getRetMsg());
            res.setVer(encryptRes.getVer());
            res.setPayTypeId(encryptRes.getPayTypeId());
            res.setReqType(encryptRes.getReqType());
            res.setTs(encryptRes.getTs());
            res.setMerId(encryptRes.getMerId());
        } catch (Exception e) {
            logger.error("拉卡拉跨境支付-签约查询 error", e);
            throw new LklClientException(e.getMessage(), e);
        }
        return res;
    }
}

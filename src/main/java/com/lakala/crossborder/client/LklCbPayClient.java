package com.lakala.crossborder.client;

import com.lakala.crossborder.client.entities.LklCrossPayEncryptReq;
import com.lakala.crossborder.client.entities.LklCrossPayEncryptRes;
import com.lakala.crossborder.client.entities.LklCrossPaySuperReq;
import com.lakala.crossborder.client.entities.gather.LklGatherReq;
import com.lakala.crossborder.client.entities.gather.LklGatherRes;
import com.lakala.crossborder.client.entities.otp.LklOtpReq;
import com.lakala.crossborder.client.entities.otp.LklOtpRes;
import com.lakala.crossborder.client.entities.payAgent.LklPayAgentReq;
import com.lakala.crossborder.client.entities.payAgent.LklPayAgentRes;
import com.lakala.crossborder.client.entities.payment.PaymentReq;
import com.lakala.crossborder.client.entities.payment.PaymentRes;
import com.lakala.crossborder.client.entities.recon.ReconSubscribeReq;
import com.lakala.crossborder.client.entities.recon.ReconSubscribeRes;
import com.lakala.crossborder.client.entities.refund.RefundReq;
import com.lakala.crossborder.client.entities.refund.RefundRes;
import com.lakala.crossborder.client.entities.submit.SubmitOrderReq;
import com.lakala.crossborder.client.entities.submit.SubmitOrderRes;
import com.lakala.crossborder.client.exception.LklClientException;
import com.lakala.crossborder.client.util.LklCrossPayRestfulClent;
import com.lakala.crossborder.client.util.LklMsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 拉卡拉跨境支付接入端口
 * </p>
 * <p/>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
@Component("lklCbPayClient")
public class LklCbPayClient {

    private static final Logger logger = LoggerFactory.getLogger(LklCbPayClient.class);

    @Autowired
    private LklCrossPayRestfulClent payRestfulClent;


    /**
     * <p>
     * 商  交订单
     * </p>
     *
     * @param order    商 订单
     * @param dataHead 文头
     * @return SubmitOrderRes
     * @throws LklClientException
     */
    public SubmitOrderRes submitOrder(SubmitOrderReq order, LklCrossPaySuperReq dataHead) throws LklClientException {
        SubmitOrderRes res = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(order, dataHead);
        try {
            LklCrossPayEncryptRes encryptRes = payRestfulClent.doPost(LklCrossPayEncryptRes.class, req, "/ppayGate/merCrossBorderAction.do");
            if ("0000".equals(encryptRes.getRetCode())) {
                res = LklMsgUtil.decrypt(encryptRes, SubmitOrderRes.class);
            } else {
                res = new SubmitOrderRes();
            }
            res.setRetCode(encryptRes.getRetCode());
            res.setRetMsg(encryptRes.getRetMsg());
            res.setVer(encryptRes.getVer());
            res.setPayTypeId(encryptRes.getPayTypeId());
            res.setReqType(encryptRes.getReqType());
            res.setTs(encryptRes.getTs());
            res.setMerId(encryptRes.getMerId());
        } catch (Exception e) {
            logger.error("拉卡拉跨境支付-商户提交订单 error", e);
            throw new LklClientException(e.getMessage(), e);
        }
        return res;
    }

    /**
     * <p>
     * 确认支付
     * </p>
     *
     * @param payOrder 业务参数
     * @param dataHead 文头
     * @return PaymentRes
     * @throws LklClientException
     */
    public PaymentRes pay(PaymentReq payOrder, LklCrossPaySuperReq dataHead) throws LklClientException {
        PaymentRes res = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(payOrder, dataHead);
        try {
            LklCrossPayEncryptRes encryptRes = payRestfulClent.doPost(LklCrossPayEncryptRes.class, req, "/ppayGate/merCrossBorderAction.do");

            if ("0000".equals(encryptRes.getRetCode())) {
                res = LklMsgUtil.decrypt(encryptRes, PaymentRes.class);
            } else {
                res = new PaymentRes();
            }
            res.setRetCode(encryptRes.getRetCode());
            res.setRetMsg(encryptRes.getRetMsg());
            res.setVer(encryptRes.getVer());
            res.setPayTypeId(encryptRes.getPayTypeId());
            res.setReqType(encryptRes.getReqType());
            res.setTs(encryptRes.getTs());
            res.setMerId(encryptRes.getMerId());
        } catch (Exception e) {
            logger.error("拉卡拉跨境支付-确认支付 error", e);
            throw new LklClientException(e.getMessage(), e);
        }
        return res;
    }

    /**
     * <p>
     * 卡 跨境支付-支付短信 证码重新获取
     * </p>
     *
     * @param otpReq
     * @param dataHead
     * @return
     * @throws LklClientException
     */
    public LklOtpRes sendOtp(LklOtpReq otpReq, LklCrossPaySuperReq dataHead) throws LklClientException {
        LklOtpRes res = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(otpReq, dataHead);
        try {
            LklCrossPayEncryptRes encryptRes = payRestfulClent.doPost(LklCrossPayEncryptRes.class, req, "/ppayGate/merCrossBorderAction.do");

            if ("0000".equals(encryptRes.getRetCode())) {
                res = LklMsgUtil.decrypt(encryptRes, LklOtpRes.class);
            } else {
                res = new LklOtpRes();
            }
            res.setRetCode(encryptRes.getRetCode());
            res.setRetMsg(encryptRes.getRetMsg());
            res.setVer(encryptRes.getVer());
            res.setPayTypeId(encryptRes.getPayTypeId());
            res.setReqType(encryptRes.getReqType());
            res.setTs(encryptRes.getTs());
            res.setMerId(encryptRes.getMerId());
        } catch (Exception e) {
            logger.error("拉卡拉跨境支付-支付短信验证码重新获取 error", e);
            throw new LklClientException(e.getMessage(), e);
        }
        return res;
    }

    /**
     * <p>
     * 卡 跨境支付-单笔实时收款
     * </p>
     *
     * @param gatherOrder
     * @param dataHead
     * @return
     * @throws LklClientException
     */
    public LklGatherRes gather(LklGatherReq gatherOrder, LklCrossPaySuperReq dataHead) throws LklClientException {
        LklGatherRes res = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(gatherOrder, dataHead);
        try {
            LklCrossPayEncryptRes encryptRes = payRestfulClent.doPost(LklCrossPayEncryptRes.class, req, "/ppayGate/merCrossBorderAction.do");

            if ("0000".equals(encryptRes.getRetCode())) {
                res = LklMsgUtil.decrypt(encryptRes, LklGatherRes.class);
            } else {
                res = new LklGatherRes();
            }
            res.setRetCode(encryptRes.getRetCode());
            res.setRetMsg(encryptRes.getRetMsg());
            res.setVer(encryptRes.getVer());
            res.setPayTypeId(encryptRes.getPayTypeId());
            res.setReqType(encryptRes.getReqType());
            res.setTs(encryptRes.getTs());
            res.setMerId(encryptRes.getMerId());
        } catch (Exception e) {
            logger.error("拉卡拉跨境支付-单笔实时收款 error", e);
            throw new LklClientException(e.getMessage(), e);
        }
        return res;
    }

    /**
     * 卡 跨境支付-单笔实时代付
     *
     * @param agentPayReq
     * @param dataHead
     * @return
     * @throws LklClientException
     */
    public LklPayAgentRes agentPay(LklPayAgentReq agentPayReq, LklCrossPaySuperReq dataHead) throws LklClientException {
        LklPayAgentRes res = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(agentPayReq, dataHead);
        try {
            LklCrossPayEncryptRes encryptRes = payRestfulClent.doPost(LklCrossPayEncryptRes.class, req, "/gate/addOrder");

            if ("0000".equals(encryptRes.getRetCode())) {
                res = LklMsgUtil.decrypt(encryptRes, LklPayAgentRes.class);
            } else {
                res = new LklPayAgentRes();
            }

            res.setRetCode(encryptRes.getRetCode());
            res.setRetMsg(encryptRes.getRetMsg());
            res.setVer(encryptRes.getVer());
            res.setPayTypeId(encryptRes.getPayTypeId());
            res.setReqType(encryptRes.getReqType());
            res.setTs(encryptRes.getTs());
            res.setMerId(encryptRes.getMerId());
        } catch (Exception e) {
            logger.error("拉卡拉跨境支付-单笔实时代付 error", e);
            throw new LklClientException(e.getMessage(), e);
        }
        return res;
    }

    /**
     * <p>
     * 卡 跨境支付-退款 口
     * </p>
     *
     * @param refundReq
     * @param dataHead
     * @return
     * @throws LklClientException
     */
    public RefundRes refun(RefundReq refundReq, LklCrossPaySuperReq dataHead) throws LklClientException {
        RefundRes res = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(refundReq, dataHead);
        try {
            LklCrossPayEncryptRes encryptRes = payRestfulClent.doPost(LklCrossPayEncryptRes.class, req, "/ppayGate/merCrossBorderAction.do");

            if ("0000".equals(encryptRes.getRetCode())) {
                res = LklMsgUtil.decrypt(encryptRes, RefundRes.class);
            } else {
                res = new RefundRes();
            }

            res.setRetCode(encryptRes.getRetCode());
            res.setRetMsg(encryptRes.getRetMsg());
            res.setVer(encryptRes.getVer());
            res.setPayTypeId(encryptRes.getPayTypeId());
            res.setReqType(encryptRes.getReqType());
            res.setTs(encryptRes.getTs());
            res.setMerId(encryptRes.getMerId());
        } catch (Exception e) {
            logger.error("拉卡拉跨境支付-退款接口 error", e);
            throw new LklClientException(e.getMessage(), e);
        }
        return res;
    }

    /**
     * <p>
     * 拉卡拉跨境支付-对账文件预约下载
     * </p>
     *
     * @param reconSubscribeReq
     * @param dataHead
     * @return
     * @throws LklClientException
     */
    public ReconSubscribeRes reconSubscribe(ReconSubscribeReq reconSubscribeReq, LklCrossPaySuperReq dataHead) throws LklClientException {

        ReconSubscribeRes res = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(reconSubscribeReq, dataHead);
        try {
            LklCrossPayEncryptRes encryptRes = payRestfulClent.doPost(LklCrossPayEncryptRes.class, req, "/ppayGate/merCrossBorderAction.do");

            if ("0000".equals(encryptRes.getRetCode())) {
                res = LklMsgUtil.decrypt(encryptRes, ReconSubscribeRes.class);
            } else {
                res = new ReconSubscribeRes();
            }

            res.setRetCode(encryptRes.getRetCode());
            res.setRetMsg(encryptRes.getRetMsg());
            res.setVer(encryptRes.getVer());
            res.setPayTypeId(encryptRes.getPayTypeId());
            res.setReqType(encryptRes.getReqType());
            res.setTs(encryptRes.getTs());
            res.setMerId(encryptRes.getMerId());
        } catch (Exception e) {
            logger.error("拉卡拉跨境支付-退款接口 error", e);
            throw new LklClientException(e.getMessage(), e);
        }
        return res;
    }
}

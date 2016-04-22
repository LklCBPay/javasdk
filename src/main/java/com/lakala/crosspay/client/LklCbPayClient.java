package com.lakala.crosspay.client;

import com.lakala.crosspay.client.entities.LklCrossPayEncryptReq;
import com.lakala.crosspay.client.entities.LklCrossPayEncryptRes;
import com.lakala.crosspay.client.entities.LklCrossPaySuperReq;
import com.lakala.crosspay.client.entities.gather.LklGatherReq;
import com.lakala.crosspay.client.entities.gather.LklGatherRes;
import com.lakala.crosspay.client.entities.otp.LklOtpReq;
import com.lakala.crosspay.client.entities.otp.LklOtpRes;
import com.lakala.crosspay.client.entities.payAgent.LklPayAgentReq;
import com.lakala.crosspay.client.entities.payAgent.LklPayAgentRes;
import com.lakala.crosspay.client.entities.payment.PaymentReq;
import com.lakala.crosspay.client.entities.payment.PaymentRes;
import com.lakala.crosspay.client.entities.submit.SubmitOrderReq;
import com.lakala.crosspay.client.entities.submit.SubmitOrderRes;
import com.lakala.crosspay.client.exception.LklClientException;
import com.lakala.crosspay.client.util.LklCrossPayRestfulClent;
import com.lakala.crosspay.client.util.LklMsgUtil;
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

    @Autowired
    private LklCrossPayRestfulClent payRestfulClent;


    /**
     * <p>
     * 商户提交订单
     * </p>
     *
     * @param order    商户订单
     * @param dataHead 报文头
     * @return SubmitOrderRes
     */
    public SubmitOrderRes submitOrder(SubmitOrderReq order, LklCrossPaySuperReq dataHead) throws LklClientException {
        SubmitOrderRes res = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(order, dataHead);
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

        return res;
    }

    /**
     * <p>
     * 确认支付
     * </p>
     *
     * @param payOrder 业务参数
     * @param dataHead 报文头
     * @return PaymentRes
     */
    public PaymentRes pay(PaymentReq payOrder, LklCrossPaySuperReq dataHead) {
        PaymentRes res = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(payOrder, dataHead);
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
        return res;
    }

    /**
     * <p>
     * 拉卡拉跨境支付-支付短信验证码重新获取
     * </p>
     *
     * @param otpReq
     * @param dataHead
     * @return
     */
    public LklOtpRes sendOtp(LklOtpReq otpReq, LklCrossPaySuperReq dataHead) {
        LklOtpRes res = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(otpReq, dataHead);
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
        return res;
    }

    /**
     * <p>
     * 拉卡拉跨境支付-单笔实时收款
     * </p>
     *
     * @param gatherOrder
     * @param dataHead
     * @return
     */
    public LklGatherRes gather(LklGatherReq gatherOrder, LklCrossPaySuperReq dataHead) {
        LklGatherRes res = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(gatherOrder, dataHead);
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
        return res;
    }

    /**
     * 拉卡拉跨境支付-单笔实时代付
     *
     * @param agentPayReq
     * @param dataHead
     * @return
     */
    public LklPayAgentRes agentPay(LklPayAgentReq agentPayReq, LklCrossPaySuperReq dataHead) {
        LklPayAgentRes res = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(agentPayReq, dataHead);
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
        return res;
    }
}

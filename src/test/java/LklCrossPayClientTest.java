import com.lakala.crossborder.client.LklCbPayClient;
import com.lakala.crossborder.client.LklCbPayQueryClient;
import com.lakala.crossborder.client.LklCbPaySignClient;
import com.lakala.crossborder.client.entities.LklCrossPaySuperReq;
import com.lakala.crossborder.client.entities.gather.LklGatherReq;
import com.lakala.crossborder.client.entities.gather.LklGatherRes;
import com.lakala.crossborder.client.entities.otp.LklOtpReq;
import com.lakala.crossborder.client.entities.otp.LklOtpRes;
import com.lakala.crossborder.client.entities.payAgent.LklPayAgentReq;
import com.lakala.crossborder.client.entities.payAgent.LklPayAgentRes;
import com.lakala.crossborder.client.entities.payment.PaymentReq;
import com.lakala.crossborder.client.entities.payment.PaymentRes;
import com.lakala.crossborder.client.entities.query.*;
import com.lakala.crossborder.client.entities.recon.ReconSubscribeReq;
import com.lakala.crossborder.client.entities.recon.ReconSubscribeRes;
import com.lakala.crossborder.client.entities.refund.RefundReq;
import com.lakala.crossborder.client.entities.refund.RefundRes;
import com.lakala.crossborder.client.entities.sign.SignReq;
import com.lakala.crossborder.client.entities.sign.SignRes;
import com.lakala.crossborder.client.entities.sign.SignVerifyReq;
import com.lakala.crossborder.client.entities.sign.SignVerifyRes;
import com.lakala.crossborder.client.entities.submit.SubmitOrderReq;
import com.lakala.crossborder.client.entities.submit.SubmitOrderRes;
import com.lakala.crossborder.client.enums.CertType;
import com.lakala.crossborder.client.enums.CrossBorderBizType;
import com.lakala.crossborder.client.enums.LklCurrency;
import com.lakala.crossborder.client.enums.LklEnv;
import com.lakala.crossborder.client.util.DateUtil;
import com.lakala.crossborder.client.util.LklCrossPayEnv;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiangzhifei on 16/4/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:client-application.xml")
public class LklCrossPayClientTest {

    private static final Logger logger = LoggerFactory.getLogger(LklCrossPayClientTest.class);

    @Autowired
    private LklCbPayClient payClient;

    @Autowired
    private LklCbPayQueryClient queryClient;

    @Autowired
    private LklCbPaySignClient signClient;

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    @Before
    public void testSetUp() {
        //注册应用环境
        String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDPg0O4rPQJL1O+jqJ4rBjFVNRAuDmBSoii9pYfPQBaescCVY0irkWWoLyfTT65TjvnPpOx+IfNzBTlB13qCEFm7algREoeUHjFgFNHiXJ2LK/R0+VWgXe5+EDFfbrFCPnmLKG3OcKDGQszP0VOf6VVTM1t56CpgaRMm1/+Tzd2TQIDAQAB";
        String privKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKVTYaBXn6fMma0cCR6ss0QXabxV4yVyVoo/RtLejflqeQmeOs9x9pmO2lkrmdkBlrxEl4sl7KwnYswD0iCmvMmUHPz9Z09n5BSRU7xZ3goauT2iIoQOMDe1l2WYJLKjinwLIHqjOyd2spOBHbNsF4QfgisQJleIW3UaW1aYt71hAgMBAAECgYAM68FbiL2fnav9DY3g6dpw3euTnprMGG2PDHb6kA5Eif+/srAh+mQtiC6kfXXesfMX77xwUZx3r/RWwY2wo5dpgWgLxwePYgf4LVMPE5m9WhYEoNMjGTp2fk9BDI8c8F9XVTQX1fyHW4a41XAWUKGzmKCrULQvlNNse6PW3YfliQJBAOQEPJbjquEHzpXNQ7Hfgupo+yLAvHmGc2SOW9nP7saia2xwItKUNLwdxshhrXaB844J1l6qT6XrQCMmYFzU6X8CQQC5nYpeJo70pmJOh/NgFN8pv2Fxf8WQL9QSX1YffCy/Z24OX7j5Hjv/WbRsUXotj8FuxTQjKj+ctx0fLNb6RgkfAkEAgFkKKf05QLX+SUBjrIodzQkO4/8NmHJgHegsN6zdwR3HziBI2bPnA0TXkg+5bo2zpO5QprTDZKfJUH/XmimWzQJAYtmplNaID3aEqDnyvNqgtoT7igx31IAEOvyWyxHAavbUP8dupQ0a+rR1R7JklNsYMrvHNmsLkZRbZ/zjSEoAFQJAG8NYfKLKzd7vIwtnB2LDFEaWYAaifDRLXL4wCFlDV9DmtA+zpfktI6QS880aFs504ki/49SnjcHfDXYfRlnmgg==";
        LklCrossPayEnv.registerEnv(LklEnv.SANDBOX, "DOPCHN000116", privKey, pubKey);
    }

    /**
     * 商户订交订单
     */
    @Test
    public void tetsSubmitOrder() {

        SubmitOrderReq req = new SubmitOrderReq();
        req.setCardNo("6222021001116245702");
        req.setBgUrl("http://baidu.com");
        req.setBusiCode(CrossBorderBizType.STUDY_ABROAD_YEAR_BELOW.getCode());
        req.setCertType(CertType.ID.getCode());
        req.setClientId("360000000000000000");
        req.setClientName("孙xx");
        req.setCurrency(LklCurrency.CNY.getCode());
        req.setCvv("123");
        req.setDateOfExpire("1602");
        req.setIsMergeSign("1");
        req.setMerOrderId("SH20160420194753");
        req.setMobile("15000000000");
        req.setOrderAmount("223123.09");
        req.setOrderEffTime("20160422194550");
        req.setOrderSummary("111312");
        req.setOrderTime("20160420194550");
        req.setPayeeAmount("123123.09");
        req.setExt1("1231231你好我");
        req.setExt2("中华人民共和国");
        Map<String, String> ext = new HashMap<String, String>();
        ext.put("cuId", "2");
        ext.put("bizTypeCode", "E10");
        ext.put("goodsFee", "3123.98");
        ext.put("taxFee", "12.09");
        ext.put("buyForexKind", "0221");
        req.setExtension(ext);


        LklCrossPaySuperReq head = new LklCrossPaySuperReq();
        head.setVer("1.0.0");
        head.setTs(DateUtil.getCurrentTime());
        head.setReqType("B0002");
        head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());
        head.setPayTypeId("1");

        SubmitOrderRes res = payClient.submitOrder(req, head);
        logger.debug("商户提交订单结果={}", res);
    }

    /**
     * 确认支付
     */
    @Test
    public void testPayment() {

        PaymentReq payOrder = new PaymentReq();
        payOrder.setMerOrderId("SH20160420194753");
        payOrder.setOrderAmount("223123.09");
        payOrder.setCurrency(LklCurrency.CNY.getCode());
        payOrder.setPayeeAmount("123123.09");
        payOrder.setMsgCode("111111");
        payOrder.setTransactionId("2016042500084727");

        LklCrossPaySuperReq head = new LklCrossPaySuperReq();
        head.setVer("1.0.0");
        head.setTs(DateUtil.getCurrentTime());
        head.setReqType("B0003");
        head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());
//        head.setPayTypeId("1");
        PaymentRes res = payClient.pay(payOrder, head);
        logger.debug("确认支付响应结果={}", res);

    }

    /**
     * 支付短信 证码重新发送
     */
    @Test
    public void testSendOtp() {
        LklOtpReq req = new LklOtpReq();
        req.setOderTime("20160420194550");
        req.setMerOrderId("SH20160420194553");
        req.setTransactionId("2016042100084651");

        LklCrossPaySuperReq head = new LklCrossPaySuperReq();
        head.setVer("1.0.0");
        head.setTs(DateUtil.getCurrentTime());
        head.setReqType("B0004");
        head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());
        LklOtpRes res = payClient.sendOtp(req, head);

        logger.debug("支付短信验证码重新发送结果={}", res);
    }

    /**
     * 单笔实时收款
     */
    @Test
    public void testGather() {
        LklGatherReq req = new LklGatherReq();

        req.setCardNo("6222021001116245702");
        req.setBgUrl("http://baidu.com");
        req.setBusiCode(CrossBorderBizType.CARGO_TRADE.getCode());
        req.setCertType(CertType.ID.getCode());
        req.setClientId("360000000000000000");
        req.setClientName("孙xx");
        req.setCurrency(LklCurrency.CNY.getCode());
        req.setCvv("123");
        req.setDateOfExpire("1602");
        req.setMerOrderId("SH20160420194563");
        req.setMobile("15000000000");
        req.setOrderAmount("223123.09");
        req.setOrderEffTime("20160422194550");
        req.setOrderSummary("111312");
        req.setOrderTime("20160420194550");
        req.setPayeeAmount("123123.09");
        req.setExt1("1231231你好我");
        req.setExt2("中华人民共和国");
        Map<String, String> ext = new HashMap<String, String>();
        ext.put("cuId", "2");
        ext.put("bizTypeCode", "E10");
        ext.put("goodsFee", "3123.98");
        ext.put("taxFee", "12.09");
        ext.put("buyForexKind", "0221");
        req.setExtension(ext);

        LklCrossPaySuperReq head = new LklCrossPaySuperReq();
        head.setVer("1.0.0");
        head.setTs(DateUtil.getCurrentTime());
        head.setReqType("B0013");
        head.setPayTypeId("4");
        head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());

        LklGatherRes res = payClient.gather(req, head);

        logger.debug("单笔实时收款={}", res);

    }

    /**
     * 单笔实时代付
     */
    @Test
    public void testAgentPay() {
        LklPayAgentReq req = new LklPayAgentReq();

        req.setInterBankCode("6222021001116245702");
        req.setPayTypeId("5");
        req.setBgUrl("http://baidu.com");
        req.setCertType(CertType.ID.getCode());
        req.setClientId("360000000000000000");
        req.setAccountName("孙xx");
        req.setAccountNumber("6222021001116245702");
        req.setCurrency(LklCurrency.CNY.getCode());
        req.setCvv("123");
        req.setDateOfExpire("1602");
        req.setMerOrderId("SH20160430195553");
        req.setMobile("15000000000");
        req.setOrderAmount("0.02");
        req.setOrderEffTime("20160426094550");
        req.setOrderSummary("111312");
        req.setOrderTime("20160425094450");

        LklCrossPaySuperReq head = new LklCrossPaySuperReq();
        head.setVer("2.0.0");
        head.setTs(DateUtil.getCurrentTime());
        head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());

        LklPayAgentRes res = payClient.agentPay(req, head);

        logger.debug("单笔实时代付={}", res);

    }

    /**
     * 汇率查询
     */
    @Test
    public void testExchangeRateQuery() {
        ExchangeRateQueryReq req = new ExchangeRateQueryReq();
        req.setCurrency(LklCurrency.AUD.getCode());

        LklCrossPaySuperReq head = new LklCrossPaySuperReq();
        head.setVer("1.0.0");
        head.setTs(DateUtil.getCurrentTime());
        head.setReqType("B0001");
        //根据商户开通的产品设置
        head.setPayTypeId("4");
        head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());

        ExchangeRateQueryRes res = queryClient.exchangeRateQuery(req, head);

        logger.debug("汇率查询结果={}", res);
    }

    /**
     * 订单查询
     */
    @Test
    public void testOrderQuery() {
        OrderQueryReq req = new OrderQueryReq();
        req.setMerOrderId("SH20160420194553");
        req.setTransactionId("2016042100084651");

        LklCrossPaySuperReq head = new LklCrossPaySuperReq();
        head.setVer("1.0.0");
        head.setTs(DateUtil.getCurrentTime());
        head.setReqType("B0007");
        head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());

        OrderQueryRes res = queryClient.orderQuery(req, head);

        logger.debug("订单查询结果={}", res);
    }

    /**
     * 签约查询
     */
    @Test
    public void testSignQuery() {
        SignQueryReq req = new SignQueryReq();
        req.setCardNo("6222021001116245702");
        req.setClientName("孙xx");
        req.setClientId("360000000000000000");
        req.setCertType(CertType.ID.getCode());
        req.setMobile("15000000000");

        LklCrossPaySuperReq head = new LklCrossPaySuperReq();
        head.setVer("1.0.0");
        head.setTs(DateUtil.getCurrentTime());
        head.setReqType("B0009");
        head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());

        SignQueryRes res = queryClient.signQuery(req, head);

        logger.debug("签约查询结果={}", res);

    }

    /**
     * 单笔实时代付状态查询
     */
    @Test
    public void testAgentpayStausQuery() {
        AgentPayStatusQueryReq req = new AgentPayStatusQueryReq();
        req.setMerOrderId("SH20160420195553");

        LklCrossPaySuperReq head = new LklCrossPaySuperReq();
        head.setVer("2.0.0");
        head.setTs(DateUtil.getCurrentTime());
        head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());

        AgentPayStatusQueryRes res = queryClient.agentPayStatusQuery(req, head);
        logger.debug("单笔实时代付状态查询={}", res);
    }

    /**
     * 商户代付账户余额查询
     */
    @Test
    public void testAgentPayBalanceQuery() {
        AgentPayAcctBalanceQueryReq req = new AgentPayAcctBalanceQueryReq();
        req.setCurrency(LklCurrency.CNY.getCode());

        LklCrossPaySuperReq head = new LklCrossPaySuperReq();
        head.setVer("2.0.0");
        head.setTs(DateUtil.getCurrentTime());
        head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());

        AgentPayAcctBalanceQueryRes res = queryClient.agentPayAcctBalanceQuery(req, head);
        logger.debug("商户代付账户余额查询={}", res);
    }

    /**
     * 退款接口
     */
    @Test
    public void testRefund() {
        RefundReq req = new RefundReq();
        req.setMerOrderId("SH20160420194753");
        req.setSeqId("WH20160430195553");
        req.setCurrency(LklCurrency.CNY.getCode());
        req.setOrderAmount("0.02");
        req.setRetTime(DateUtil.getCurrentTime());
        req.setRetAmt("0.02");
        req.setRetCny(LklCurrency.CNY.getCode());

        LklCrossPaySuperReq head = new LklCrossPaySuperReq();
        head.setTs(DateUtil.getCurrentTime());
        head.setReqType("B0006");
        head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());

        RefundRes res = payClient.refun(req, head);
        logger.debug("退款接口={}", res);
    }

    /**
     * 用户签约
     */
    @Test
    public void testSign() {
        SignReq req = new SignReq();
        req.setCardNo("6222021001116245702");
        req.setClientName("孙xx");
        req.setCertType(CertType.ID.getCode());
        req.setClientId("360000000000000000");
        req.setDateOfExpire("1602");
        req.setMobile("15000000000");

        LklCrossPaySuperReq head = new LklCrossPaySuperReq();
        head.setTs(DateUtil.getCurrentTime());
        head.setReqType("B0010");
        head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());

        SignRes res = signClient.sign(req, head);
        logger.debug("用户签约={}", res);

    }

    /**
     * 用户签约验证
     */
    @Test
    public void testSignVerify() {
        SignVerifyReq req = new SignVerifyReq();

        req.setCardNo("6222021001116245702");
        req.setClientName("孙xx");
        req.setCertType(CertType.ID.getCode());
        req.setClientId("360000000000000000");
        req.setMobile("15000000000");
        req.setSmsCode("111111");

        LklCrossPaySuperReq head = new LklCrossPaySuperReq();
        head.setTs(DateUtil.getCurrentTime());
        head.setReqType("B0011");
        head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());

        SignVerifyRes res = signClient.signVerify(req, head);
        logger.debug("用户签约验证={}", res);
    }

    /**
     * 对账文件预约下载
     */
    @Test
    public void testReconSubscribe() {
        ReconSubscribeReq req = new ReconSubscribeReq();
        req.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());
        req.setStartDate("20160415");
        req.setEndDate("20160425");
        req.setNotifyAddr("http://baidu.com");

        LklCrossPaySuperReq head = new LklCrossPaySuperReq();
        head.setTs(DateUtil.getCurrentTime());
        head.setReqType("B0008");
        head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());

        ReconSubscribeRes res = payClient.reconSubscribe(req, head);
        logger.debug("对账文件预约下载={}", res);
    }

}

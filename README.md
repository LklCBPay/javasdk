#Read me

[![Build Status](https://travis-ci.org/LklCBPay/javasdk.svg?branch=master)](https://travis-ci.org/LklCBPay/javasdk)

![v3.3](https://img.shields.io/badge/Version-v3.4-blue.svg)

*有任何问题欢迎提出 [issue](https://github.com/lklcrossboard/javasdk/issues) 或邮件到 <int_bz@lakala.com>*

# 拉卡拉跨境支付平台后台接口java-sdk使用指南
*开发者可将该sdk打成jar包设置在自己项目类路径中，或作为maven的一个模块引入自己项目，以使用该sdk方便接入拉卡拉跨境支付平台后台接口*

**requirment:**

        * java 1.6+
        * spring
        * maven
---------------
### sdk集成指南
* 在开发者自己项目类路径中添加文件lklconfig.properties。该用文件用于定义httpClient连接池配置项。如下：

```java 
#连接超时时间，毫秒
connectTimeout=5000
#读超时时间，毫秒
readTimeout=5000
#http连接池最大连接数
maxTotal=200
#每个route的连接数
defaultMaxPerRoute=200
```
* 以上配置可根据自身系统情况自行设定
* 此sdk是maven项目，目录符合maven目录风格。
   * /src/test/java目中的文件为接口测试文件。
   * /src/main/java/LklCbPayClient文件中各方法为拉卡拉跨境支付平台后台接口对应的支付、退款接口对接方法
   * /src/main/java/LklCbPayQueryClient文件中各方法为拉卡拉跨境支付平台后台接口对应的查询类接口对接方法
   * /src/main/java/LklCbPaySignClient文件中各方法为拉卡拉跨境支付平台后台接口对应的签约类接口对接方法
* 开发者需在自己项目中引入sdk开发包spring配置
```xml
 <import resource="classpath*:client-application.xml"/>
```

>若需要启用sdk包中监听拉卡拉跨境回调商户的接口需对springmvc做配置

>1. 商户需开启 ```<mvc:annotation-driven/>``` ;

>2. 设置springmvc扫描包 ``` <context:component-scan base-package="com.lakala.crossborder.client.util.webhook"/> ```
> `如无需启用sdk包中监听拉卡拉跨境回调则直接在自己项目中引入sdk开发包spring配置即可`


## 接口对接说明
* 开发者在对接之前需注册对接环境

```java
LklCrossPayEnv.registerEnv(LklEnv.SANDBOX, merId, privKey, pubKey);
```
> `建议在项目启动时做全局唯一性注册`

LklEnv.SANDBOX-拉卡拉服务器环境。LklEnv.SANDBOX以及LklEnv.LIVE。live环境为生产环境

merId-拉卡拉跨境支付平台商户号

privKey-拉卡拉跨境支付平台下发私钥

pubKey-拉卡拉跨境支付平台下发公钥

---

### 商户提交订单

```java
SubmitOrderReq req = new SubmitOrderReq();
req.setCardNo("6222000000000000000");
req.setBgUrl("http://merchantDomain:port/merchantApplicationContext/lklpayResult/handle");
req.setBusiCode(CrossBorderBizType.STUDY_ABROAD_YEAR_BELOW.getCode());
req.setCertType(CertType.ID.getCode());
req.setClientId("321000000000000000");
req.setClientName("孙XX");
req.setCurrency(LklCurrency.CNY.getCode());
req.setCvv("123");
req.setDateOfExpire("1602");
req.setIsMergeSign("1");
req.setMerOrderId("SH20160420194553");
req.setMobile("15000000000");
req.setOrderAmount("223123.09");
req.setOrderEffTime("20160422194550");
req.setOrderSummary("111312");
req.setOrderTime("20160420194550");
req.setPayeeAmount("123123.09");
req.setExt1("1231231你好我");
req.setExt2("中华人民共和国");
//ext中为扩展信息，根据自身业务设置，可不设
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
```


### 确认支付


```java
PaymentReq payOrder = new PaymentReq();
payOrder.setMerOrderId("SH20160420194552");
payOrder.setOrderAmount("223123.09");
payOrder.setCurrency(LklCurrency.CNY.getCode());
payOrder.setPayeeAmount("123123.09");
payOrder.setMsgCode("111111");
payOrder.setTransactionId("2016042100084649");

LklCrossPaySuperReq head = new LklCrossPaySuperReq();
head.setVer("1.0.0");
head.setTs(DateUtil.getCurrentTime());
head.setReqType("B0003");
head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());
PaymentRes res = payClient.pay(payOrder, head);
```

###支付短信验证码重新发送

```java
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
```

###单笔实时收款

```java
LklGatherReq req = new LklGatherReq();

req.setCardNo("6222000000000000000");
req.setBgUrl("http://merchantDomain:port/merchantApplicationContext/lklpayResult/handle");
req.setBusiCode(CrossBorderBizType.STUDY_ABROAD_YEAR_BELOW.getCode());
req.setCertType(CertType.ID.getCode());
req.setClientId("321000000000000000");
req.setClientName("孙XX");
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
//ext中为扩展信息，根据自身业务设置，可不设
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
head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());

LklGatherRes res = payClient.gather(req, head);
```

###单笔实时代付
```java
LklPayAgentReq req = new LklPayAgentReq();

req.setInterBankCode("6222000000000000000");
req.setPayTypeId("5");
req.setBgUrl("http://merchantDomain:port/merchantApplicationContext/lklpayResult/handle");
req.setCertType(CertType.ID.getCode());
req.setClientId("360000000000000000");
req.setAccountName("孙xx");
req.setAccountNumber("6222000000000000000");
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
```

###汇率查询
```java
ExchangeRateQueryReq req = new ExchangeRateQueryReq();
req.setCurrency(LklCurrency.AUD.getCode());

LklCrossPaySuperReq head = new LklCrossPaySuperReq();
head.setVer("1.0.0");
head.setTs(DateUtil.getCurrentTime());
head.setReqType("B0001");
head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());

ExchangeRateQueryRes res = queryClient.exchangeRateQuery(req, head);
```

###订单查询
```java
OrderQueryReq req = new OrderQueryReq();
req.setMerOrderId("SH20160420194553");
req.setTransactionId("2016042100084651");

LklCrossPaySuperReq head = new LklCrossPaySuperReq();
head.setVer("1.0.0");
head.setTs(DateUtil.getCurrentTime());
head.setReqType("B0007");
head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());

OrderQueryRes res = queryClient.orderQuery(req, head);
```

###签约查询
```java
SignQueryReq req = new SignQueryReq();
req.setCardNo("6222000000000000000");
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
```

###单笔实时代付状态查询
```java
AgentPayStatusQueryReq req = new AgentPayStatusQueryReq();
req.setMerOrderId("SH20160420195553");

LklCrossPaySuperReq head = new LklCrossPaySuperReq();
head.setVer("2.0.0");
head.setTs(DateUtil.getCurrentTime());
head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());

AgentPayStatusQueryRes res = queryClient.agentPayStatusQuery(req, head);
```

###商户代付账户余额查询
```java
AgentPayAcctBalanceQueryReq req = new AgentPayAcctBalanceQueryReq();
req.setCurrency(LklCurrency.CNY.getCode());

LklCrossPaySuperReq head = new LklCrossPaySuperReq();
head.setVer("2.0.0");
head.setTs(DateUtil.getCurrentTime());
head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());

AgentPayAcctBalanceQueryRes res = queryClient.agentPayAcctBalanceQuery(req, head);
```

###退款接口
```java
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
```

###用户签约
```java
SignReq req = new SignReq();
req.setCardNo("6222000000000000000");
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
```

###用户签约验证
```java
SignVerifyReq req = new SignVerifyReq();

req.setCardNo("6222000000000000000");
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
```

###对账文件预约下载
```java
ReconSubscribeReq req = new ReconSubscribeReq();
req.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());
req.setStartDate("20160415");
req.setEndDate("20160425");
req.setNotifyAddr("http://merchantDomain:port/merchantApplicationContext/lklreconFile/handle");

LklCrossPaySuperReq head = new LklCrossPaySuperReq();
head.setTs(DateUtil.getCurrentTime());
head.setReqType("B0008");
head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());

ReconSubscribeRes res = payClient.reconSubscribe(req, head);
```

-------

## 处理拉卡拉跨境支付通知
### 若商户选择开启通知响应，即使不实现对应方法sdk会默认响应给拉卡拉跨境为成功。

### 处理支付结果通知
开发者只需实现WebHookHandler接口并实现对应方法即可,bean id需要指定为“lklpayResultHandle”。如果handle方法不抛出异常，则认为是处理成功。sdk响应给拉卡拉跨境为成功。
如：
```java

@Component("lklpayResultHandle")
public class PayResultNotifyImpl implements WebHookHandler<PayResultNotify> {
    private static final Logger logger = LoggerFactory.getLogger(PayResultNotifyImpl.class);

    @Override
    public void handle(PayResultNotify payResultNotify) throws Exception {

         payResultNotify.getPayResult();
         payResultNotify.getCurrency();
         payResultNotify.getExchangeRate();
         payResultNotify.getMerOrderId();
         payResultNotify.getPayAmount();
         payResultNotify.getTransactionId();
                
         /*****************************商户取到通知内容，自行处理业务*******************************************/

    }

}
```
该接口对应的url为:``` 商户域名/商户应用上下文/lklpayResult/handle```

### 处理对账文件下载通知
开发者只需实现WebHookHandler接口并实现对应方法即可,bean id需要指定为“lklreconFileHandle”。如果handle方法不抛出异常，则认为是处理成功。sdk响应给拉卡拉跨境为成功。
如：
```java
@Component("lklreconFileHandle")
public class ReconDown implements WebHookHandler<ReconDownload> {
    private static final Logger logger = LoggerFactory.getLogger(ReconDown.class);

    @Override
    public void handle(ReconDownload reconDownload) throws Exception {
        String fileName = reconDownload.getFileName();
        InputStream in = reconDownload.getIn();

        BufferedInputStream bin = new BufferedInputStream(in);
        byte[] contents = new byte[1024];
        int byteRead = 0;
        String strFileContents;

        while ((byteRead = bin.read(contents)) != -1) {
            strFileContents = new String(contents, 0, byteRead);
            logger.info("file===================={}", strFileContents);
        }
        in.close();
    }
}
```
该接口对应的url为:``` 商户域名/商户应用上下文/lklreconFile/handle```
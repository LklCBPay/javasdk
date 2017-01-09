# Read me

[![Build Status](https://travis-ci.org/LklCBPay/javasdk.svg?branch=master)](https://travis-ci.org/LklCBPay/javasdk)

![v3.3](https://img.shields.io/badge/Version-v3.4-blue.svg)

*有任何问题欢迎提出 [issue](https://github.com/lklcrossboard/javasdk/issues) 或邮件到 <int_bz@lakala.com>*

# 拉卡拉跨境支付平台后台接口java-sdk使用指南
*开发者可将该sdk打成jar包设置在自己项目类路径中，或作为maven的一个模块引入自己项目，以使用该sdk方便接入拉卡拉跨境支付平台后台接口*

**requirment:**

        * java 1.6+
        * spring
        * maven
---
### sdk集成指南
* 在开发者自己项目类路径中添加文件lklconfig.properties。该用文件用于定义httpClient连接池配置项。如下：

```java 
#连接超时时间，毫秒
connectTimeout=15000
#读超时时间，毫秒
readTimeout=15000
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
   * /src/main/java/LklCbPayBatchTradeClient文件中各方法为拉卡拉跨境支付平台批量交易对应的对接方法
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
> `建议在项目启动时做全局唯一性注册,一个应用只能注册一个环境。见LklCrossPayEnv#registerEnv方法`

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
head.setPayTypeId("4");
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
//根据商户开通的产品设置
head.setPayTypeId("4");
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
//根据商户开通的产品设置
head.setPayTypeId("4");
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

## 批量代收
### 申请交易令牌
```java
ApplyTradeTokenReq order = new ApplyTradeTokenReq();

order.setBizType("DS");
order.setFileCount("1");
order.setBizCount("2");
order.setBizAmount("100");
order.setSecCode(DateUtil.getCurrentTime());
//该字段为商户对待上传zip文件内容做MD5后的值，MD5(secCode+内容),参见DigestUtil#fileDigestWithSalt
String digest = DigestUtil.fileDigestWithSalt(order.getSecCode(), "/Users/jiang/test.zip");
order.setDigest(digest);

LklCrossPaySuperReq header = new LklCrossPaySuperReq();
header.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());
header.setTs(DateUtil.getCurrentTime());
header.setVer("3.0.0");

ApplyTradeTokenRes res = batchTradeClient.applyTradeToken(order, header);
```
### 上传文件
```java
BatUploadFileReq req = new BatUploadFileReq();
req.setMerchantId(LklCrossPayEnv.getEnvConfig().getMerId());
//拉卡拉下发的交易令牌
req.setBizToken("e52f8a52248336a28e0dd3678244e19a5d595ff650b7fae0bacb96151d53285d");
//根据拉卡拉下发的批次号,修改本地待上传文件名为:商户号_DS_提交日期_批次号_SRC.zip
req.setFileName("DOPCHN000258_DS_20161031_000005_SRC.zip");
//该secCode为申请批量交易令牌时商户生成的secCode
String secCode = "20161031153500000026";
req.setSecCode(secCode);
//申请批次号所用盐值+商户号+bizToken+文件名
String sign = DigestUtil.Encrypt(secCode + "DOPCHN000258" + "e52f8a52248336a28e0dd3678244e19a5d595ff650b7fae0bacb96151d53285d" + "DOPCHN000258_DS_20161031_000005_SRC.zip", "MD5", "UTF-8");
req.setSign(sign);
req.setFilePath("/Users/jiang/");
BatUploadFileRes res = batchTradeClient.uploadFile(req);
```

### 批量交易状态查询
```java
BatchBizQueryReq req = new BatchBizQueryReq();
req.setBizToekn("dd567cdc9ba594e9479d15b09ff9addc7292eb3e60e45f83fdc1147b8b5b3f7e");

LklCrossPaySuperReq header = new LklCrossPaySuperReq();
header.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());
header.setTs(DateUtil.getCurrentTime());
header.setVer("3.0.0");

try {
    BatchBizQueryRes res = batchTradeClient.batchBizQuery(req, header);
    logger.info("res={}", res);
} catch (Exception e) {
    logger.error(e.getMessage(), e);
```

### (补)回盘文件下载
```java
DownLoadFileReq req = new DownLoadFileReq();
//该令牌为拉卡拉给商户的下载文件通知中给定
req.setDownLoadToken("123123kjn12n31j2k31231o2n912h91b231n");
//true-回盘文件;false-补回盘文件
req.setResFile(true);
//该secCode为拉卡拉给商户的下载文件通知中给定
req.setSecCode("12312nn123n");
//该文件名为拉卡拉给商户的下载文件通知中给定
req.setFileName("test.zip");
//该路径为商户自行设定
req.setLocalFilePath("/Users/jiang/");
//该摘要为拉卡拉给商户的下载文件通知中给定
req.setLklDigest("jnaiusndauwnina182u3102u09");
try {
    batchTradeClient.downLoadLklResFile(req);
} catch (Exception e) {
    logger.error("下载文件失败", e);
}
```
---
## 认证服务
### 认证接口
```java
        AuthReq authReq = new AuthReq();
        //订单号,不可重复
        authReq.setOrderNo("123123123");
        //姓名
        authReq.setName("蒋XX");
        //证件类型,目前默认为00-身份证
        authReq.setCertType("00");
        //证件号
        authReq.setCertNo("321111111111111111");
        //认证类型
        authReq.setBizType("00");

        LklCrossPaySuperReq head = new LklCrossPaySuperReq();
        head.setVer("3.0.0");
        head.setTs(DateUtil.getCurrentTime());
        head.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());

        try {
            AuthRes authRes = authClient.auth(authReq, head);
            logger.info("认证结果{},msg={}", new String[]{authRes.getRetCode(), authRes.getRetMsg()});
        } catch (LklClientException e) {
            logger.error("认证异常", e);
        }
```
---
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
    public void handle(PayResultNotify payResultNotify) throws LklCommonException {

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
    public void handle(ReconDownload reconDownload) throws LklCommonException {
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

### 处理批量交易回调
开发者只需实现WebHookHandler接口并实现对应方法即可,bean id需要指定为“lklBatTradeWebHook”。如果handle方法不抛出异常，则认为是处理成功。sdk响应给拉卡拉跨境为成功。
如：
``` java
@Component("lklBatTradeWebHook")
public class BatTradeWebHook implements WebHookHandler<BatchTradeNotify> {
    private static final Logger logger = LoggerFactory.getLogger(BatTradeWebHook.class);

    public void handle(BatchTradeNotify notifyMsg) throws LklCommonException {
        logger.info("enter menthod BatTradeWebHook.handle,notifyMsg={}", notifyMsg.toString());

        String notifyType = notifyMsg.getNotifyType();
        if (null == notifyType || "".equals(notifyType)) {
            //通过异常传递发送给拉卡拉的消息
            throw new LklCommonException("通知类型-notifyType为空");
        }
        String bizToken = notifyMsg.getBizToken();
        String bizResult = notifyMsg.getBizResult();
        String merchantId = notifyMsg.getMerchantId();

        if ("01".equals(notifyType)) {
            //处理交易结果回调
        } else if ("02".equals(notifyType)) {
            //请求通知中downLoadUrl下载回盘文件
        } else if ("03".equals(notifyType)) {
            ///请求通知中downLoadUrl下载补回盘文件
            final String downLoadUrl = notifyMsg.getDownLoadUrl();
            //此secCode用于校验文件的签名.MD5(secCode+文件内容)
            final String secCode = notifyMsg.getSecCode();
            //文件名
            final String fileName = notifyMsg.getMerchantResFileName();
            //回盘文件摘要MD5(secCode+文件内容)
            final String digest = notifyMsg.getDigest();

            //若有错误则通过异常带出消息,如
            // throw new LklCommonException("XXX失败");
        }
    }

}
```
该接口对应的url为:``` 商户域名:端口/商户应用上下文/lklBatTrade/webHook```。请事先在拉卡拉跨境支付商户自助服务平台中注册回调地址。
如需自己实现回调处理流程，可参照BatchTradeWebHook自行实现;BatTradeWebHook为回调业务处理示例

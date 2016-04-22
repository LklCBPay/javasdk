#Read me

[![Build Status](https://travis-ci.org/lklcrossboard/javasdk.svg?branch=master)](https://travis-ci.org/lklcrossboard/javasdk)

![v3.3](https://img.shields.io/badge/Version-v3.4-blue.svg)

*有任何问题欢迎提出 [issue](https://github.com/lklcrossboard/javasdk/issues)*



## 拉卡拉跨境支付平台后台接口java-sdk使用指南
*开发者可将该sdk打成jar包设置在自己项目类路径中，或作为maven的一个模块引入自己项目，以使用该sdk方便接入拉卡拉跨境支付平台后台接口.sdk需要java1.6+*

----
### sdk项目说明
* 在开发者自己项目类路径中添加文件lklconfig.properties。该用文件用于定义httpClient连接池配置项。如下：

``` 
#连接超时时间，毫秒
connectTimeout=50000
#读超时时间，毫秒
readTimeout=300000
#http连接池最大连接数
maxTotal=200
#每个route的连接数
defaultMaxPerRoute=200
```
* 以上配置可根据自身系统情况自行设定
* 此sdk是maven项目，目录符合maven目录风格。
   * /src/test/java目中的文件为接口测试文件。
   * /src/main/java/LklCbPayClient文件中各方法为拉卡拉跨境支付平台后台接口对应的对接方法


## 接口对接说明
* 开发者在对接之前需注册对接环境

```java
LklCrossPayEnv.registerEnv(LklEnv.SANDBOX, merId, privKey, pubKey);
```

merId-拉卡拉跨境支付平台商户号

privKey-拉卡拉跨境支付平台下发私钥

pubKey-拉卡拉跨境支付平台下发公钥

---

### 商户提交订单

```java

		SubmitOrderReq req = new SubmitOrderReq();
        req.setCardNo("6222021001116245702");
        req.setBgUrl("http://baidu.com");
        req.setBusiCode(CrossBorderBizType.STUDY_ABROAD_YEAR_BELOW.getCode());
        req.setCertType(CertType.ID.getCode());
        req.setClientId("321000000000000000");
        req.setClientName("孙XX");
        req.setCurrency(LklCurrency.CNY.getCode());
        req.setCvv("123");
        req.setDateOfExpire("1602");
        req.setIsMergeSign("1");
        req.setMerOrderId("SH20160420194553");
        req.setMobile("13651234567");
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

        req.setCardNo("6222021001116245702");
        req.setBgUrl("http://baidu.com");
        req.setBusiCode(CrossBorderBizType.STUDY_ABROAD_YEAR_BELOW.getCode());
        req.setCertType(CertType.ID.getCode());
        req.setClientId("321000000000000000");
        req.setClientName("孙XX");
        req.setCurrency(LklCurrency.CNY.getCode());
        req.setCvv("123");
        req.setDateOfExpire("1602");
        req.setMerOrderId("SH20160420194563");
        req.setMobile("13651234567");
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

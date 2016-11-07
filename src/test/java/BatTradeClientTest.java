import com.lakala.crossborder.client.LklCbPayBatchTradeClient;
import com.lakala.crossborder.client.entities.LklCrossPaySuperReq;
import com.lakala.crossborder.client.entities.batchTrade.*;
import com.lakala.crossborder.client.enums.LklEnv;
import com.lakala.crossborder.client.util.DateUtil;
import com.lakala.crossborder.client.util.DigestUtil;
import com.lakala.crossborder.client.util.LklCrossPayEnv;
import com.lakala.crossborder.client.util.webhook.batTrade.BatchTradeWebHook;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 批量交易客户端
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:client-application.xml")
public class BatTradeClientTest {
    private static final Logger logger = LoggerFactory.getLogger(BatTradeClientTest.class);

    @Autowired
    private LklCbPayBatchTradeClient batchTradeClient;

    @Autowired
    private BatchTradeWebHook payResultWebHook;

    @Autowired
    private LklCrossPayEnv lklCrossPayEnv;

    @Before
    public void testSetUp() {
        //注册应用环境
        String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDPg0O4rPQJL1O+jqJ4rBjFVNRAuDmBSoii9pYfPQBaescCVY0irkWWoLyfTT65TjvnPpOx+IfNzBTlB13qCEFm7algREoeUHjFgFNHiXJ2LK/R0+VWgXe5+EDFfbrFCPnmLKG3OcKDGQszP0VOf6VVTM1t56CpgaRMm1/+Tzd2TQIDAQAB";
        String privKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKM28gdn95gwaoSZZno/RNE8v89HZgdXJ+4kjwNTjfcpPJ+WQkvANUWipPO8NSM7v/NagUejWOeJfkusbU9EUbF266ub9nMDy9ukk2rrLGpYgaThptKml0vrrd2yO7MNeKqBNK6+8NSC3mC9rTzTP46USC5A57qzvj1xo+1k0w5XAgMBAAECgYAreapUoLQKHPNyqK3saEf7eDQbkYgy8ltXivNxTmNlb92PIOY96VAHYYCQ0BRTc0n5F6WSIIfA/RXgMGjZkXg039XV3YYbs+oWmcy1miduD7M0Bjgy5nKy+pN6E11LwLXCI71Ny799P3EDWZVK6+PBBrXk0utK3QmTjn600Sr4WQJBAPLLBYfafq3TuErKo6V1rz+exVXvE6VlmfTq3hAqxbSPERA+ntJ/IIsexsVvvMX25rHa43tqI3HVBMJTrq3Auc0CQQCsF8Qxwuwb0AIX0X959ptRLgTJpKLplwk22qDbqcoMrarXQUTkMs57fc3uq2OsV0NSjbyslqBjgE1YoIs1IbSzAkEAvElF2L1RUGzIlvffQQmrBqOSVbo6eiH46z7ZR6BkAkqQ6RAnCwcbrcgITkUBGGUJMxSVJbiCFF0me6154bV8YQJAFz6OjXoakhcicT0mzr50dB6XHvVR2+M+p14YZBMm61d5v+FdAMWsBhoT+qzDpb+TpE7osRcXStlFmTb2FpMa6QJAWFDHy9P0GcgBQhzJZUgYtgQZA483ERjiNLvFUlEWIWalOM/kcLbuQ8DNCboI4K3jIAhrfGsKIYuKFx7AugRa3w==";
        lklCrossPayEnv.registerEnv(LklEnv.SANDBOX, "DOPCHN000258", privKey, pubKey);
    }

    /**
     * 申请批量交易令牌
     */
    @Test
    public void testApplyTradeToken() {
        ApplyTradeTokenReq order = new ApplyTradeTokenReq();

        order.setBizType("DS");
        order.setFileCount("1");
        order.setBizCount("2");
        order.setBizAmount("100");
        order.setSecCode(DateUtil.getCurrentTime());
        //该字段为商户对待上传zip文件内容做MD5后的值，MD5(secCode+内容),参见DigestUtil#fileDigestWithSalt
        String digest = DigestUtil.fileDigestWithSalt(order.getSecCode(), "/Users/jiang/DOPCHN000258_DS_20161031_000005_SRC.zip");
        order.setDigest(digest);

        LklCrossPaySuperReq header = new LklCrossPaySuperReq();
        header.setMerId(LklCrossPayEnv.getEnvConfig().getMerId());
        header.setTs(DateUtil.getCurrentTime());
        header.setVer("3.0.0");

        ApplyTradeTokenRes res = batchTradeClient.applyTradeToken(order, header);
        logger.debug("res={}", res.toString());
    }

    /**
     * 上传批量文件
     */
    @Test
    public void testUoloadFile() {
        BatUploadFileReq req = new BatUploadFileReq();
        req.setMerchantId(LklCrossPayEnv.getEnvConfig().getMerId());
        //拉卡拉下发的交易令牌
        req.setBizToken("e52f8a52248336a28e0dd3678244e19a5d595ff650b7fae0bacb96151d53285d");
        //根据拉卡拉下发的批次号,修改本地待上传文件名为:商户号_DS_提交日期_批次号_SRC.zip
        req.setFileName("DOPCHN000258_DS_20161031_000005_SRC.zip");
        //该secCode为申请批量交易令牌时商户生成的secCode
        String secCode = "20161031153500000026";
        //申请批次号所用盐值+商户号+bizToken+文件名
        String sign = DigestUtil.Encrypt(secCode + "DOPCHN000258" + "e52f8a52248336a28e0dd3678244e19a5d595ff650b7fae0bacb96151d53285d" + "DOPCHN000258_DS_20161031_000005_SRC.zip", "MD5", "UTF-8");
        req.setSign(sign);
        req.setFilePath("/Users/jiang/");
        req.setSecCode(secCode);

        BatUploadFileRes res = batchTradeClient.uploadFile(req);
        logger.debug("res={}", res);
    }


    /**
     * 批量交易状态查询
     */
    @Test
    public void testBatchBizQuery() {
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
        }

    }

    /**
     * 下载（补）回盘文件
     */
    @Test
    public void testDownLoadLklResFile() {
        DownLoadFileReq req = new DownLoadFileReq();
        //该令牌为拉卡拉给商户的下载文件通知中给定
        req.setDownLoadToken("c43963a065e55c6b8e9e91036d0be5b1a4148e62114d76dd9bf3af2bb3776079");
        //true-回盘文件;false-补回盘文件
        req.setResFile(true);
        //该secCode为拉卡拉给商户的下载文件通知中给定
        req.setSecCode("20161104153430871942");
        //该文件名为拉卡拉给商户的下载文件通知中给定
        req.setFileName("test.zip");
        //该路径为商户自行设定
        req.setLocalFilePath("/Users/jiang/");
        //该摘要为拉卡拉给商户的下载文件通知中给定
        req.setLklDigest("ac4b2c3b0afd7270a2eb88fc11e0c666");
        try {
            batchTradeClient.downLoadLklResFile(req);
        } catch (Exception e) {
            logger.error("下载文件失败", e);
        }
    }

    @Test
    public void testWebHook() {
        String j = "{\"encData\":\"B85F29D7516BCABA78CA7824931386BDF533480B61349EBEC20EE92B3A4BDEB7\",\"ts\":\"20161103194200000010\",\"encKey\":\"2EEC3B837A306A55C91C5F905D1950854FB91F357C734893280228368B1BC54BB0F205469E1ABA3AAEB9AC56FF8DEA2CBA4E9052E1C8A1B0CF453ACAC6AC8834CF4CFDE4E8C01F17F9966AB6AE01E53DC4BFC65AFEDFE567F443654BAAC83F027B33C8C2706B27A3F829960FEAB2FF06C89C0A7564690C5C5F21DD4AFD9E2951\",\"reqType\":\"B0005\",\"merId\":\"DOPCHN000258\",\"mac\":\"2B0C8BF7C4B35FFBF77E12BF9D65415930873F0579F962EFE7FB03E95A20EE3D4D01ACAD1C9097F5DB443B2CCFC1C662D894175EB0DF1A2C2624E4823EE04F40A58D3CEE1FF08A18F22881E96966EDD6304EA4BCDE40249B30BFDEABD39D35D0865534764FB6F1BC6DD140A09DFE5F4923368C47EA50719FCD841ED7B774AF6E\",\"ver\":\"3.0.0\"}";
//        Gson json = new GsonBuilder().create();
//        LklCrossPayEncryptReq req = json.fromJson(j, LklCrossPayEncryptReq.class);
//        LklCrossPayEncryptReq req = new LklCrossPayEncryptReq();
//        req.setEncData("B301F1A41A8E373999BE9ECAB59A8D56C99BAB99644BE2E27A697B2484025796");
//        req.setEncKey("523FB8FD1A2BEC29E315349B30BD5B0AFA509C2DAE1D92D54A621498C4EB175639DA58D6A45545139EAAB9233E7DAA04F70EB7472161BAACE4F915700C3DDFBF6064E40800076B619B1F5D37395AF0B0D2972A4DA482055C65BED035DE3735AB882C72901DE1CB9C1906E0E4E4E504CB7EF060FEBC3A2981CF6654122C0AF124");
//        req.setMac("219E3E2C52457CA93629726A939C400EA56752F0DE6A8C6EB2CDBA702F5730001D336FD6E8CF719DC540DCD260206A1727D8558CDFAFDFAFAD07C2D24046AAEF88874DC1E03CE7BC4084D87C6024C18E1B74389FE7FB05E8AEB28E489077038CA02A4FCF9C8EA28A9DC2255E300DBEFAF73A89A07FD7519EC038AC7056950F7F");
//        req.setMerId("DOPCHN000258");
//        req.setReqType("B0005");
//        req.setTs("20161103184400000043");
//        req.setVer("3.0.0");

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost();
//			StringBody content = new StringBody(reqJson,"application/json;charset=UTF-8", Charset.forName("utf-8"));
        String notifyUrl = "http://10.7.37.99:8080/lklBatTrade/webHook";
        try {
            StringEntity content = new StringEntity(j, "application/json;charset=UTF-8", "utf-8");
            RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(60000).build();
            httpPost.setConfig(config);
            httpPost.setEntity(content);

            httpPost.setURI(new URI(notifyUrl));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            response.getStatusLine().getStatusCode();
            InputStream in = null;
            BufferedInputStream bin = null;
            in = response.getEntity().getContent();
            bin = new BufferedInputStream(in);
            byte[] contents = new byte[1024];
            int byteRead = 0;
            StringBuffer strFileContents = new StringBuffer();
            while ((byteRead = bin.read(contents)) != -1) {
                strFileContents.append(new String(contents, 0, byteRead));
            }
            logger.info("res====================" + strFileContents.toString());
        } catch (URISyntaxException e) {
            logger.error("商户注册回调地址失败,url=" + notifyUrl, e);

        } catch (ClientProtocolException e) {
            logger.error("商户注册回调地址失败,url=" + notifyUrl, e);

        } catch (IOException e) {
            logger.error("商户注册回调地址失败,url=" + notifyUrl, e);

        }


//        LklCrossPayEncryptRes res = payResultWebHook.proceed(req);
//
//        logger.info("res={}", json.toJson(res));
    }
}

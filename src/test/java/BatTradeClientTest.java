import com.lakala.crossborder.client.LklCbPayBatchTradeClient;
import com.lakala.crossborder.client.entities.LklCrossPaySuperReq;
import com.lakala.crossborder.client.entities.batchTrade.*;
import com.lakala.crossborder.client.enums.LklEnv;
import com.lakala.crossborder.client.util.DateUtil;
import com.lakala.crossborder.client.util.DigestUtil;
import com.lakala.crossborder.client.util.LklCrossPayEnv;
import com.lakala.crossborder.client.util.webhook.batTrade.BatchTradeWebHook;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

}

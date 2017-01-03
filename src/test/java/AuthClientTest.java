import com.lakala.crossborder.client.AuthClient;
import com.lakala.crossborder.client.entities.LklCrossPaySuperReq;
import com.lakala.crossborder.client.entities.auth.AuthReq;
import com.lakala.crossborder.client.entities.auth.AuthRes;
import com.lakala.crossborder.client.enums.LklEnv;
import com.lakala.crossborder.client.exception.LklClientException;
import com.lakala.crossborder.client.util.DateUtil;
import com.lakala.crossborder.client.util.LklCrossPayEnv;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jiang on 2017/1/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:client-application.xml")
public class AuthClientTest {
    private static final Logger logger = LoggerFactory.getLogger(LklCrossPayClientTest.class);

    @Autowired
    private LklCrossPayEnv lklCrossPayEnv;

    @Autowired
    private AuthClient authClient;

    @Before
    public void testSetUp() {
        //注册应用环境
        String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDPg0O4rPQJL1O+jqJ4rBjFVNRAuDmBSoii9pYfPQBaescCVY0irkWWoLyfTT65TjvnPpOx+IfNzBTlB13qCEFm7algREoeUHjFgFNHiXJ2LK/R0+VWgXe5+EDFfbrFCPnmLKG3OcKDGQszP0VOf6VVTM1t56CpgaRMm1/+Tzd2TQIDAQAB";
        String privKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIbyEihcDZbLfFhVpRU0knOtlzBhuWEjLBN4zd8dVzqAq52zWR04ybgZjjtlw+R9ICeJigmw2Dt3yy8SjpyMu5rYurTg/V0rGwayEuuE7aazi50nhZ/GYFRUVGIG0BoCI+73vsUmCO5EaUIJP/E0ew1bLMV7SMPynoQ8G9jXj8rXAgMBAAECgYAmfpFNcAz0UjGzZSMFbIzGcONrCsV9/zGIkHJxzgXfC2tpPgsSuetZF/kp2nrKCCOPA74by5WzSRXt5KZH5CFzuwwqIBv5cn6AiKiVmro3AnHV+qqNtfGXwLottu0VYcBEY2IPKb7SQ4pP5I0H973hnxR4qRzMAMUmEBVMiuUR+QJBAObLUBTjd7AE4Sls3/vBsYsYLYFfmw/dRHaW6jnuNxPTfVtPTGjY3V/672Vs01/f6QdtHHpMN+2xUk+acErAJTUCQQCVrvakm5R2sQwPycSO4mZW5sHKGbBosAkcY4llISVtxzSjgkPtBPVukCdNSGTuJX7+Ci8KolilrCc2XQCkH21bAkEAlcUAXd3TEL3J5BkMLRLgBTSWaytAtAXR5OdAboGA6nPHGJcYLb31wtBTxEzfyorCbRhIb7DAZpY4pQHCty+DtQJATFwCdPztYxN03MUIof+7R4/WwpwSU4WiUDozCEU9i+A46UT2E/8YmbuuYQ2Sd67nNv/I+brSUEofgus0/YUOywJBAMPu5o9guMsAjVqvXuxbkFpblYGZO/BrwiuLGDWEj4DZFqrIDmqgA6edy3HSoZ7U69BJZLTPb9DeebEiebmJZUI=";
        lklCrossPayEnv.registerEnv(LklEnv.SANDBOX, "RZCP161200000", privKey, pubKey);
    }

    @org.junit.Test
    public void testAuth() {
        AuthReq authReq = new AuthReq();
        //订单号,不可重复
        authReq.setOrderNo("123123123");
        //姓名
        authReq.setName("蒋志飞");
        //证件类型,目前默认为00-身份证
        authReq.setCertType("00");
        //证件号
        authReq.setCertNo("321088198308252015");
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
    }
}

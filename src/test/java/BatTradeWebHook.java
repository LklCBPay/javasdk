import com.lakala.crossborder.client.entities.webHook.BatchTradeNotify;
import com.lakala.crossborder.client.exception.LklCommonException;
import com.lakala.crossborder.client.util.webhook.WebHookHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 批量交易回调业务逻辑处理
 * <p>
 * 该代码用于商户处理处理拉卡拉批量交易回调消息的业务逻辑。该代码仅用于示例，供参照使用。
 * <p/>
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
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
//        String merchantId = notifyMsg.getMerchantId();

        if ("1".equals(notifyType)) {
            //处理交易结果回调
            String bizResult = notifyMsg.getBizResult();
        } else if ("2".equals(notifyType)) {
            //此secCode用于校验文件的签名.MD5(secCode+文件内容)
            String secCode = notifyMsg.getSecCode();
            //文件名
            String fileName = notifyMsg.getMerchantResFileName();
            //回盘文件摘要MD5(secCode+文件内容)
            String digest = notifyMsg.getDigest();
            //文件下载token,凭此token下载回盘文件
            String token = notifyMsg.getDownLoadToken();

            //若有错误则通过异常带出消息,如
            // throw new LklCommonException("XXX失败");
        } else if ("3".equals(notifyType)) {
            //此secCode用于校验文件的签名.MD5(secCode+文件内容)
            String secCode = notifyMsg.getSecCode();
            //文件名
            String fileName = notifyMsg.getMerchantResFileName();
            //回盘文件摘要MD5(secCode+文件内容)
            String digest = notifyMsg.getDigest();
            //文件下载token,凭此token下载回盘文件
            String token = notifyMsg.getDownLoadToken();

            //若有错误则通过异常带出消息,如
            // throw new LklCommonException("XXX失败");
        }
    }

}

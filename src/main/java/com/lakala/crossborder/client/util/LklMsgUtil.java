package com.lakala.crossborder.client.util;

import com.google.gson.Gson;
import com.lakala.crossborder.client.entities.LklCrossPayEncryptReq;
import com.lakala.crossborder.client.entities.LklCrossPayEncryptRes;
import com.lakala.crossborder.client.entities.LklCrossPaySuperReq;
import com.lakala.crossborder.client.entities.LklCrossPaySuperRes;
import com.lakala.crossborder.client.exception.LklEncryptException;
import com.lakala.crossborder.client.util.webhook.SuperWebHookRequest;
import com.lakala.crossborder.client.util.webhook.SuperWebHookResponse;

import java.io.UnsupportedEncodingException;

/**
 * <p>
 * 拉卡拉跨境支付消息工具类
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class LklMsgUtil {


    /**
     * <p>
     * 加密请求参数
     * encrypt the msg sent to lakala
     * </p>
     *
     * @param obj      参数对象
     * @param dataHead 报文头
     * @param <T>
     * @return LklCrossPayEncryptReq 返回一个加密后的请求参数，用于向拉卡拉跨境支付平台发起请求
     * @throws LklEncryptException
     */
    public static <T> LklCrossPayEncryptReq encryptMsg(T obj, LklCrossPaySuperReq dataHead) throws LklEncryptException {
        ToolsUtil.remove();
        String publicKey = LklCrossPayEnv.getEnvConfig().getPublicKey();
        String privateKey = LklCrossPayEnv.getEnvConfig().getPrivateKey();
        String merId = LklCrossPayEnv.getEnvConfig().getMerId();
        String ts = dataHead.getTs();
        //生成32位随机串
        String desKey = ToolsUtil.getMerKey();
        //时间戳拼接对称密钥的hex，用响应方公钥加密，生成加密密钥密文，hex编码,生成encKey
        String encKeyStr = ts + desKey;
        String encKey = null;
        try {
            encKey = ByteArrayUtil.byteArray2HexString(RSAUtil.encryptByPublicKey(encKeyStr.getBytes("GBK"), publicKey));
        } catch (Exception e) {
            throw new LklEncryptException("生成encKey失败", e);
        }
        Gson json = new Gson();
        String bizJson = json.toJson(obj);
        //生成encData
        String encData;
        try {
            encData = ByteArrayUtil.byteArray2HexString(DESCrypto.enCrypto(bizJson.getBytes("GBK"), desKey));
        } catch (UnsupportedEncodingException e) {
            throw new LklEncryptException("加密业务参数失败", e);
        }
        String ver = dataHead.getVer();
        String macStr = null;
        if ("1.0.0".equals(ver.trim())) {
            String reqType = dataHead.getReqType();
            String payTypeId = dataHead.getPayTypeId();
            if (null == payTypeId || "".equals(payTypeId)) {
                macStr = merId + ver + ts + reqType + encData + "";
            } else {
                macStr = merId + ver + ts + reqType + encData + payTypeId;
            }
        } else if ("2.0.0".equals(ver.trim())) {
            macStr = merId + ver + ts + encData;
        }
        //SHA-1
        macStr = DigestUtil.Encrypt(macStr, "SHA-1");
        //生成MAC
        String mac = null;
        try {
            mac = ByteArrayUtil.byteArray2HexString(RSAUtil.encryptByPrivateKey(macStr.getBytes("GBK"), privateKey));
        } catch (Exception e) {
            throw new LklEncryptException(e);
        }

        //组装加密请求参数
        LklCrossPayEncryptReq req = new LklCrossPayEncryptReq();
        req.setMerId(merId);
        req.setPayTypeId(dataHead.getPayTypeId());
        req.setVer(dataHead.getVer());
        req.setTs(dataHead.getTs());
        req.setEncData(encData);
        req.setEncKey(encKey);
        req.setMac(mac);
        req.setReqType(dataHead.getReqType());

        return req;
    }

    /**
     * <p>
     * 解密拉卡拉响应报文
     * decrypt the response msg back from lakala.the des key should be the same to which used to encrypt the msg sent to lakala
     * </p>
     *
     * @param encryptRes 拉卡拉加密响应报文
     * @param <T>        拉卡拉接口业务参数,扩展自父类LklCrossPaySuperRes {@link LklCrossPaySuperRes}
     * @return
     * @throws LklEncryptException
     */
    public static <T extends LklCrossPaySuperRes> T decrypt(LklCrossPayEncryptRes encryptRes, Class<T> resClazz) throws LklEncryptException {
        T result = null;
        String retCode = encryptRes.getRetCode();
        String retMsg = encryptRes.getRetMsg();
        String merId = encryptRes.getMerId();
        String ver = encryptRes.getVer();
        String ts = encryptRes.getTs();
        String encData = encryptRes.getEncData();
        String reqType = encryptRes.getReqType();
        String payTypeId = encryptRes.getPayTypeId();
        String mac = encryptRes.getMac();
        //本地mac sha1字符串
        String retMacStr = null;
        //请求参数mac解密字符串sha1
        String reqMacStr = null;
        if ("1.0.0".equals(ver.trim())) {
            if (null == payTypeId || "".equals(payTypeId.trim())) {
                retMacStr = DigestUtil.Encrypt(retCode + retMsg + merId + ver + ts + reqType + encData + "", "SHA-1");
            } else {
                retMacStr = DigestUtil.Encrypt(retCode + retMsg + merId + ver + ts + reqType + encData + payTypeId, "SHA-1");
            }
        } else if ("2.0.0".equals(ver.trim())) {
            retMacStr = DigestUtil.Encrypt(retCode + retMsg + merId + ver + ts + encData, "SHA-1");
        }
        try {
            reqMacStr = new String(RSAUtil.decryptByPublicKey(ByteArrayUtil.hexString2ByteArray(mac), LklCrossPayEnv.getEnvConfig().getPublicKey()));
        } catch (Exception e) {
            throw new LklEncryptException("mac解密失败", e);
        }
        //比较报文中mac sha1与本地mac sha1是否相等
        if (!reqMacStr.equals(retMacStr)) {
            throw new LklEncryptException("mac校验失败");
        }
        //解密业务参数
        String key = ToolsUtil.getMerKey();
        String requestData = null;

        try {
            requestData = new String(DESCrypto.deCrypt(ByteArrayUtil.hexString2ByteArray(encData), key), "GBK");
        } catch (UnsupportedEncodingException e) {
            throw new LklEncryptException("业务参数解密失败", e);
        }

        Gson json = new Gson();
        result = json.fromJson(requestData, resClazz);
        ToolsUtil.remove();
        return result;
    }


    /**
     * 解密拉卡拉回调消息
     * decrypt the webhook msg from lakala,des key should  get from the method :getMerchantKey
     *
     *
     * @param lklCrossPayEncryptReq
     * @param resClazz
     * @param <T>
     * @return
     */
    public static <T extends SuperWebHookRequest> T decryptMsgFromLkl(LklCrossPayEncryptReq lklCrossPayEncryptReq, Class<T> resClazz) {
        T result = null;
        String ts = lklCrossPayEncryptReq.getTs();
        String encData = lklCrossPayEncryptReq.getEncData();
        String reqType = lklCrossPayEncryptReq.getReqType();
        String mac = lklCrossPayEncryptReq.getMac();

        //请求参数mac解密字符串sha1
        String reqMacStr = null;
        //本地mac sha1字符串
        String retMacStr = DigestUtil.Encrypt(ts + reqType + encData + "", "SHA-1");
        try {
            reqMacStr = new String(RSAUtil.decryptByPublicKey(ByteArrayUtil.hexString2ByteArray(mac), LklCrossPayEnv.getEnvConfig().getPublicKey()));
        } catch (Exception e) {
            throw new LklEncryptException("mac解密失败", e);
        }
        if (!retMacStr.equals(reqMacStr)) {
            throw new LklEncryptException("mac校验失败");
        }
        //用响应方私钥解密加密密钥密文，比对时间戳，取后32个字符反HEX，得对称密钥
        String encKey = lklCrossPayEncryptReq.getEncKey();
        String desKey = getMerchantKey(encKey, LklCrossPayEnv.getEnvConfig().getPrivateKey());
        ToolsUtil.remove();
        ToolsUtil.setMerKey(desKey);
        //用获得的对称密钥解密加密业务参数
        String requestData = null;
        try {
            requestData = new String(DESCrypto.deCrypt(ByteArrayUtil.hexString2ByteArray(encData), desKey), "GBK");
        } catch (UnsupportedEncodingException e) {
            throw new LklEncryptException("业务参数解密失败", e);
        }
        Gson json = new Gson();
        result = json.fromJson(requestData, resClazz);
        return result;

    }

    /**
     * 加密拉卡拉webhook响应返回给拉卡拉
     * encrypt the webhook response msg sent back to lakala
     *
     * @param webHookResponse
     * @return
     */
    public static LklCrossPayEncryptRes encryptWebHookMsg(SuperWebHookResponse webHookResponse, LklCrossPayEncryptRes encryptRes) {
        String publicKey = LklCrossPayEnv.getEnvConfig().getPublicKey();
        String privateKey = LklCrossPayEnv.getEnvConfig().getPrivateKey();
        String ts = encryptRes.getTs();
        String desKey = ToolsUtil.getMerKey();
        String encKeyStr = ts + desKey;
        String encKey = null;
        try {
            encKey = ByteArrayUtil.byteArray2HexString(RSAUtil.encryptByPublicKey(encKeyStr.getBytes("GBK"), publicKey));
        } catch (Exception e) {
            throw new LklEncryptException("生成encKey失败", e);
        }
        Gson json = new Gson();
        String bizJson = json.toJson(webHookResponse);
        //生成encData
        String encData;
        try {
            encData = ByteArrayUtil.byteArray2HexString(DESCrypto.enCrypto(bizJson.getBytes("GBK"), desKey));
        } catch (UnsupportedEncodingException e) {
            throw new LklEncryptException("加密业务参数失败", e);
        }
        String reqType = encryptRes.getReqType();
        String macStr = ts + reqType + encData;
        //SHA-1
        macStr = DigestUtil.Encrypt(macStr, "SHA-1");
        //生成MAC
        String mac = null;
        try {
            mac = ByteArrayUtil.byteArray2HexString(RSAUtil.encryptByPrivateKey(macStr.getBytes("GBK"), privateKey));
        } catch (Exception e) {
            throw new LklEncryptException(e);
        }
        encryptRes.setMac(mac);
        encryptRes.setEncKey(encKey);
        encryptRes.setEncData(encData);
        ToolsUtil.remove();
        return encryptRes;
    }

    /**
     * 计算请求方对称密钥
     *
     * @param reqEncKey  加密密钥encKey
     * @param privateKey 私钥
     * @return
     */
    private final static String getMerchantKey(String reqEncKey, String privateKey) {

        if (reqEncKey == null || privateKey == null) return null;
        // 用响应方私钥解密加密密钥密文，比对时间戳，取后32个字符反HEX，得对称密钥
        String merKey = ""; // 商户对称密钥
        try {
            merKey = new String(RSAUtil.decryptByPrivateKey(ByteArrayUtil.hexString2ByteArray(reqEncKey), privateKey), "GBK");
        } catch (Exception e) {
            throw new LklEncryptException("解密请求方对称密钥失败", e);
        }
        return merKey.substring(merKey.length() - 32, merKey.length());
    }
}

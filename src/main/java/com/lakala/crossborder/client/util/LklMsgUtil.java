package com.lakala.crossborder.client.util;

import com.google.gson.Gson;
import com.lakala.crossborder.client.entities.LklCrossPayEncryptReq;
import com.lakala.crossborder.client.entities.LklCrossPayEncryptRes;
import com.lakala.crossborder.client.entities.LklCrossPaySuperReq;
import com.lakala.crossborder.client.entities.LklCrossPaySuperRes;
import com.lakala.crossborder.client.exception.LklEncryptException;

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

}

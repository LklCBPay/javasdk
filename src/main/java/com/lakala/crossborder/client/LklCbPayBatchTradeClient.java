package com.lakala.crossborder.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lakala.crossborder.client.entities.LklCrossPayEncryptReq;
import com.lakala.crossborder.client.entities.LklCrossPayEncryptRes;
import com.lakala.crossborder.client.entities.LklCrossPaySuperReq;
import com.lakala.crossborder.client.entities.batchTrade.*;
import com.lakala.crossborder.client.exception.LklClientException;
import com.lakala.crossborder.client.exception.LklCommonException;
import com.lakala.crossborder.client.util.DigestUtil;
import com.lakala.crossborder.client.util.LklCrossPayEnv;
import com.lakala.crossborder.client.util.LklCrossPayRestfulClent;
import com.lakala.crossborder.client.util.LklMsgUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

/**
 * 批量交易
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
@Component("LklBatchTradeClient")
public class LklCbPayBatchTradeClient {
    private static final Logger logger = LoggerFactory.getLogger(LklCbPayBatchTradeClient.class);

    @Autowired
    private LklCrossPayRestfulClent payRestfulClent;

    /**
     * 申请批量交易令牌
     *
     * @param order    报文体
     * @param dataHead 报文头
     * @return
     * @throws LklClientException
     */
    public ApplyTradeTokenRes applyTradeToken(ApplyTradeTokenReq order, LklCrossPaySuperReq dataHead) {
        ApplyTradeTokenRes res = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(order, dataHead);

        try {
            LklCrossPayEncryptRes encryptRes = payRestfulClent.doPost(LklCrossPayEncryptRes.class, req, "/gate/applyBatchToken");
            if ("0000".equals(encryptRes.getRetCode())) {
                res = LklMsgUtil.decrypt(encryptRes, ApplyTradeTokenRes.class);
            } else {
                res = new ApplyTradeTokenRes();
            }
            res.setRetCode(encryptRes.getRetCode());
            res.setRetMsg(encryptRes.getRetMsg());
            res.setVer(encryptRes.getVer());
            res.setPayTypeId(encryptRes.getPayTypeId());
            res.setReqType(encryptRes.getReqType());
            res.setTs(encryptRes.getTs());
            res.setMerId(encryptRes.getMerId());
        } catch (Exception e) {
            logger.error("批量交易-申请批量交易令牌异常,secCode=" + order.getSecCode(), e);
            throw new LklClientException(e.getMessage(), e);
        }
        return res;
    }

    /**
     * 上传批量文件
     *
     * @param order
     * @return BatUploadFileRes
     * throws LklClientException
     */
    public BatUploadFileRes uploadFile(BatUploadFileReq order) {
        logger.info("start uplading batch file to lakala,file={},bizToken={}", new String[]{order.getFileName(), order.getBizToken()});
        BatUploadFileRes res = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        BufferedReader bufferedReader = null;
        String filePath = order.getFilePath();
        String fileName = order.getFileName();

        try {
            File file = new File(filePath + fileName);
            FileEntity fileEntity = new FileEntity(file);
            fileEntity.setContentEncoding("UTF-8");
            fileEntity.setChunked(false);
            fileEntity.setContentType("binary/octet-stream");
            String reqUrl = LklCrossPayEnv.getEnvConfig().getServer() + ":" + LklCrossPayEnv.getEnvConfig().getPort() + "/gate/batchBizFileUpload/" + order.getMerchantId() + "/" + order.getFileName() + "/" + order.getBizToken() + "/" + order.getSign();
            HttpPost httpPost = new HttpPost(reqUrl);
            httpPost.setEntity(fileEntity);


            CloseableHttpResponse response = httpClient.execute(httpPost);
            logger.info("uploaded file to url={},and filePath={}", new String[]{
                    reqUrl, filePath
            });
            int httpStatus = response.getStatusLine().getStatusCode();

            HttpEntity entity = response.getEntity();
            long contentLen = entity.getContentLength();
            StringBuffer stringBuffer = null;
            if (null != entity) {
                bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                String text;
                stringBuffer = new StringBuffer();
                while ((text = bufferedReader.readLine()) != null) {
                    stringBuffer.append(text).append("\r\n");
                }
            }
            logger.info("response from lakala for upload batch file is {},", new Object[]{stringBuffer.toString()});

            if (200 == httpStatus) {
                Gson json = new GsonBuilder().create();
                BatUploadFileRes uploadFileRes = json.fromJson(stringBuffer.toString(), BatUploadFileRes.class);

                String retCode = uploadFileRes.getRetCode();
                String rerMsg = uploadFileRes.getRetMsg();
                String sign = uploadFileRes.getSign();
                String secCode = order.getSecCode();
                //验证返回参数签名
                String localSign = DigestUtil.Encrypt(secCode + retCode + rerMsg, "MD5", "utf-8");
                if (!localSign.equals(sign)) {
                    logger.warn("批量交易-上传批量文件拉卡拉响应签名验证不通过");
                    throw new LklClientException("批量交易-上传批量文件拉卡拉响应签名验证不通过,bizToken=" + order.getBizToken() + ",file=" + fileName + "http status=" + httpStatus + ",responseTxt=" + stringBuffer.toString());
                }

            } else {
                logger.warn("response status from lakala is {},and responseTxt is {}", new Object[]{httpStatus, stringBuffer.toString()});
                throw new LklClientException("批量交易-上传批量文件异常,bizToken=" + order.getBizToken() + ",file=" + fileName + "http status=" + httpStatus + ",responseTxt=" + stringBuffer.toString());
            }
        } catch (Exception e) {
            logger.error("批量交易-上传批量文件异常,bizToken=" + order.getBizToken() + ",file=" + fileName, e);
            throw new LklClientException("批量交易-上传批量文件异常,bizToken=" + order.getBizToken() + ",file=" + fileName, e);
        } finally {
            try {
                httpClient.close();
                if (null != bufferedReader)
                    bufferedReader.close();
            } catch (IOException e) {
                logger.error("批量交易-上传批量文件异常,bizToken=" + order.getBizToken() + ",file=" + fileName, e);
                throw new LklClientException("批量交易-上传批量文件异常,bizToken=" + order.getBizToken() + ",file=" + fileName, e);
            }
        }
        return res;
    }

    /**
     * 下载拉卡拉（补）回盘文件,若写文件失败则会抛出异常
     *
     * @param req
     * @throws LklClientException
     */
    public void downLoadLklResFile(DownLoadFileReq req) {
        logger.info("开始下载文件,req={}", req.toString());
        String downLoadUrl = LklCrossPayEnv.getEnvConfig().getServer() + ":" + LklCrossPayEnv.getEnvConfig().getPort();
        //回盘
        if (req.isResFile()) {
            downLoadUrl += "/fileService/downLoadBckFile/" + req.getDownLoadToken();
        } else {
            //补回盘
            downLoadUrl += "/fileService/downLoadPlusBck/" + req.getDownLoadToken();
        }

        String secCode = req.getSecCode();
        String fileName = req.getFileName();
        String filePath = req.getLocalFilePath();
        String lklDigest = req.getLklDigest();

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet();
        BufferedInputStream bin = null;
        FileWriter fw = null;
        FileOutputStream fo = null;
        try {

            httpGet.setURI(new URI(downLoadUrl));
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int httpStatus = response.getStatusLine().getStatusCode();

            if (null != response.getHeaders("retCode") && response.getHeaders("retCode").length > 0) {
                String retCode = response.getHeaders("retCode")[0].getValue();
                String retMsg = URLEncodedUtils.parse(response.getHeaders("retMsg")[0].getValue(), Charset.forName("utf-8")).toString();
                if (!"0000".equals(retCode)) {
                    logger.warn("下载拉卡拉回盘文件失败,downLoadUrl=" + downLoadUrl + ",文件名为:" + fileName + ",拉卡拉服务响应=" + retMsg + ",retCode=" + retCode);
                    throw new LklCommonException("下载拉卡拉回盘文件失败,downLoadUrl=" + downLoadUrl + ",文件名为:" + fileName + ",拉卡拉服务响应=" + retMsg + ",retCode=" + retCode);
                }
            }

            if (200 != httpStatus) {
                throw new LklClientException("下载拉卡拉回盘文件异常,downLoadUrl=" + downLoadUrl + ",文件名为:" + fileName + ",拉卡拉服务响应=" + httpStatus);
            }

            bin = new BufferedInputStream(response.getEntity().getContent());
            fo = new FileOutputStream(filePath + fileName);
            byte[] contents = new byte[1024];
            int byteRead = 0;
            while ((byteRead = bin.read(contents)) != -1) {
                fo.write(contents, 0, byteRead);

            }
            //计算下载文件的摘要
            String localDigest = DigestUtil.fileDigestWithSalt(secCode, filePath + fileName);
            //比较文件摘要是否与拉卡拉消息中一致

            // ------------------------此处商户可以自行修改，是否对摘要进行判定。建议进行判定！！！
            if (!localDigest.equals(lklDigest)) {
                logger.warn("digest of {} is not correct", fileName);
                logger.debug("localDigest of {} is {}", new String[]{fileName, localDigest});
                logger.debug("lakalaDigest of {} is {}", new String[]{fileName, lklDigest});
                throw new LklClientException("回盘文件 " + fileName + "摘要验证失败");
            }
            // ------------------------此处商户可以自行修改，是否对摘要进行判定。建议进行判定！！！

            logger.info("{} download completed", fileName);
        } catch (LklCommonException e) {
            logger.error("下载拉卡拉回盘文件异常,downLoadUrl=" + downLoadUrl + ",文件名为:" + fileName, e);
            throw new LklClientException(e);
        } catch (ClientProtocolException e) {
            logger.error("下载拉卡拉回盘文件异常,downLoadUrl=" + downLoadUrl + ",文件名为:" + fileName, e);
            throw new LklClientException("下载拉卡拉回盘文件异常,downLoadUrl=" + downLoadUrl + ",文件名为:" + fileName, e);
        } catch (IOException e) {
            logger.error("下载拉卡拉回盘文件异常,downLoadUrl=" + downLoadUrl + ",文件名为:" + fileName, e);
            throw new LklClientException("下载拉卡拉回盘文件异常,downLoadUrl=" + downLoadUrl + ",文件名为:" + fileName, e);
        } catch (URISyntaxException e) {
            logger.error("下载拉卡拉回盘文件异常,downLoadUrl=" + downLoadUrl + ",文件名为:" + fileName, e);
            throw new LklClientException("下载拉卡拉回盘文件异常,downLoadUrl=" + downLoadUrl + ",文件名为:" + fileName, e);
        } catch (Exception e) {
            logger.error("下载拉卡拉回盘文件异常,downLoadUrl=" + downLoadUrl + ",文件名为:" + fileName, e);
            throw new LklClientException("下载拉卡拉回盘文件异常,downLoadUrl=" + downLoadUrl + ",文件名为:" + fileName, e);
        } finally {
            try {
                httpClient.close();
                if (null != fo)
                    fo.close();
                if (null != bin)
                    bin.close();
            } catch (IOException e) {
                logger.error("下载拉卡拉回盘文件异常,downLoadUrl=" + downLoadUrl + ",文件名为:" + fileName, e);
                throw new LklClientException("下载拉卡拉回盘文件异常,downLoadUrl=" + downLoadUrl + ",文件名为:" + fileName, e);
            }
        }
    }

    /**
     * 查询批量交易状态
     *
     * @param order
     * @param dataHead
     * @return
     * @throws LklClientException
     */
    public BatchBizQueryRes batchBizQuery(BatchBizQueryReq order, LklCrossPaySuperReq dataHead) throws LklClientException {
        BatchBizQueryRes res = null;
        LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(order, dataHead);
        try {
            LklCrossPayEncryptRes encryptRes = payRestfulClent.doPost(LklCrossPayEncryptRes.class, req, "/gate/batchBizQuery");
            if ("0000".equals(encryptRes.getRetCode())) {
                res = LklMsgUtil.decrypt(encryptRes, BatchBizQueryRes.class);
            } else {
                res = new BatchBizQueryRes();
            }
            res.setRetCode(encryptRes.getRetCode());
            res.setRetMsg(encryptRes.getRetMsg());
            res.setVer(encryptRes.getVer());
            res.setPayTypeId(encryptRes.getPayTypeId());
            res.setReqType(encryptRes.getReqType());
            res.setTs(encryptRes.getTs());
            res.setMerId(encryptRes.getMerId());
        } catch (Exception e) {
            logger.error("批量交易-批量交易查询异常,bizToken=" + order.getBizToekn(), e);
            throw new LklClientException(e.getMessage(), e);
        }
        return res;
    }

}

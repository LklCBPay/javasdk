package com.lakala.crossborder.client.util.webhook.recon;

import com.lakala.crossborder.client.entities.notify.ReconDownload;
import com.lakala.crossborder.client.util.webhook.WebHookHandler;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;

/**
 * Created by jiang on 16/4/26.
 */
@Controller
public class LklReconDownload {

    private static final Logger logger = LoggerFactory.getLogger(LklReconDownload.class);

    @Autowired(required = false)
    @Qualifier("lklreconFileHandle")
    private WebHookHandler<ReconDownload> webHookIntf;

    @RequestMapping(value = "/lklreconFile/handle", method = RequestMethod.POST)
    @ResponseBody
    public String proceed(@RequestBody String notify) {
        notify = URLDecoder.decode(notify);
        logger.info("LklReconDownload,notify from lakala={}", notify);
        if (null == notify || "".equals(notify)) {
            logger.info("LklReconDownload------收到通知为空");
            return null;
        }

        ReconDownload downloadUrl = new ReconDownload();
        String[] params = notify.split("&");
        if (null == params || params.length <= 0) {
            logger.info("LklReconDownload------收到通知格式不正确");
            return null;
        }
        String[] url = params[0].split("[=]");
        if (null == url || url.length <= 2) {
            logger.info("LklReconDownload------收到通知格式不正确");
            return null;
        }
        String downUrl = url[1] + "=" + url[2];
        if (null == downUrl || "".equals(downUrl)) {
            logger.info("LklReconDownload------收到通知格式不正确");
            return null;
        }
        downloadUrl.setFileName(url[2]);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet();
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(10000)
                .setSocketTimeout(30000)
                .setConnectionRequestTimeout(5000)
                .build();
        httpGet.setConfig(config);
        try {
            httpGet.setURI(new URI(downUrl.trim()));
            CloseableHttpResponse response = httpClient.execute(httpGet);
            downloadUrl.setIn(response.getEntity().getContent());
            if (params.length > 1) {
                if (null != params[1].split("=") && params[1].split("=").length > 1) {
                    downloadUrl.setPrivData(params[1].split("=")[1]);
                }
            }
            logger.info("downloadUrl,fileName={},url={}", new String[]{downloadUrl.getFileName(), downUrl});
            webHookIntf.handle(downloadUrl);

            response.close();
            return "OK";
        } catch (URISyntaxException e) {
            logger.error("downLoad recon file error", e);
        } catch (ClientProtocolException e) {
            logger.error("downLoad recon file error", e);
        } catch (IOException e) {
            logger.error("downLoad recon file error", e);
        } catch (Exception e) {
            logger.error("downLoad recon file error", e);
        } finally {
            try {
                if (null != httpClient) {
                    httpClient.close();
                }
            } catch (IOException e) {
                logger.error("close httpClient error", e);
            }
        }
        return "BAD PROGRAM";
    }

}

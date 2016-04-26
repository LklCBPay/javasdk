package com.lakala.crossborder.client.entities.notify;

import com.lakala.crossborder.client.util.webhook.SuperWebHookRequest;

import java.io.InputStream;

/**
 * Created by jiang on 16/4/26.
 */
public class ReconDownload extends SuperWebHookRequest {

    private InputStream in;

    private String fileName;

    private String privData;

    public InputStream getIn() {
        return in;
    }

    public void setIn(InputStream in) {
        this.in = in;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPrivData() {
        return privData;
    }

    public void setPrivData(String privData) {
        this.privData = privData;
    }
}

package com.lakala.crossborder.client.entities.batchTrade;

/**
 * Created by jiang on 2016/10/26.
 */
public class DownLoadFileReq {


    private String downLoadToken;

    /**
     * true-回盘
     * false-补回盘
     */
    private boolean isResFile;

    private String secCode;

    private String fileName;

    private String localFilePath;

    private String lklDigest;

    public String getDownLoadToken() {
        return downLoadToken;
    }

    public void setDownLoadToken(String downLoadToken) {
        this.downLoadToken = downLoadToken;
    }

    public boolean isResFile() {
        return isResFile;
    }

    public void setResFile(boolean resFile) {
        isResFile = resFile;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public void setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
    }

    public String getLklDigest() {
        return lklDigest;
    }

    public void setLklDigest(String lklDigest) {
        this.lklDigest = lklDigest;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DownLoadFileReq{");
        sb.append("downLoadToken='").append(downLoadToken).append('\'');
        sb.append(", secCode='").append(secCode).append('\'');
        sb.append(", fileName='").append(fileName).append('\'');
        sb.append(", localFilePath='").append(localFilePath).append('\'');
        sb.append(", lklDigest='").append(lklDigest).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

package com.lakala.crosspay.client.exception;

/**
 * <p>
 * 拉卡拉跨支付client异常,向拉卡拉跨境支付平台发起请求时可能会抛出该异常
 * </p>
 * Created by jiang on 16/4/19.
 */
public class LklClientException extends RuntimeException {

    public LklClientException(Throwable t) {
        super(t);
    }

    public LklClientException(String errorMessage) {
        super(errorMessage);
    }

    public LklClientException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}

package com.lakala.crossborder.client.exception;

/**
 * Created by jiang on 2016/10/25.
 */
public class LklCommonException extends RuntimeException {

    public LklCommonException(Throwable t) {
        super(t);
    }

    public LklCommonException(String errorMessage) {
        super(errorMessage);
    }

    public LklCommonException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}

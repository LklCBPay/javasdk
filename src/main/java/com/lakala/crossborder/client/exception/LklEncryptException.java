package com.lakala.crossborder.client.exception;

/**
 * <p>
 * 加密异常
 * </p>
 * Created by jiang on 16/4/19.
 */
public class LklEncryptException extends RuntimeException {
    public LklEncryptException(Throwable t) {
        super(t);
    }

    public LklEncryptException(String errorMessage) {
        super(errorMessage);
    }

    public LklEncryptException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}

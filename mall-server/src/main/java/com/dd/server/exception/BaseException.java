package com.dd.server.exception;

import com.dd.server.exception.message.ErrorInfo;

public class BaseException extends RuntimeException {

    private ErrorInfo errorInfo;

    public BaseException(ErrorInfo errorInfo) {

        super("code=" + errorInfo.getCode() + ", desc=" + errorInfo.getDesc());

        this.errorInfo = errorInfo;
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

}

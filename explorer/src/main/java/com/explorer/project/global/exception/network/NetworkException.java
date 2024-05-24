package com.explorer.project.global.exception.network;

import lombok.Getter;

@Getter
public class NetworkException extends RuntimeException {

    private final NetworkErrorCode errorCode;

    public NetworkException(NetworkErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}

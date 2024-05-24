package com.explorer.project.global.exception.network;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum NetworkErrorCode {

    NOT_CONNECTION("Connection이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus httpStatus;


}

package com.example.sogong.global.common.response.exception;

import com.example.sogong.global.common.response.code.AuthErrorCode;
import lombok.Getter;

@Getter
public class AuthErrorException extends RuntimeException {
    private final AuthErrorCode errorCode;
    private final String message;

    public AuthErrorException(AuthErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public String toString() {
        return "AUTH 에러 코드: " + errorCode + ", 사유: " + message;
    }
}

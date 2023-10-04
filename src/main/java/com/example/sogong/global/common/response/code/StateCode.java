package com.example.sogong.global.common.response.code;

import org.springframework.http.HttpStatus;

public interface StateCode {
    HttpStatus getHttpStatus();
    String getMessage();
}

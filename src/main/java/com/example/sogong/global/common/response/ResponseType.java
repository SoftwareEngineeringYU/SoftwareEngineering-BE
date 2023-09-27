package com.example.sogong.global.common.response;

import lombok.Getter;

@Getter
public enum ResponseType {
    SUCCESS(200), FAILURE(400);

    private final int code;

    ResponseType(int code) {
        this.code = code;
    }

    @Override public String toString() {
        return String.valueOf(code);
    }

    public String getMessage() {
        return this.name();
    }
}

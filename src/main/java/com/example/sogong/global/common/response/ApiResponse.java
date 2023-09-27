package com.example.sogong.global.common.response;

import lombok.Builder;

import static com.example.sogong.global.common.response.ResponseType.SUCCESS;

@Builder
public record ApiResponse<T> (
    int code,
    String message,
    T data
) {
    public static ApiResponse<?> successWithNoContent() {
        return ApiResponse.builder()
            .code(SUCCESS.getCode())
            .build();
    }

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
            .code(SUCCESS.getCode())
            .data(data)
            .build();
    }

    public static <T> ApiResponse<T> failure(T data) {
        return ApiResponse.<T>builder()
            .code(ResponseType.FAILURE.getCode())
            .message(ResponseType.FAILURE.getMessage())
            .data(data)
            .build();
    }

    public static ApiResponse<?> error() {
        return ApiResponse.builder()
            .code(ResponseType.FAILURE.getCode())
            .message(ResponseType.FAILURE.getMessage())
            .build();
    }
}

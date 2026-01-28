package com.example.bodydiary.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Response {
    private final int code;
    private final String message;
    private final Object data;

    public static Response success(Object data) {
        return new Response(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    public static Response success(String message) {
        return new Response(ResponseCode.SUCCESS.getCode(), message, null);
    }

    public static Response error(ResponseCode responseCode) {
        return new Response(responseCode.getCode(), responseCode.getMessage(), null);
    }

    public static Response error(ResponseCode responseCode, String customMessage) {
        return new Response(responseCode.getCode(), customMessage, null);
    }

    public static Response error(int code, String message) {
        return new Response(code, message, null);
    }
}

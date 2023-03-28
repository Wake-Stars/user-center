package com.stars.usercenter.common;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;

import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: BaseResponse
 * @Description: 通用返回类
 * @Author: Stars
 * @Date: 2023-03-23     21:38
 */
@Data
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = 5761341637216435272L;

    private int code;

    private T data;

    private String message;

    private String description;

    public BaseResponse(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;

    }

    public static <K> BaseResponse<K> ok() {
        return new BaseResponse<>(0, null, "", "");
    }

    public static <T> BaseResponse<T> ok(T data) {
        return new BaseResponse<>(0, data, "", "");
    }

    public static <T> BaseResponse<T> fail(int code, T data, String message) {
        return new BaseResponse<>(code, data, message, "");
    }

    public static <T> BaseResponse<T> error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode.getCode(), null, errorCode.getMessage(), errorCode.getDescription());
    }

    public static <T> BaseResponse<T> error(ErrorCode errorCode, String message) {
        return new BaseResponse<>(errorCode.getCode(), null, message, errorCode.getDescription());
    }
    public static <T> BaseResponse<T> error(int code,String message, String description) {
        return new BaseResponse<>(code, null, message, description);
    }

    public static <T> BaseResponse<T> error(ErrorCode errorCode, String message, String description) {
        return new BaseResponse<>(errorCode.getCode(), null, errorCode.getMessage(), description);
    }


}


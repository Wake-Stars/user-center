package com.stars.usercenter.exception;

import com.stars.usercenter.common.BaseResponse;
import com.stars.usercenter.common.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description: 全局异常处理器
 * @Author: Stars
 * @Date: 2023-03-24     19:47
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public  BaseResponse businessExceptionHandler(BusinessException e) {
        log.error("businessException" + e.getMessage(),e);
        return BaseResponse.error(e.getCode(),e.getMessage(),e.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException",e);
        return BaseResponse.error(ErrorCode.SYSTEM_ERROR,e.getMessage(),"");
    }

}

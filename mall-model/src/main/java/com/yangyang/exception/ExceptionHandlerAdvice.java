package com.yangyang.exception;

import com.sun.org.apache.xpath.internal.operations.String;
import com.yangyang.pojo.response.ResultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author gzy
 * @date 2020/4/25 20:57
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler({IllegalArgumentException.class})
    public ResultResponse badRequestException(IllegalArgumentException exception){
        ResultResponse<String> resultResponse = new ResultResponse();
        resultResponse.setCode(HttpStatus.BAD_REQUEST.value());
        resultResponse.setMsg(exception.getMessage());
        resultResponse.setSuccess(false);
        return resultResponse;
    }

    @ExceptionHandler({ServiceException.class})
    public ResultResponse oauthException(ServiceException serviceException) {
        ResultResponse<String> resultResponse = new ResultResponse<>();
        resultResponse.setCode(HttpStatus.UNAUTHORIZED.value());
        resultResponse.setMsg(serviceException.getMessage());
        resultResponse.setSuccess(false);
        return resultResponse;
    }
}

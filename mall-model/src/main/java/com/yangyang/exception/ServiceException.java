package com.yangyang.exception;

import com.yangyang.pojo.response.ResultResponse;
import lombok.Data;

/**
 * @author gzy
 * @date 2020/4/26 16:19
 */
@Data
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 2626418565730406733L;

    private String message;


    public ServiceException(String message) {
        this.message = message;
    }
}

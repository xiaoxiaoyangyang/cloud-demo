package com.yangyang.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author gzy
 * @date 2020/4/26 16:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse<T> implements Serializable {
    private static final long serialVersionUID = 7128994017252677617L;

    private int code;
    private boolean success;
    private String msg;
    private T data;
}

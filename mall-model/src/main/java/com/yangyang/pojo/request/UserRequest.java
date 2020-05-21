package com.yangyang.pojo.request;

import lombok.Data;

/**
 * @author gzy
 * @date 2020/4/28 0:09
 */
@Data
public class UserRequest {
    private String username;
    private String nickname;
    private String sex;
    private Integer type;
    private Integer enable;
    private Integer page =1;
    private Integer size =10;
}

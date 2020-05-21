package com.yangyang.model;

import com.yangyang.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.attribute.IntegerSyntax;
import java.io.Serializable;
import java.util.Date;

/**
 * @author gzy
 * @date 2020/4/25 15:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser implements Serializable {

    private static final long serialVersionUID = 5263471383504511799L;
    private Long userId;
    private String username;
    private String password;
    private String nickname;
    private String headImageUrl;
    private String phone;
    private Integer sex;
    private Integer enable;
    private Integer type;
    private Date createTime;
    private Date updateTime;
}

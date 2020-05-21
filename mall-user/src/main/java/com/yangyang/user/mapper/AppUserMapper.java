package com.yangyang.user.mapper;

import com.yangyang.model.AppUser;
import com.yangyang.pojo.request.UserRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author gzy
 * @date 2020/4/25 18:59
 */
@Mapper
public interface AppUserMapper {
    AppUser selectByUsername(@Param("username") String username);

    void save(AppUser appUser);

    int count(@Param("userRequest") UserRequest userRequest);

    List<AppUser> selectByParams(@Param("userRequest") UserRequest userRequest, @Param("start")int start, @Param("size") int size);

    AppUser selectOneByUserId(Long userId);

    void updateAppUser(AppUser appUser);
}

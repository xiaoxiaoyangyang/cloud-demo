package com.yangyang.log.mapper;

import com.yangyang.model.Log;
import com.yangyang.pojo.request.LogRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.print.attribute.IntegerSyntax;
import java.util.List;

/**
 * @author gzy
 * @date 2020/5/3 15:36
 */
@Mapper
public interface LogMapper {
    void save(Log log);

    Integer count(@Param("logRequest") LogRequest logRequest);

    List<Log> queryList(@Param("logRequest") LogRequest logRequest, @Param("start") Integer start,@Param("size") Integer size);
}

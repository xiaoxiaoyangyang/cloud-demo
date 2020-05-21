package com.yangyang.mall.file.mapper;

import com.yangyang.model.FileInfo;
import com.yangyang.pojo.request.FileRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gzy
 * @date 2020/5/5 19:18
 */
@Mapper
public interface FileMapper {
    FileInfo selectById(String fileId);

    void save(FileInfo fileInfo);

    void delete(String fileId);

    int count(@Param("fileRequest") FileRequest fileRequest);

    List<FileInfo> selectFileList(@Param("fileRequest") FileRequest fileRequest, @Param("start")Integer start, @Param("size")Integer size);
}

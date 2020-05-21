package com.yangyang.mall.file.service;


import com.yangyang.model.FileInfo;
import com.yangyang.model.Page;
import com.yangyang.pojo.request.FileRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author gzy
 * @date 2020/5/5 18:15
 */
public interface FileService {
    FileInfo upload(MultipartFile file, String fileSource) throws IOException;

    void delete(String fileId);

    Page<FileInfo> findFileList(FileRequest fileRequest);
}

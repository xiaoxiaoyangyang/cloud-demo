package com.yangyang.mall.file.controller;

import com.yangyang.mall.file.annotation.FileSource;
import com.yangyang.mall.file.config.FileServiceFactory;
import com.yangyang.mall.file.service.FileService;
import com.yangyang.model.FileInfo;
import com.yangyang.model.Page;
import com.yangyang.pojo.request.FileRequest;
import com.yangyang.pojo.response.ResultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author gzy
 * @date 2020/5/5 18:43
 */
@RestController
public class FileController {

    @PostMapping("/files-anon/uploadFile")
    public ResultResponse<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("fileSource") String fileSource){
        try {
            FileService fileService = FileServiceFactory.getInstance().fileService(fileSource);
            FileInfo upload = fileService.upload(file, fileSource);
            ResultResponse<FileInfo> resultResponse = new ResultResponse<>();
            resultResponse.setCode(HttpStatus.OK.value());
            resultResponse.setData(upload);
            resultResponse.setSuccess(true);
            return resultResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PreAuthorize("hasAuthority('back:files:delete')")
    @DeleteMapping("/mall-files/{fileId}")
    public ResultResponse deleteFile(@PathVariable("fileId")String fileId,@RequestParam("fileSource")String fileSource) {
        FileService fileService = FileServiceFactory.getInstance().fileService(fileSource);
        fileService.delete(fileId);
        ResultResponse<FileInfo> resultResponse = new ResultResponse<>();
        resultResponse.setCode(HttpStatus.OK.value());
        resultResponse.setSuccess(true);
        return resultResponse;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('back:files:query')")
    public ResultResponse<?> findFiles(@RequestBody FileRequest fileRequest){
        FileService fileService = FileServiceFactory.getInstance().fileService(fileRequest.getFileSource());
        int channel = FileSource.typeFileSource(fileRequest.getFileSource());
        fileRequest.setChannel(channel);
        Page<FileInfo> fileList = fileService.findFileList(fileRequest);
        ResultResponse<Page<FileInfo>> resultResponse = new ResultResponse<>();
        resultResponse.setCode(HttpStatus.OK.value());
        resultResponse.setSuccess(true);
        resultResponse.setData(fileList);
        return resultResponse;
    }
}

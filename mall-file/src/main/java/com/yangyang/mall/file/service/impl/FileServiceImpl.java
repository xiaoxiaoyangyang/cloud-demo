package com.yangyang.mall.file.service.impl;

import com.yangyang.mall.file.annotation.FileSource;
import com.yangyang.mall.file.mapper.FileMapper;
import com.yangyang.mall.file.service.FileService;
import com.yangyang.mall.file.utils.FileUtils;
import com.yangyang.model.FileInfo;
import com.yangyang.model.Page;
import com.yangyang.pojo.request.FileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * @author gzy
 * @date 2020/5/5 18:15
 */
@Service("fileLocalServiceImpl")
public class FileServiceImpl implements FileService {
    @Value("${file.local.path}")
    private String localFilePath;
    @Value("${file.local.uriPrefix}")
    public String urlPrefix;
    @Autowired
    private FileMapper fileMapper;
    @Override
    public FileInfo upload(MultipartFile file,String fileSource) throws IOException {
        FileInfo fileInfo = FileUtils.fileInfo(file);
        FileInfo oldFileInfo = fileMapper.selectById(fileInfo.getFileId());
        if (oldFileInfo != null) {
            return oldFileInfo;
        }
        if (!fileInfo.getFileName().contains(".")) {
            throw new IllegalArgumentException("缺少后缀名");
        }
        fileInfo.setChannel(FileSource.typeFileSource(fileSource));

        int index = fileInfo.getFileName().lastIndexOf(".");
        String fileSuffix = fileInfo.getFileName().substring(index);
        String suffix = "/" + LocalDate.now().toString().replace("-","/")+"/"+fileInfo.getFileId()+fileSuffix;
        String path = localFilePath + suffix;
        String url = urlPrefix + suffix;
        fileInfo.setPath(path);
        fileInfo.setUrl(url);
        FileUtils.save(file,path);
        fileInfo.setChannel(FileSource.LOCAL.type);
        fileMapper.save(fileInfo);
        return fileInfo;
    }

    @Override
    public void delete(String fileId) {
        FileInfo fileInfo = fileMapper.selectById(fileId);
        FileUtils.deleteFile(fileInfo.getPath());
        fileMapper.delete(fileId);
    }

    @Override
    public Page<FileInfo> findFileList(FileRequest fileRequest) {
        int count = fileMapper.count(fileRequest);
        Page<FileInfo> fileInfoPage = new Page<>();
        if (count > 0) {
            int start = (fileRequest.getPage() - 1) * fileRequest.getSize();
            List<FileInfo> fileInfos = fileMapper.selectFileList(fileRequest, start, fileRequest.getSize());
            fileInfoPage.setData(fileInfos);
        }
        fileInfoPage.setTotal(count);
        fileInfoPage.setPage(fileRequest.getPage());
        fileInfoPage.setSize(fileRequest.getSize());
        return fileInfoPage;
    }
}

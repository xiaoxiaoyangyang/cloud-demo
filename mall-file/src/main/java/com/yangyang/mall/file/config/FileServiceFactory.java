package com.yangyang.mall.file.config;

import com.yangyang.mall.file.annotation.FileSource;
import com.yangyang.mall.file.service.FileService;
import com.yangyang.mall.file.service.impl.FileServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import sun.nio.cs.ext.MacArabic;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * @author gzy
 * @date 2020/5/5 18:14
 */
@Slf4j
@Configuration
public class FileServiceFactory {
    @Autowired
    private FileServiceImpl fileLocalServiceImpl;

    private static HashMap<String, FileService> map = new HashMap<>();

    @PostConstruct
    public void init(){
        map.put(FileSource.LOCAL.sourceFile,fileLocalServiceImpl);
        log.info("---------------------日志的打印信息");
    }

    public static class Single {
        public final static FileServiceFactory fileServiceFactory = new FileServiceFactory();
    }

    public static FileServiceFactory getInstance(){
        return Single.fileServiceFactory;
    }

    public FileService fileService(String fileSource){
        if (StringUtils.isBlank(fileSource)) {
            return fileLocalServiceImpl;
        }
        return map.get(fileSource);
    }
}

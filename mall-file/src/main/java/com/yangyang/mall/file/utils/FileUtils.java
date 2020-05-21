package com.yangyang.mall.file.utils;

import com.yangyang.model.FileInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * @author gzy
 * @date 2020/5/5 19:09
 */
public class FileUtils {
    public static FileInfo fileInfo(MultipartFile file) throws IOException {
        String md5 = fileMd5(file.getInputStream());
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileId(md5);
        fileInfo.setFileName(file.getOriginalFilename());
        fileInfo.setContentType(file.getContentType());
        if (file.getContentType().startsWith("image/")) {
            fileInfo.setIsImage(1);
        } else {
            fileInfo.setIsImage(2);
        }
        fileInfo.setSize(file.getSize());
        fileInfo.setCreateTime(new Date());
        return fileInfo;
    }

    public static String fileMd5(InputStream inputStream) {
        try {
            return DigestUtils.md5Hex(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String save(MultipartFile file, String path) {
        try {
            File targetFile = new File(path);
            if (targetFile.exists()) {
                return path;
            }
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteFile(String pathName) {
        File file = new File(pathName);
        if (file.exists()) {
            boolean delete = file.delete();
            if (delete) {
                File[] files = file.getParentFile().listFiles();
                if (files.length == 0) {
                    String name = file.getParentFile().getName();
                    deleteFile(name);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        File file = new File("D:\\gzyanzhuang\\视频\\test\\test1\\2.txt");
        remove(file);
    }
    public static void remove(File file){
        if (file.exists()){
            boolean delete = file.delete();
            System.out.println("--------------删除成功的文件夹"+file.getName());
            if (delete) {
                File[] files = file.getParentFile().listFiles();
                if (files.length == 0) {
                    File parentFile = file.getParentFile();
                    remove(parentFile);
                }
            }
        }
    }
}

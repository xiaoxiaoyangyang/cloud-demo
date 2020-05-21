package com.yangyang.mall.file.annotation;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gzy
 * @date 2020/5/5 18:19
 */
public enum  FileSource {
    LOCAL(1,"local","本地上传"),
    ;
    public int type;
    public String sourceFile;
    public String description;

    FileSource(int type, String sourceFile, String description) {
        this.type = type;
        this.sourceFile = sourceFile;
        this.description = description;
    }

    public static int typeFileSource(String sourceFile){
        List<FileSource> collect = Arrays.asList(FileSource.values())
                .stream()
                .filter(fileSource -> StringUtils.equalsIgnoreCase(sourceFile, fileSource.sourceFile))
                .collect(Collectors.toList());
        if (collect.size() == 0) {
            return 1;
        } else {
            FileSource fileSource = collect.get(0);
            return fileSource.type;
        }
    }
}

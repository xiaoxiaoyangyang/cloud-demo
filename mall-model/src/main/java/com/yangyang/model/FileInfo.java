package com.yangyang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gzy
 * @date 2020/5/5 19:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo implements Serializable {
    private static final long serialVersionUID = 9112999146497022963L;
    private String fileId;
    private String fileName;
    private String contentType;
    private int isImage;
    private Date createTime;
    private long size;
    private String path;
    private String url;
    private int channel;
}

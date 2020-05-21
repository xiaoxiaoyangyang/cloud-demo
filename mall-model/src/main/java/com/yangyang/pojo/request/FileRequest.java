package com.yangyang.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gzy
 * @date 2020/5/10 21:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileRequest {
    private String fileSource;
    private String fileId;
    private Integer channel;
    private Integer page;
    private Integer size;
}

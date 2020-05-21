package com.yangyang.user.utils;

import org.apache.commons.collections.MapUtils;
import sun.security.util.Length;

import java.util.Map;
import java.util.Objects;

/**
 * @author gzy
 * @date 2020/4/25 22:21
 */
public class PageUtil {

    public static final String PAGE = "page";
    public static final String SIZE = "size";


    public static void pageParamConvert(Map<String, Object> params) {
        if (MapUtils.isNotEmpty(params)) {
            if (params.containsKey(PAGE)) {
                params.put(PAGE,MapUtils.getIntValue(params,PAGE));
            }
            if (params.containsKey(SIZE)) {
                params.put(SIZE,MapUtils.getIntValue(params,SIZE));
            }
        }
    }
}

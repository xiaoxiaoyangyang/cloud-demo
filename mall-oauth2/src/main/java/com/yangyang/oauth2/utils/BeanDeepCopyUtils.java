package com.yangyang.oauth2.utils;

import org.dozer.DozerBeanMapper;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;


/**
 * 属性的转换
 * @author gzy
 * @date 2019/7/16 10:54
 */
public class BeanDeepCopyUtils {

    private static DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    public static <T> T copyProperties(Object source, Class<T> destination) {
        if (isNull(source)) {
            return null;
        }
        return dozerBeanMapper.map(source, destination);
    }

    public static <T> List<T> copyPropertiesOfList(List<?> source, Class<T> destination) {
        List<T> list = new ArrayList<T>();
        if (CollectionUtils.isEmpty(source)) {
            return list;
        }
        source.forEach(v -> {
            list.add(dozerBeanMapper.map(v, destination));
        });
        return list;
    }

}

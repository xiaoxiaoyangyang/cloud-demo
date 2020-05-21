package com.yangyang.utils;


import java.util.ArrayList;

/**
 * @author gzy
 * @date 2020/4/25 21:22
 */
public class PermitAllUrlUtil {
    public static final String[] ENDPOINTS = {"/actuator/health"};

    public static String[] permitAllUrl(String... urls) {
        if (urls == null || urls.length == 0) {
            return PermitAllUrlUtil.ENDPOINTS;
        }

        ArrayList<String> list = new ArrayList<>();
        for (String url: urls) {
            list.add(url);
        }

        for (String url: ENDPOINTS) {
            list.add(url);
        }

        return list.toArray(new String[list.size()]);
    }

}

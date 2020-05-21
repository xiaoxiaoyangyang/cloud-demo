//package com.yangyang.log.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.HttpHost;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//import java.util.stream.Collectors;
//
///**
// * @author guozhiyang_vendor
// */
//@Slf4j
//@Configuration
//public class ElasticRestClientFactory {
//    @Value("${elastic.clusterAddress}")
//    private String clusterAddress;
//    @Value("${elastic.connection-timeout}")
//    private Integer connectTimeOut;
//    @Value("${elastic.socket-timeout}")
//    private Integer socketTimeOut;
//    @Value("${elastic.connection-request-timeout}")
//    private Integer connectRequestTimeOut;
//    @Value("${elastic.connection-common}")
//    private Integer connectNumber;
//    @Value("${elastic.connection-total}")
//    private Integer connectTotal;
//    @Value("${elastic.scheme}")
//    private String scheme;
//
//    static final String COLON = ":";
//    static final String COMMA = ",";
//
//    @Bean
//    public RestHighLevelClient restHighLevelClient(){
//        String[] split = clusterAddress.split(COMMA);
//        HttpHost[] httpHosts = new HttpHost[split.length];
//        HttpHost[] collect = Arrays.stream(split).map(s -> {
//            String[] split1 = s.split(COLON);
//            HttpHost httpHost = new HttpHost(split1[0], Integer.parseInt(split1[1]), scheme);
//            return httpHost;
//        }).collect(Collectors.toList()).toArray(httpHosts);
//
//        RestHighLevelClient restHighLevelClient= new RestHighLevelClient(
//                RestClient.builder(collect).setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
//                    @Override
//                    public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder builder) {
//                        builder.setConnectTimeout(connectTimeOut);
//                        builder.setSocketTimeout(socketTimeOut);
//                        builder.setConnectionRequestTimeout(connectRequestTimeOut);
//                        return builder;
//                    }
//                }).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
//                    @Override
//                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
//                        httpAsyncClientBuilder.setMaxConnTotal(connectTotal);
//                        httpAsyncClientBuilder.setMaxConnPerRoute(connectNumber);
//                        return httpAsyncClientBuilder;
//                    }
//                })
//        );
//        return restHighLevelClient;
//    }
//}

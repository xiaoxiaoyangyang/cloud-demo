//package com.yangyang.log.service.impl;
//
//import com.alibaba.fastjson.JSON;
//import com.yangyang.log.service.LogService;
//import com.yangyang.model.Log;
//import com.yangyang.model.Page;
//import com.yangyang.pojo.request.LogRequest;
//import org.elasticsearch.action.admin.indices.alias.Alias;
//import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
//import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
//import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.index.query.*;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.search.sort.FieldSortBuilder;
//import org.elasticsearch.search.sort.SortOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
///**
// * @author gzy
// * @date 2020/5/5 16:06
// */
////@Service
//public class EsLogServiceImpl implements LogService{
//    @Value("${elastic.file.path}")
//    private String fileName;
//    @Value("${elastic.aliases}")
//    private String alias;
//    @Value("${elastic.shards}")
//    private Integer shards;
//    @Value("${elastic.replicas}")
//    private Integer replicas;
//
//    @Autowired
//    private RestHighLevelClient restHighLevelClient;
//
//    @Override
//    public void save(Log log) {
//        try {
//            String id = UUID.randomUUID().toString().replace("-", "");
//            IndexRequest indexRequest = new IndexRequest("operate_index", "operateLog", id);
//            indexRequest.source(JSON.toJSONString(log), XContentType.JSON);
//            IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
//            int status = response.status().getStatus();
//            if (status == HttpStatus.CREATED.value()) {
//                System.out.println("----------------日志插入成功");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public Page<Log> query(LogRequest logRequest) {
//        try {
//            SearchRequest searchRequest = new SearchRequest(alias);
//            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//            ArrayList<QueryBuilder> list = new ArrayList<>();
//            if (logRequest != null) {
//                if (logRequest.getCreateTime() != null) {
//                    RangeQueryBuilder create_time = QueryBuilders.rangeQuery("create_time").gte(logRequest.getCreateTime());
//                    list.add(create_time);
//                }
//                if (logRequest.getUsername() != null) {
//                    TermQueryBuilder username = QueryBuilders.termQuery("username", logRequest.getUsername());
//                    list.add(username);
//                }
//                if (logRequest.getModule() != null) {
//                    TermQueryBuilder module = QueryBuilders.termQuery("module", logRequest.getModule());
//                    list.add(module);
//                }
//            }
//            if (list.size() == 0) {
//                searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//            } else {
//                BoolQueryBuilder mu=QueryBuilders.boolQuery();
//                list.forEach(queryBuilder -> {
//                    mu.must(queryBuilder);
//                });
//                searchSourceBuilder.query(mu);
//            }
//            int start = (logRequest.getPage() - 1) * logRequest.getSize();
//            searchSourceBuilder.from(start).size(logRequest.getSize()).sort(new FieldSortBuilder("log_id").order(SortOrder.DESC));
//            searchRequest.source(searchSourceBuilder);
//            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//            long totalHits = searchResponse.getHits().getTotalHits();
//            SearchHits hits = searchResponse.getHits();
//            List<Log> logs = new ArrayList<>();
//            hits.forEach(searchHits->{
//                Log log = JSON.parseObject(searchHits.getSourceAsString(), Log.class);
//                logs.add(log);
//            });
//            Page<Log> logPage = new Page<>();
//            logPage.setTotal(totalHits);
//            logPage.setData(logs);
//            logPage.setPage(logRequest.getPage());
//            logPage.setSize(logRequest.getSize());
//            return logPage;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @PostConstruct
//    public void initIndex(){
//        try {
//            GetIndexRequest getIndexRequest = new GetIndexRequest();
//            getIndexRequest.indices("operate_index");
//            boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
//            if (!exists) {
//                ClassPathResource cpr = new ClassPathResource(fileName);
//                InputStream inputStream = cpr.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//                String mapping = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
//                CreateIndexRequest createIndexRequest = new CreateIndexRequest("operate_index");
//                createIndexRequest.settings(
//                        Settings.builder()
//                                .put("index.number_of_shards",shards)
//                                .put("index.number_of_replicas",replicas)
//                                .put("max_result_window",100000000)
//                );
//                createIndexRequest.alias(new Alias(alias)).mapping("operateLog",mapping, XContentType.JSON);
//                CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
//                System.out.println("-----------索引建立成功了--------------->"+createIndexResponse.index());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

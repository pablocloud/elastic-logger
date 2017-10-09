package com.github.pablocloud.elasticlogger.types

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.github.pablocloud.elasticlogger.utils.ElasticConfig
import org.apache.http.HttpHost
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.action.index.IndexResponse
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.common.xcontent.XContentType

import java.lang.reflect.Array

class ElasticClient<T> {

    private String host
    private String port
    private String protocol
    private ObjectMapper objectMapper

    ElasticClient() {

    }

    def sendToElastic(T t) {
        objectMapper = new ObjectMapper()
        IndexResponse response = null
        RestClient restClient = RestClient.builder(
                new HttpHost(
                        ElasticConfig.getProperties().get("config.elastic.host").toString(),
                        Integer.parseInt(ElasticConfig.CONFIG.getProperty("config.elastic.port")),
                        ElasticConfig.CONFIG.getProperty("config.elastic.protocol"))).build()
        RestHighLevelClient client = new RestHighLevelClient(restClient)
        try {
            def name = t.getClass().getSimpleName().toLowerCase()
            IndexRequest request = new IndexRequest(name, "doc")
            if (t instanceof Array || t instanceof List) {
                objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true)
            } else {
                objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
            }
            String jsonString = objectMapper.writeValueAsString(t)
            request.source(jsonString, XContentType.JSON)
            try {
                response = client.index(request)
                restClient.close()
            } catch (IOException e) {
                e.printStackTrace()
            }
        } catch (IOException e) {
            e.printStackTrace()
        }
        return response
    }

}

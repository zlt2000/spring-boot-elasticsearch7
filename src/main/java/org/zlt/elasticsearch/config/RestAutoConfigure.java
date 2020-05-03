package org.zlt.elasticsearch.config;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientBuilderCustomizer;
import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义es连接池
 *
 * @author zlt
 * @date 2020/5/3
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@Configuration
public class RestAutoConfigure {
    /**
     * 链接建立超时时间
     */
    @Value("${zlt.connectTimeOut:1000}")
    private Integer connectTimeOut;
    /**
     * 等待数据超时时间
     */
    @Value("${zlt.socketTimeOut:30000}")
    private Integer socketTimeOut;
    /**
     * 连接池获取连接的超时时间
     */
    @Value("${zlt.connectionRequestTimeOut:500}")
    private Integer connectionRequestTimeOut;
    /**
     * 最大连接数
     */
    @Value("${zlt.maxConnectNum:30}")
    private Integer maxConnectNum;
    /**
     * 最大路由连接数
     */
    @Value("${zlt.maxConnectPerRoute:10}")
    private Integer maxConnectPerRoute;

    @Bean
    public RestClientBuilderCustomizer restClientBuilderCustomizer(RestClientProperties restProperties) {
        return (builder) -> {
            setRequestConfig(builder);

            setHttpClientConfig(builder, restProperties);
        };
    }

    /**
     * 异步httpclient连接延时配置
     */
    private void setRequestConfig(RestClientBuilder builder) {
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(connectTimeOut)
                    .setSocketTimeout(socketTimeOut)
                    .setConnectionRequestTimeout(connectionRequestTimeOut);
            return requestConfigBuilder;
        });
    }

    /**
     * 异步httpclient连接数配置
     */
    private void setHttpClientConfig(RestClientBuilder builder, RestClientProperties restProperties){
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(maxConnectNum)
                    .setMaxConnPerRoute(maxConnectPerRoute);

            PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
            map.from(restProperties::getUsername).to(username -> {
                CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                credentialsProvider.setCredentials(AuthScope.ANY,
                        new UsernamePasswordCredentials(username, restProperties.getPassword()));
                httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            });
            return httpClientBuilder;
        });
    }
}

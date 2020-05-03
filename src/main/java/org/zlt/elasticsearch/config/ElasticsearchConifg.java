package org.zlt.elasticsearch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.core.ResultsMapper;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;

/**
 * @author zlt
 * @date 2020/5/3
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@Configuration
public class ElasticsearchConifg {
    @Bean
    public ResultsMapper resultsMapper(SimpleElasticsearchMappingContext mappingContext, EntityMapper entityMapper) {
        return new MyResultMapper(mappingContext, entityMapper);
    }
}

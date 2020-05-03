package org.zlt.elasticsearch.service;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.AliasQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.zlt.elasticsearch.model.MyUser;

import java.util.Optional;

/**
 * @author zlt
 * @date 2020/5/3
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@Service
public class MyUserService {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private MyUserRepository userRepository;

    /**
     * 创建/更新索引
     */
    public boolean createOrUpdateIndex() {
        boolean result = elasticsearchRestTemplate.createIndex(MyUser.class);
        if (result) {
            result = elasticsearchRestTemplate.putMapping(MyUser.class);
        }
        return result;
    }

    /**
     * 删除索引
     */
    public boolean deleteIndex() {
        return elasticsearchRestTemplate.deleteIndex(MyUser.class);
    }

    /**
     * 增加索引别名
     */
    public boolean addAlias() {
        AliasQuery query = new AliasQuery();
        query.setIndexName("my-user");
        query.setAliasName("user");
        return elasticsearchRestTemplate.addAlias(query);
    }

    /**
     * 新增或更新数据
     */
    public void save(MyUser user) {
        userRepository.save(user);
    }

    /**
     * 删除数据
     */
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    /**
     * 通过id查找
     */
    public Optional<MyUser> findById(String id) {
        return userRepository.findById(id);
    }

    /**
     * 分页查询
     */
    public Page<MyUser> queryPage() {
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("username", "admin"))
                .withPageable(PageRequest.of(0, 10))
                .build();
        return userRepository.search(query);
    }
}

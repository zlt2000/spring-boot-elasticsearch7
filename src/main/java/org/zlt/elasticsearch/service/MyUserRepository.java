package org.zlt.elasticsearch.service;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.zlt.elasticsearch.model.MyUser;

/**
 * @author zlt
 * @date 2020/5/3
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
public interface MyUserRepository extends ElasticsearchRepository<MyUser, String> {

}

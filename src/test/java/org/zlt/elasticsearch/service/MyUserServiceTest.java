package org.zlt.elasticsearch.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.zlt.elasticsearch.model.MyUser;

import java.util.Optional;

/**
 * @author zlt
 * @date 2020/5/3
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyUserServiceTest {
    @Autowired
    private MyUserService userService;

    @Test
    public void test1CreateIndex() {
        boolean result = userService.createOrUpdateIndex();
        System.out.println(result);
        assertThat(result).isTrue();
    }

    @Test
    public void test8DeleteIndex() {
        boolean result = userService.deleteIndex();
        System.out.println(result);
        assertThat(result).isTrue();
    }

    @Test
    public void test2AddAlias() {
        boolean result = userService.addAlias();
        System.out.println(result);
        assertThat(result).isTrue();
    }

    @Test
    public void test3InsertUser() {
        MyUser user = new MyUser();
        user.setId(1L);
        user.setUsername("admin");
        user.setSex("男");
        user.setAge(25);
        userService.save(user);
    }

    @Test
    public void test4UpdateUser() {
        MyUser user = new MyUser();
        user.setId(1L);
        user.setUsername("admin");
        user.setSex("女");
        user.setAge(20);
        userService.save(user);
    }

    @Test
    public void test5FindUserById() {
        Optional<MyUser> userOpt = userService.findById("1");
        assertThat(userOpt.isPresent()).isTrue();
    }

    @Test
    public void test6QueryPage() {
        Page<MyUser> page = userService.queryPage();
        page.forEach(System.out::println);
        assertThat(page.getTotalElements()).isGreaterThan(0);
    }

    @Test
    public void test7DeleteUserById() {
        userService.deleteById("1");
    }
}

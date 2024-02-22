package com.zjj.usercenterbackend;

import com.zjj.usercenterbackend.mapper.UserMapper;
import com.zjj.usercenterbackend.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname SampleTest
 * @Description TODO
 * @Author zjj
 * @Date 2/22/24 2:34PM
 */
@SpringBootTest
public class SampleTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}

package com.zjj.usercenterbackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class UserCenterBackendApplicationTests {
    @Test
    void testDigest(){
        String password = DigestUtils.md5DigestAsHex(("abcd" + "mypassword").getBytes());
        System.out.println(password);
    }

    @Test
    void contextLoads() {
    }

}

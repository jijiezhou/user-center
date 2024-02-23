package com.zjj.usercenterbackend.service;
import java.util.Date;

import com.zjj.usercenterbackend.model.domain.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * @Classname UserServiceTest
 * @Description TODO
 * @Author zjj
 * @Date 2/22/24 4:52 PM
 */
@SpringBootTest
class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    public void testAddUser(){
        User user = new User();
        user.setUsername("zjj");
        user.setUserAccount("jijiez");
        user.setGender(0);
        user.setUserPassword("123456");
        user.setPhone("188");
        user.setEmail("gmail.com");

        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assert.assertTrue(result);
    }

    @Test
    void userRegister() {
        //1. not null
        String userAccount = "jijiez";
        String userPassword = "";
        String checkPassword = "12345678";
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        //2. account length >= 4
        userAccount = "zjj";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        //3. password length >= 8
        userAccount = "jijiez";
        userPassword = "123456";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        //4. account no special chars
        userAccount = "ji ji ";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        //5. password == checkPassword
        checkPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        //6. account duplicate
        userAccount = "jijiez";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        //7. correct
        userAccount = "yupi";
        userPassword = "12345678";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertTrue(result > 0);

    }
}

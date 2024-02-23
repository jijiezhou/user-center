package com.zjj.usercenterbackend.service;

import com.zjj.usercenterbackend.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zj
 * @description User table CRUD
 * @createDate 2024-02-22 16:37:48
 */
public interface UserService extends IService<User> {

    /**
     * Register
     * @param userAccount account
     * @param userPassword password
     * @param checkPassword check
     * @return userId
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * Login
     * @param userAccount account
     * @param userPassword password
     * @param request httpservletrequest
     * @return User info(dissensitification)
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * Dissensitification
     * @param originUser
     * @return
     */
    User getSafetyUser(User originUser);
}

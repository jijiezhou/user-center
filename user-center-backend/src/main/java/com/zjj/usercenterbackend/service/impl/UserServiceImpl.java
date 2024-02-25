package com.zjj.usercenterbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjj.usercenterbackend.mapper.UserMapper;
import com.zjj.usercenterbackend.model.domain.User;
import com.zjj.usercenterbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zjj.usercenterbackend.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author zj
 * @description implement of User table CRUD
 * @createDate 2024-02-22 16:37:48
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * salt: make password more complex
     */
    private static final String SALT = "zjj";

    /**
     * Register
     *
     * @param userAccount   account
     * @param userPassword  password
     * @param checkPassword check
     * @return userId
     */
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        //1. Check
        //1.1 null
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return -1;
        }
        if (userAccount.length() < 4) {
            return -1;
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            return -1;
        }

        //1.2 account cannot special chars
        String invalidPattern = "[ `~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(invalidPattern).matcher(userAccount);
        if (matcher.find()) {
            return -1;
        }

        if (!userPassword.equals(checkPassword))
            return -1;

        //1.3 account cannot duplicate
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0)
            return -1;

        //2. Encode password
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        //3. Insert data
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            return -1;
        }

        return user.getId();
    }

    /**
     * Login
     *
     * @param userAccount  account
     * @param userPassword password
     * @param request      httpservletrequest
     * @return User info
     */
    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        //1. Check if user account and password valid
        //1.1 null
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        if (userAccount.length() < 4) {
            return null;
        }
        if (userPassword.length() < 8) {
            return null;
        }

        //1.2 account cannot special chars
        String invalidPattern = "[ `~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(invalidPattern).matcher(userAccount);
        if (matcher.find()) {
            return null;
        }

        //2. Check is password match with password in database
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        //user not exist
        if (user == null) {
            log.info("login failed, userAccount cannot match userPassword");
            return null;
        }

        //3. Return user info(dissensitification)
        User safetyUser = getSafetyUser(user);

        //4. Record user login status(session)
        //4.1 set attribute(concurrentMap key value) in session
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);

        return safetyUser;
    }

    /**
     * Disensitification
     *
     * @param originUser
     * @return
     */
    @Override
    public User getSafetyUser(User originUser) {
        if (originUser == null)
            return null;

        User safetyUser = new User();
        safetyUser.setId(originUser.getId());
        safetyUser.setUsername(originUser.getUsername());
        safetyUser.setUserAccount(originUser.getUserAccount());
        safetyUser.setAvatarUrl(originUser.getAvatarUrl());
        safetyUser.setGender(originUser.getGender());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setEmail(originUser.getEmail());
        safetyUser.setUserRole(originUser.getUserRole());
        safetyUser.setUserStatus(originUser.getUserStatus());
        safetyUser.setCreateTime(originUser.getCreateTime());
        return safetyUser;
    }
}





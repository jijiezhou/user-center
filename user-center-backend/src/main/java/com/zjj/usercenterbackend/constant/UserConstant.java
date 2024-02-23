package com.zjj.usercenterbackend.constant;

/**
 * @Classname UserConstant
 * @Description TODO
 * @Author zjj
 * @Date 2/23/24 2:35 PM
 */
public interface UserConstant {
    /**
     * user login status key
     */
    String USER_LOGIN_STATE = "userLoginState";

    //Authorization
    /**
     * ordinary user
     */
    int DEFAULT_ROLE = 0;

    /**
     * admin
     */
    int ADMIN_ROLE = 1;
}

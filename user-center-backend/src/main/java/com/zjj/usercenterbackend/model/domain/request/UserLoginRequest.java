package com.zjj.usercenterbackend.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname UserLoginRequest
 * @Description TODO
 * @Author zjj
 * @Date 2/23/24 1:46 PM
 */
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = -1781680349465303196L;

    private String userAccount;

    private String userPassword;
}

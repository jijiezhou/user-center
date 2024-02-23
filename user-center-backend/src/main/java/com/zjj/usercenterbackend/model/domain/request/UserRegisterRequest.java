package com.zjj.usercenterbackend.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname UserRegisterRequest
 * @Description TODO
 * @Author zjj
 * @Date 2/23/24 1:24 PM
 */
@Data
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = -4585084136236144612L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;
}

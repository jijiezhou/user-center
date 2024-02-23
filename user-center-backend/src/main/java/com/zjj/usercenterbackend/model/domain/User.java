package com.zjj.usercenterbackend.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * username
     */
    private String username;

    /**
     * accouunt
     */
    private String userAccount;

    /**
     * avatar
     */
    private String avatarUrl;

    /**
     * gender
     */
    private Integer gender;

    /**
     * password
     */
    private String userPassword;

    /**
     * phone number
     */
    private String phone;

    /**
     * email
     */
    private String email;

    /**
     * status
     */
    private Integer userStatus;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * updateTime
     */
    private Date updateTime;

    /**
     * isDelete
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 0: user; 1:admin
     */
    private Integer userRole;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

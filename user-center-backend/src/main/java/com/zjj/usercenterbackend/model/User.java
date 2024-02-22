package com.zjj.usercenterbackend.model;

import lombok.Data;

/**
 * @Classname User
 * @Description TODO
 * @Author zjj
 * @Date 2/22/24 2:28 PM
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}

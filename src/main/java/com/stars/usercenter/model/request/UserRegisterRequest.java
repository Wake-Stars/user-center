package com.stars.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: UserRegisterRequest
 * @Description: 用户注册请求体
 * @Author: Stars
 * @Date: 2023-03-17     18:53
 */
@Data
public class UserRegisterRequest implements Serializable {


    private static final long serialVersionUID = -8267525494018049392L;
    private String userAccount;
    private String userPassword;
    private String checkPassword;

    private String planetCode;
}

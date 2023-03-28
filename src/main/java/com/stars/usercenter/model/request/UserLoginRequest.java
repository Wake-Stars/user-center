package com.stars.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: UserRegisterRequest
 * @Description: 用户登录请求体
 * @Author: Stars
 * @Date: 2023-03-17     18:53
 */
@Data
public class UserLoginRequest implements Serializable {


    private static final long serialVersionUID = -950376581846359986L;
    private String userAccount;
    private String userPassword;
    
}

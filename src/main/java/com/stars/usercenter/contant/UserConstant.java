package com.stars.usercenter.contant;

/**
 * @ClassName: UserConstant
 * @Description:
 * @Author: Stars
 * @Date: 2023-03-18     14:29
 */
public interface UserConstant {
    /**
     * 辅助加密
     */
     String SALT = "Stars";
    /**
     * 用户登录状态
     */
    String USER_LOGIN_STATE = "user_login_state";
    /**
     * 普通用户
     */
    int DEFAULT_ROLE = 0;
    /**
     * 管理员
     */
    int ADMIN_ROLE = 1;

}

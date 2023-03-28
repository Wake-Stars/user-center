package com.stars.usercenter.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.server.HttpServerRequest;
import com.stars.usercenter.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author Prometheus
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2023-03-16 20:36:49
*/
public interface UserService extends IService<User> {

    /**
     * 用户注释
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword, String planetCode);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     *  用户脱敏
     * @param originUser 原始用户
     * @return 安全用户
     */
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     * @param request 请求
     * @return 返回是否注销成功
     */
    int userLogout(HttpServletRequest request);
}

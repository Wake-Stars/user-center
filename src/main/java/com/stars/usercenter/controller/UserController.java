package com.stars.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.stars.usercenter.common.BaseResponse;
import com.stars.usercenter.common.ErrorCode;
import com.stars.usercenter.exception.BusinessException;
import com.stars.usercenter.model.User;
import com.stars.usercenter.model.request.UserLoginRequest;
import com.stars.usercenter.model.request.UserRegisterRequest;
import com.stars.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.stars.usercenter.contant.UserConstant.ADMIN_ROLE;
import static com.stars.usercenter.contant.UserConstant.USER_LOGIN_STATE;

/**
 * @ClassName: UserController
 * @Description:
 * @Author: Stars
 * @Date: 2023-03-17     16:38
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    private BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            // return BaseResponse.error(ErrorCode.PARAMS_ERROR)
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户注册的请求体为空");
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, planetCode)) {
            throw new BusinessException(ErrorCode.NULL_ERROR,"用户注册的请求体中的账户或密码,星球编号为空");
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        return BaseResponse.ok(result);

    }



    @PostMapping("/login")
    private BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR,"用户登录请求体为空");
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();

        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.NULL_ERROR,"用户登录请求体中账户或密码为空");

        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return BaseResponse.ok(user);

    }

    @PostMapping("/logout")
    private BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR,"请求不存在");
        }
        int result = userService.userLogout(request);
        return BaseResponse.ok(result);


    }

    @GetMapping ("/current")
    private BaseResponse<User> getCurrentUser( HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        if(currentUser == null ) {
            throw new BusinessException(ErrorCode.NOT_LOGIN,"未登录");
        }
        Long userId = currentUser.getId();
        //todo 校验用户是否合法
        User user = userService.getById(userId);

        User safetyUser = userService.getSafetyUser(user);
        return BaseResponse.ok(safetyUser);

    }

    @GetMapping("/search")
    private BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
        //仅管理员可查询
        if (isNotAdmin(request)) {
           throw new BusinessException(ErrorCode.PARAMS_ERROR,"无权限");

        }


        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like(User::getUsername, username);
        }
        List<User> userList = userService.list(queryWrapper);
        List<User> list = userList.stream()
                .map(user -> userService.getSafetyUser(user))
                .collect(Collectors.toList());
        return BaseResponse.ok(list);

    }

    @PostMapping("/delete")
    private BaseResponse<Boolean> deleteUser(@RequestBody Long id, HttpServletRequest request) {
        if (isNotAdmin(request)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"无权限");
        }

        if (id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"id存在异常");
        }
        boolean result = userService.removeById(id);
        return BaseResponse.ok(result);

    }

    private static boolean isNotAdmin(HttpServletRequest request) {
        //仅管理员可查询
        User userObj = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        return userObj == null || userObj.getUserRole() != ADMIN_ROLE;
    }


}

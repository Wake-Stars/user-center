package com.stars.usercenter.mapper;

import com.stars.usercenter.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Prometheus
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2023-03-16 20:36:49
* @Entity com.stars.usercenter.model.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}





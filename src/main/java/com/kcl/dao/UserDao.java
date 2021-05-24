package com.kcl.dao;

import com.kcl.pojo.User;

import org.apache.ibatis.annotations.Mapper;

/**
 * 项目名： jwt
 * 包名:    com.kcl.dao
 * 文件名   UserDao
 * 创建者
 * 创建时间: 2021/5/24 11:11 AM
 * 描述  ${TODO}
 */

@Mapper
public interface UserDao {
    //根据用户名和密码查询用户
     User getUserByUserNameAndPassword(User user);
}


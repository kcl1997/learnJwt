package com.kcl.service;

import com.kcl.dao.UserDao;
import com.kcl.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名： jwt
 * 包名:    com.kcl.service
 * 文件名   UserService
 * 创建者
 * 创建时间: 2021/5/24 11:19 AM
 * 描述  ${TODO}
 */

@Service
public class UserServiceImpl{

    @Autowired(required = true)
    UserDao mUserDao;

    public User getUserByUserNameAndPassword(User user){
        User user1 = mUserDao.getUserByUserNameAndPassword(user);
        if(user1 != null)
            return user1;
        else{
            throw new RuntimeException("登录失败");
        }
    }
}


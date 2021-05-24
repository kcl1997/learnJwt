package com.kcl.controller;

import com.kcl.pojo.User;
import com.kcl.service.UserServiceImpl;
import com.utils.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目名： jwt
 * 包名:    com.kcl.controller
 * 文件名   UserController
 * 创建者
 * 创建时间: 2021/5/24 11:20 AM
 * 描述  ${TODO}
 */

@Controller
public class UserController {


    @Autowired
    UserServiceImpl mUserService;

    @ResponseBody
    @GetMapping("/user/login")
    public Map<String,Object> login(User user){

        Map<String,Object> map = new HashMap<>();
        try {
            User dbuser = mUserService.getUserByUserNameAndPassword(user);
            //嵌入jwt
            Map<String,String> playload = new HashMap<>();
            playload.put("id",dbuser.getId().toString());
            playload.put("username",dbuser.getPassword());
            String token = JwtUtils.getToken(playload);
            map.put("token",token);

            map.put("code",200);
            map.put("msg","认证成功");

        } catch (Exception e) {
           // e.printStackTrace();
            map.put("code",400);
            map.put("msg","认证失败");
        }
        return  map;

    }



    @GetMapping("/test")
    @ResponseBody
    public Map<String,Object> test(HttpServletRequest request){
        String token = request.getHeader("token");
        String username = JwtUtils.verify(token).getClaim("username").asString();
        String id = JwtUtils.verify(token).getClaim("id").asString();

        Map<String,Object> map = new HashMap<>();
        map.put("state",true);
        map.put("id",id);
        map.put("username",username);
        return  map;
    }
}


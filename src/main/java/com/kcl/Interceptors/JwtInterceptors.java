package com.kcl.Interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.JwtUtils;

import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目名： jwt
 * 包名:    com.kcl.Interceptors
 * 文件名   JwtInterceptors
 * 创建者
 * 创建时间: 2021/5/24 2:19 PM
 * 描述  ${TODO}
 */
public class JwtInterceptors implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //从请求header中获取token
        String token = request.getHeader("token");
        Map<String,Object> map = new HashMap<>();

        try {
            JwtUtils.verify(token);
            return true;   //放行 --> controller继续处理

        } catch (SignatureVerificationException e) {
            map.put("msg","签名不一致异常");
        }catch (TokenExpiredException e){
            map.put("msg","令牌过期异常");
        }catch (AlgorithmMismatchException e){ //不会出现，自己已经规定了
            map.put("msg","算法不匹配异常");
        }catch (InvalidClaimException e){
            map.put("msg","失效Playload异常");
        }catch (Exception e){
            map.put("msg","其他异常");
        }
        map.put("state",false);
        //拦截并返回json信息
        String json = new ObjectMapper().writeValueAsString(map); // map --> json
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}


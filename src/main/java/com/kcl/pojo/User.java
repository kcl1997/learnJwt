package com.kcl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名： jwt
 * 包名:    com.kcl.pojo
 * 文件名   User
 * 创建者
 * 创建时间: 2021/5/24 11:09 AM
 * 描述  ${TODO}
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String salt;
}


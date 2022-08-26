package com.zzp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *@Author:zzp
 *@Date:2022/8/13 13:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String username;
    private String password;
    private String perms;
    private String email;
    private String avatar;
    private Date createTime;
    private Date updateTime;

}

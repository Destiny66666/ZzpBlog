package com.zzp.service;

import com.zzp.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 *@Author:zzp
 *@Date:2022/8/13 13:59
 */
public interface UserService {

    /**
     * 根据用户名查询信息
     */
    User queryUserByName(@Param("username") String username);

}

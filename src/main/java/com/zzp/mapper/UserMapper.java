package com.zzp.mapper;

import com.zzp.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *@Author:zzp
 *@Date:2022/8/13 13:50
 */
@Repository
@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询信息
     */
    User queryUserByName(@Param("username") String username);

}

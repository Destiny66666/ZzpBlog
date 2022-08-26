package com.zzp.service.Impl;

import com.zzp.mapper.UserMapper;
import com.zzp.pojo.User;
import com.zzp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@Author:zzp
 *@Date:2022/8/13 14:10
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserByName(String username) {
        return userMapper.queryUserByName(username);
    }
}

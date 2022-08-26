package com.zzp.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 *
 *@Author:zzp
 *@Date:2022/8/13 14:36
 *@Describe:MD5加密类
 */
public class MD5 {    //添加MD5加密方法
	//输入相对应的盐值和密码，可再添加输入自定义加密次数的方法
    public static String SysMd5(String username,String password) {
        String hashAlgorithmName = "MD5";//加密方式
        ByteSource salt = ByteSource.Util.bytes(username);//以账号作为盐值
        int hashIterations = 1024;//加密1024次
        String newPassword= new SimpleHash(hashAlgorithmName,password,salt,hashIterations).toHex();
        return newPassword;
    }
}

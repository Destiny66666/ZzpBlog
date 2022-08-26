package com.zzp.service;

import com.zzp.pojo.About;

/**
 *@Author:zzp
 *@Date:2022/8/13 13:52
 */
public interface AboutService {

    /**
     * 查询关于我信息
     */
    About queryAbout();

    /**
     * 修改关于我信息
     */
    int updateAbout(About about);
}

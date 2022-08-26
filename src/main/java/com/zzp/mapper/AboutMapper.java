package com.zzp.mapper;

import com.zzp.pojo.About;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *@Author:zzp
 *@Date:2022/8/13 13:41
 */
@Repository
@Mapper
public interface AboutMapper {

    /**
     * 查询关于我信息
     */
    About queryAbout();

    /**
     * 修改关于我信息
     */
    int updateAbout(About about);

}

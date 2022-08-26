package com.zzp.mapper;

import com.zzp.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *@Author:zzp
 *@Date:2022/8/13 13:49
 */
@Repository
@Mapper
public interface TypeMapper {

    /**
     * 新增类型
     */
    int addType(Type type);

    /**
     * 查询全部类型
     */
    List<Type> queryTypeAll();

    /**
     * 根据name查询类别
     */
    Type queryTypeByName(@Param("name") String name);

    /**
     * 根据ID删除类别
     */
    int deleteTypeById(@Param("id") int id);

    /**
     * 修改类别
     */
    int updateTypeById(Type type);

    /**
     * 根据ID查询类别
     */
    Type queryTypeById(@Param("id") int id);

    /**
     * 添加类型文章数量
     */
    int addTypeNumberById(@Param("id") int id);

    /**
     * 减少类型文章数量
     */
    int deleteTypeNumberById(@Param("id") int id);

    /**
     * 查询标签数量
     */
    int queryTypeCount();

}

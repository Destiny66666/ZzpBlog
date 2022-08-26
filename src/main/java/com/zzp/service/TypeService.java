package com.zzp.service;

import com.zzp.pojo.Type;

import java.util.List;

/**
 *@Author:zzp
 *@Date:2022/8/13 13:58
 */
public interface TypeService {
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
    Type queryTypeByName(String name);

    /**
     * 根据ID删除类别
     */
    int deleteTypeById(int id);

    /**
     * 修改类别
     */
    int updateTypeById(Type type);

    /**
     * 根据ID查询类别
     */
    Type queryTypeById(int id);

    /**
     * 添加类型文章数量
     */
    int addTypeNumberById(int id);

    /**
     * 减少类型文章数量
     */
    int deleteTypeNumberById(int id);

    /**
     * 查询标签数量
     */
    int queryTypeCount();

}

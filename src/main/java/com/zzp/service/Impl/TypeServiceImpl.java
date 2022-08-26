package com.zzp.service.Impl;

import com.zzp.mapper.TypeMapper;
import com.zzp.pojo.Type;
import com.zzp.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@Author:zzp
 *@Date:2022/8/13 14:09
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeMapper typeMapper;

    @Override
    public int addType(Type type) {
        return typeMapper.addType(type);
    }

    @Override
    public List<Type> queryTypeAll() {
        return typeMapper.queryTypeAll();
    }

    @Override
    public Type queryTypeByName(String name) {
        return typeMapper.queryTypeByName(name);
    }

    @Override
    public int deleteTypeById(int id) {
        return typeMapper.deleteTypeById(id);
    }

    @Override
    public int updateTypeById(Type type) {
        return typeMapper.updateTypeById(type);
    }

    @Override
    public Type queryTypeById(int id) {
        return typeMapper.queryTypeById(id);
    }

    @Override
    public int addTypeNumberById(int id) {
        return typeMapper.addTypeNumberById(id);
    }

    @Override
    public int deleteTypeNumberById(int id) {
        return typeMapper.deleteTypeNumberById(id);
    }

    @Override
    public int queryTypeCount() {
        return typeMapper.queryTypeCount();
    }


}

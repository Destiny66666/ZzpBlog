package com.zzp.service.Impl;

import com.zzp.mapper.AboutMapper;
import com.zzp.pojo.About;
import com.zzp.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@Author:zzp
 *@Date:2022/8/13 14:03
 */
@Service
public class AboutServiceImpl implements AboutService {

    @Autowired
    AboutMapper aboutMapper;

    @Override
    public About queryAbout() {
        return aboutMapper.queryAbout();
    }

    @Override
    public int updateAbout(About about) {
        return aboutMapper.updateAbout(about);
    }
}

package com.zzp.config;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 *@Author:zzp
 *@Date:2022/8/13 16:38
 */
//配置虚拟路径，通过虚拟路径指向磁盘路径
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //获取jar包所在的目录
        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getSource();
        //在jar包所在目录下生成一个upload文件夹来储存上传的图片
        String disPath = jarF.getParentFile().toString()+"/upload/";

        String os = System.getProperty("os.name");

        if (os.toLowerCase().startsWith("win")) {  //如果是Windows系统
            registry.addResourceHandler("/upload/**").addResourceLocations("file:./"+disPath);
        } else { //linux和mac系统
            registry.addResourceHandler("/upload/**").addResourceLocations("file:./"+disPath);
        }


        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}

package com.zzp.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *@Author:zzp
 *@Date:2022/8/13 14:41
 */
@Configuration
public class ShiroConfig {

    //第三步：ShiroFilterFactoryBean
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSessionManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器
        /*
            anon：无需认证就可以访问
            authc：必须认证了才能访问
            user：必须拥有 记住我 功能才能用
            perms：拥有对某个资源的权限才能访问
            role：拥有某个角色权限才能访问
        */
        Map<String, String> filterMap = new LinkedHashMap<>();
//
//        filterMap.put("/user/add","authc");
//        filterMap.put("/user/update","authc");
        //正常情况下，没有授权会跳转到未授权的页面

        filterMap.put("/admin/**","perms[1]");
        filterMap.put("/admin/*","authc");

        bean.setFilterChainDefinitionMap(filterMap);

        // 被拦截的时候跳到登录页面
        bean.setLoginUrl("/login");

        //设置未授权的页面
        bean.setUnauthorizedUrl("/noauth");

        return bean;
    }

    //第二步：DefaultWebSecurityManager
    @Bean(name = "getDefaultWebSessionManager")
    public DefaultWebSecurityManager getDefaultWebSessionManager(@Qualifier("userRealm") UserRealm userRealm,@Qualifier("webSessionManager") DefaultWebSessionManager sessionManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联userRealm
        securityManager.setRealm(userRealm);

        securityManager.setSessionManager(sessionManager);

        return securityManager;
    }

    //第一步：创建 realm 对象，需要自定义
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();
        //设置加密的方式(一定要设置自定义的加密方式)
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;

    }


    //整合ShiroDialect：用来整合shiro 和 thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }




    //MD5加密
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("md5");
        //加密次数
        credentialsMatcher.setHashIterations(1024);
        //是否存储为16进制(一定要开启)
//        将setStoredCredentialsHexEncoded设置为true，则需要使用toHex()进行转换成字符串，默认使用的是toBase64()
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    /**
     * 解决shiro的URL中出现sessionID的情况
     * EG: localhost:8080/toLogin;jsessionid=D5C1EE61B97EE2D7098F58A837B82BD4
     * @return
     */
    @Bean(name = "webSessionManager")
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionIdUrlRewritingEnabled(false);
        return defaultWebSessionManager;
    }


}


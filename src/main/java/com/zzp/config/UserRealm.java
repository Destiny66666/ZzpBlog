package com.zzp.config;

import com.zzp.pojo.User;
import com.zzp.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *@Author:zzp
 *@Date:2022/8/13 13:45
 */
// 自定义的UserRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=》授权doGetAuthorizationInfo");

        //SimpleAuthorizationInfo
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //拿到当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();   //拿到User对象
        //设置当前用户的权限
        info.addStringPermission(currentUser.getPerms());


        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=》认证doGetAuthenticationInfo");

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //连接数据库 获取信息

        User user = userService.queryUserByName(userToken.getUsername());

        if (user==null){    //没有这个人
            return null;    //UnknownAccountException
        }
        //给浏览器session
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("loginUser",user);
        String password = user.getPassword();

        //盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());//使用账号作为盐值
        String realmName = this.getName();
        System.out.println(password);
        //密码认证，shiro自动帮你认证

        return new SimpleAuthenticationInfo(user,password,credentialsSalt,realmName);
    }
}

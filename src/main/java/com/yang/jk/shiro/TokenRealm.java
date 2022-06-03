package com.yang.jk.shiro;

import com.yang.jk.pojo.po.SysRole;
import com.yang.jk.pojo.vo.Role;
import com.yang.jk.pojo.vo.UserPermissVo;
import com.yang.jk.service.RedisService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther yhjStart
 * @create 2022-03-31 16:14
 */
public class TokenRealm extends AuthorizingRealm {

    @Autowired
    private RedisService redisService;
    public TokenRealm(CredentialsMatcher credentialsMatcher) {
        super(credentialsMatcher);
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof Token;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
        String token = (String) primaryPrincipal;
        Object o = redisService.get(token);
        UserPermissVo userPermissVo = (UserPermissVo) o;
        List<Role> roles = userPermissVo.getRoles();
        List<String> collect = roles.stream().map(e -> e.getRole_name()).collect(Collectors.toList());
        simpleAuthorizationInfo.addRoles(collect);
        for (Role role : roles) {
            List<String> list = role.getData().stream().map(e -> e.getPermission_name()).collect(Collectors.toList());
            if (list.size()>=0) {

                simpleAuthorizationInfo.addStringPermissions(list);
            }
        }
//        if (o!=null) {
//            simpleAuthorizationInfo.addStringPermission("SysUser:list");
//        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = ((Token)authenticationToken).getToken();
        return new SimpleAuthenticationInfo(token,token,getName());
    }
}

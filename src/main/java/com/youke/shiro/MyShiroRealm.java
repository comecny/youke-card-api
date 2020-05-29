package com.youke.shiro;

import com.youke.entity.BackgroudPermissions;
import com.youke.service.BackgroudUserService;
import com.youke.vo.BackgroudUserVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class MyShiroRealm extends AuthorizingRealm  {

    static Logger logger = LogManager.getLogger(MyShiroRealm.class.getName());

    @Autowired
    private BackgroudUserService backgroudUserService;

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.debug("-----------------------------认证-------------------------------------");
        String username = (String)token.getPrincipal();
        BackgroudUserVO userInfo = backgroudUserService.getUserInfo(username);
        if (userInfo == null || userInfo.getBcakgroudUserPassword() == null) return null;

        SimpleAuthenticationInfo authc = new SimpleAuthenticationInfo(
                userInfo,
                userInfo.getBcakgroudUserPassword(),
                ByteSource.Util.bytes("youke_20200525DIchK487WCXRAQ"),
                this.getName()
        );
        logger.debug("-----------------------------认证完毕------------------------------------");
        return authc;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.debug("-------------------------------授权------------------------------------");
        BackgroudUserVO userInfo =(BackgroudUserVO) principalCollection.getPrimaryPrincipal();
        userInfo.getRole();
        SimpleAuthorizationInfo authz = new SimpleAuthorizationInfo();
        authz.addRole(userInfo.getRole());
        ArrayList<String> list = new ArrayList<>();
        List<BackgroudPermissions> listPermissins = userInfo.getListPermissins();
        for (BackgroudPermissions listPermissin : listPermissins) {
            list.add(listPermissin.getDescribes());
        }
        logger.info("该角色所有权限（）："+list);
        authz.addStringPermissions(list);
        return authz;
    }

}


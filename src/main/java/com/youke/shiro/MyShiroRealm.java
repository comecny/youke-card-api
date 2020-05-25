package com.youke.shiro;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youke.dao.BackgroudUserMapper;
import com.youke.vo.BackgroudUserVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


public class MyShiroRealm extends AuthorizingRealm {

    static Logger logger = LogManager.getLogger(MyShiroRealm.class.getName());

    @Autowired
    private BackgroudUserMapper backgroudUserMapper;

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("-----------------------------认证-------------------------------------");
        String username = (String)token.getPrincipal();
        BackgroudUserVO userInfo = backgroudUserMapper.getUserInfo(username);
        if (userInfo == null || userInfo.getBcakgroudUserPassword() == null) return null;

        SimpleAuthenticationInfo authc = new SimpleAuthenticationInfo(
                userInfo,
                userInfo.getBcakgroudUserPassword(),
                ByteSource.Util.bytes("youke_20200525DIchK487WCXRAQ"),
                this.getName()
        );
        logger.info("-----------------------------认证完毕-------------------------------------");
        return authc;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("-------------------------------授权------------------------------------");
        return null;
    }
}


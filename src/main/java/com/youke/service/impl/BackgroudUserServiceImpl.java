package com.youke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youke.common.exception.db.InsertException;
import com.youke.dao.UserDao;
import com.youke.entity.User;
import com.youke.vo.BackgroudUserVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youke.dao.BackgroudUserMapper;
import com.youke.entity.BackgroudUser;
import com.youke.service.BackgroudUserService;

import java.util.List;
import java.util.Map;

@Service
public class BackgroudUserServiceImpl implements BackgroudUserService{

    static Logger logger = LogManager.getLogger(BackgroudUserServiceImpl.class.getName());

    @Autowired
    private BackgroudUserMapper backgroudUserMapper;

    @Autowired
    private UserDao userDao;

    @Override
    public int insertBackgroudUser(BackgroudUser backgroudUser) {
        //先查是否存在可用的用户
        QueryWrapper<BackgroudUser> backgroudUserQueryWrapper = new QueryWrapper<>();
        backgroudUserQueryWrapper.setEntity(
                BackgroudUser
                .builder()
                .status("0")
                .backgroudUserName(backgroudUser.getBackgroudUserName())
                .build());
        Integer count = backgroudUserMapper.selectCount(backgroudUserQueryWrapper);
        if (count > 0) return -10;

        //如果不存在则直接插入
        //先将传递过来的密码从进行MD5加密
        String md5Password = new Md5Hash(backgroudUser.getBcakgroudUserPassword(),
                "youke_20200525DIchK487WCXRAQ", 2).toString();
        backgroudUser.setBcakgroudUserPassword(md5Password);
        backgroudUserMapper.insertSelective(backgroudUser);
        Integer backgroudUserId = backgroudUser.getId();
        logger.info("新增后台用户返回主键id: "+backgroudUserId);

        //将返回的后台主键id和roleid添加到用户和角色关联表
        boolean success = backgroudUserMapper.insertBackUserRelRole(backgroudUserId,backgroudUser.getRoleId());
        if (!success) throw new InsertException("新增后台用户和角色关联表异常");

        //最后去讲将前台user表中的后台用户标识设为1
        User user = User.builder().backUserSign(1).id(backgroudUser.getUserId()).build();
        return userDao.updateUser(user);
    }

    @Override
    public  IPage<Map<String, Object>> listBackGroudUserPaging(Integer page, Integer length) {
        QueryWrapper<BackgroudUser> userQueryWrapper = new QueryWrapper<BackgroudUser>()
                .setEntity(BackgroudUser.builder().status("0").build());
        return backgroudUserMapper.listBackGroudUserPaging(new Page<BackgroudUserVO>(page,length),userQueryWrapper);
    }
}

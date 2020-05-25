package com.youke.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youke.entity.BackgroudUser;
import com.youke.vo.BackgroudUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface BackgroudUserMapper extends BaseMapper<BackgroudUser>  {

    int deleteByPrimaryKey(Integer id);

    int insert(BackgroudUser record);

    int insertSelective(BackgroudUser record);

    BackgroudUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BackgroudUser record);

    int updateByPrimaryKey(BackgroudUser record);

    boolean insertBackUserRelRole(@Param("bUserId")Integer backgroudUserId, @Param("bUserRoleId")Integer roleId);

    int checkRole(@Param("bUserRoleId")Integer id);

    Page<Map<String, Object>> listBackGroudUserPaging(Page<BackgroudUserVO> objectPage,
                                                      @Param(Constants.WRAPPER)Wrapper<BackgroudUser> userQueryWrapper);

    BackgroudUserVO getUserInfo(@Param("username")String username);
}
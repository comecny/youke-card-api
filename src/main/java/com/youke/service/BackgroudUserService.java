package com.youke.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.entity.BackgroudUser;
import com.youke.vo.BackgroudUserVO;

import java.util.Map;

public interface BackgroudUserService{

    int insertBackgroudUser(BackgroudUser backgroudUser);

    IPage<Map<String, Object>> listBackGroudUserPaging(Integer page, Integer length);
}

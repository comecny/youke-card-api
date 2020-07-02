package com.youke.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.dao.BackgroundMenuMapper;
import com.youke.entity.BackgroundMenu;
import com.youke.entity.BackgroundMenuRelRole;
import com.youke.service.BackgroundMenuService;
import com.youke.vo.BackgroundMenuRelRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
public class BackgroundMenuServiceImpl extends ServiceImpl<BackgroundMenuMapper, BackgroundMenuRelRole> implements BackgroundMenuService {

    @Autowired
    private BackgroundMenuMapper backgroundMenuMapper;

    @Override
    public Map listMenu(Integer roleId) {
        List<BackgroundMenu> listMneu = backgroundMenuMapper.listMneu(roleId);

        ArrayList<BackgroundMenu> total = new ArrayList<>();

        for (int i = 0; i < listMneu.size(); i++) {
            //判定所有菜单中没有父级菜单的的选项
            if (listMneu.get(i).getPid() == 0) {
                total.add(listMneu.get(i));
            }
        }

        for (BackgroundMenu info : total) {
            getChilds(info.getId(), listMneu);
            info.setChildList(getChilds(info.getId(), listMneu));
        }

        Map map = new HashMap<>();
        map.put("menu", total);
        return map;
    }

    @Override
    public int insertRoleRelMenu(BackgroundMenuRelRole menuRelRole) {
        return backgroundMenuMapper.insert(menuRelRole);
    }

    @Override
    public int deleteRoleRelMenu(BackgroundMenuRelRoleVO backgroundMenuRelRole) {
        List<BackgroundMenuRelRole> list = backgroundMenuRelRole.getList();
       return backgroundMenuMapper.deleteBatch(list);
    }

    @Override
    public List<BackgroundMenuRelRole> listUserMenuByRoleId(Integer roleId) {
        return backgroundMenuMapper.
                selectList(new QueryWrapper<BackgroundMenuRelRole>()
                        .select("b_menu_id")
                        .setEntity(BackgroundMenuRelRole
                                .builder()
                                .bUserRoleId(roleId)
                                .build()));
    }

    private static List<BackgroundMenu> getChilds(Integer id, List<BackgroundMenu> list){

            //子菜单
            List<BackgroundMenu> childList = new ArrayList<>();
            //遍历所有节点，将父级菜单与传过来的id做比较
            for (BackgroundMenu info: list){

                if (info.getPid() != 0){
                    //若存在父节点，则比较
                    if (info.getPid().equals(id)){
                        childList.add(info);
                    }
                }
            }

            for (BackgroundMenu menu: childList){
                //去查有没有父节点
                if (menu.getPid() != 0){

                    //递归开始
                    menu.setChildList(getChilds(menu.getId(),list));
                }
            }
            //退出条件
            if (childList.size() == 0){
                return null;
            }
            return childList;
        }




}

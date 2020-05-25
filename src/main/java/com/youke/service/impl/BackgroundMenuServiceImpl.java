package com.youke.service.impl;

import com.youke.dao.BackgroundMenuMapper;
import com.youke.entity.BackgroundMenu;
import com.youke.service.BackgroundMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
public class BackgroundMenuServiceImpl implements BackgroundMenuService {

    @Autowired
    private BackgroundMenuMapper backgroundMenuMapper;

    @Override
    public Map listMenu() {
        Integer roleId = 1;
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


        public static List<BackgroundMenu> getChilds(Integer id, List<BackgroundMenu> list){

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

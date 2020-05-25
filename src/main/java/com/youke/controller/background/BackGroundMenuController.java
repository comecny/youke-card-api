package com.youke.controller.background;

import com.youke.result.MsgCode;
import com.youke.result.Result;
import com.youke.service.BackgroundMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("background/menu")
public class BackGroundMenuController {

    @Autowired
    private BackgroundMenuService menuService;

    @GetMapping("listMenu")
    @ApiOperation("菜单列表查询")
    public Result<Map> listMenu(){
      Map map = menuService.listMenu();
        return new Result<Map>(map, MsgCode.FIND_SUCCESS);
    }
}

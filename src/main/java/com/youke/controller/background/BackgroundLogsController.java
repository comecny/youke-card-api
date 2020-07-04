package com.youke.controller.background;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.entity.BackgroudLogs;
import com.youke.service.BackgroudLogsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("logs")
public class BackgroundLogsController {

    @Autowired
    private BackgroudLogsService logsService;

    @GetMapping("listLogsPaging")
    @ApiOperation("分页查询操作日志")
    public Result<IPage<BackgroudLogs>> listLogsPaging(Integer page,Integer length){
       IPage<BackgroudLogs> iPage = logsService.listLogsPaging(page,length);
       return new Result<IPage<BackgroudLogs>>(iPage, MsgCode.FIND_SUCCESS);
    }
}

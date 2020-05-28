package com.youke.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.entity.Banner;
import com.youke.service.BannerService;
import com.youke.utils.DateUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @PostMapping("insertBanner")
    @ApiOperation("新增banner图")
    public Result<Void> insertBanner(@RequestBody Banner banner){
        banner.setCreateTime(DateUtil.nowDate());
        boolean success = bannerService.save(banner);
        if (success){
            return new Result<Void>(null, MsgCode.IINSERT_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }

    @PutMapping("updateBanner")
    @ApiOperation("更新banner图")
    public Result<Void> updateBanner(@RequestBody Banner banner){
        banner.setUpdateTime(DateUtil.nowDate());
        boolean success = bannerService.updateById(banner);
        if (success){
            return new Result<Void>(null,MsgCode.UPDATE_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.UPDATE_FAIL);
    }

    @DeleteMapping("deleteBanner")
    @ApiOperation("删除banner")
    public Result<Void> deleteBanner(@RequestBody Banner banner){
        banner.setUpdateTime(DateUtil.nowDate());
        banner.setStatus("1");
        boolean success = bannerService.updateById(banner);
        if (success){
            return new Result<Void>(null,MsgCode.DELETE_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.DELETE_FAIL);
    }

    @GetMapping("listBanner")
    @ApiOperation("查询banner列表")
    public Result<List<Banner>> listBanner(){
        List<Banner> list = bannerService.list(new QueryWrapper<Banner>().setEntity(Banner.builder().status("0").build()));
        return new Result<List<Banner>>(list,MsgCode.FIND_SUCCESS);
    }
}

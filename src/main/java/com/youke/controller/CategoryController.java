package com.youke.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.entity.Industry;
import com.youke.entity.ProductsAttribute;
import com.youke.entity.ShopsScore;
import com.youke.service.IndustryService;
import com.youke.service.ProductsAttributeService;
import com.youke.utils.DateUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private IndustryService industryService;

    @Autowired
    private ProductsAttributeService attributeService;

    @PostMapping("insertIndustry")
    @ApiOperation("新增行业分类")
    public Result<Void> insertIndustry(@RequestBody Industry industry){
        industry.setCreateTime(DateUtil.nowDate());
        boolean success = industryService.save(industry);
        if (success){
            return new Result<Void>(null, MsgCode.IINSERT_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }

    @PutMapping("updateIndustryById")
    @ApiOperation("修改行业分类")
    public Result<Void> updateIndustryById(@RequestBody Industry industry){
        industry.setUpdateTime(DateUtil.nowDate());
        boolean success = industryService.updateById(industry);
        if (success){
            return new Result<Void>(null,MsgCode.UPDATE_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.UPDATE_FAIL);
    }

    @DeleteMapping("deleteIndustryById")
    @ApiOperation("删除行业分类")
    public Result<Void> deleteIndustryById(@RequestBody Industry industry){
        industry.setStatus("1");
        industry.setUpdateTime(DateUtil.nowDate());
        boolean success = industryService.updateById(industry);
        if (success){
            return new Result<Void>(null,MsgCode.DELETE_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.DELETE_FAIL);
    }

    @GetMapping("listIndustry")
    @ApiOperation("查询行业列表")
    public Result<List<Industry>> listIndustry(){
        List<Industry> list = industryService.list(new QueryWrapper<Industry>()
                .setEntity(Industry.builder().status("0").build()));
        return new Result<List<Industry>>(list,MsgCode.FIND_SUCCESS);
    }

    @PostMapping("insertAttribute")
    @ApiOperation("新增属性")
    public Result<Void> insertAttribute(@RequestBody ProductsAttribute attribute){
        attribute.setCreateTime(DateUtil.nowDate());
        attributeService.save(attribute);
        return new Result<Void>(null,MsgCode.IINSERT_SUCCESS);
    }

    @PutMapping("updateAttribute")
    @ApiOperation("修改属性")
    public Result<Void> updateAttribute(@RequestBody ProductsAttribute attribute){
        attribute.setUpdateTime(DateUtil.nowDate());
        attributeService.updateById(attribute);
        return new Result<Void>(null,MsgCode.UPDATE_SUCCESS);
    }

    @GetMapping("getAttribute")
    @ApiOperation("查询属性")
    public Result<IPage<ProductsAttribute>> getAttribute(Integer page, Integer length){
       IPage<ProductsAttribute> iPage = attributeService.getAttribute(page,length);
       return new Result<IPage<ProductsAttribute>>(iPage,MsgCode.FIND_SUCCESS);
    }

    @DeleteMapping("deleteAttribure")
    @ApiOperation("删除属性")
    public Result<Void> deleteAttribure(Integer id){
        boolean b = attributeService.removeById(id);
        return new Result<Void>(null,MsgCode.DELETE_SUCCESS);
    }


}

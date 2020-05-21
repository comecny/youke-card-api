package com.youke.controller;

import com.youke.result.MsgCode;
import com.youke.result.Result;
import com.youke.utils.CosUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @ApiOperation(value = "文件上传公用接口")
    @PostMapping(value = "/uplodFiles")
    public Result<String> uplodFiles(MultipartFile file) {
        return new Result<String>(CosUtil.upload(file),MsgCode.SUCCESS);
    }
}

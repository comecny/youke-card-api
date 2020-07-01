package com.youke.controller;

import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.utils.CosUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @ApiOperation(value = "文件上传公用接口")
    @PostMapping(value = "/uplodFiles")
    public Result<Map> uplodFiles(MultipartFile file) {
       String file1 = CosUtil.upload(file);
        Map objectObjectMap = new HashMap();
        String substring = file1.substring(file1.length() - 4, file1.length());
        if (".mp4".equals(substring)){
            objectObjectMap.put("type",2);
            objectObjectMap.put("file",file1);
        }else {
            objectObjectMap.put("type",1);
            objectObjectMap.put("file",file1);
        }
        return new Result<Map>(objectObjectMap,MsgCode.SUCCESS);
    }
}

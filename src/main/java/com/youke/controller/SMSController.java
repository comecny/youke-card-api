package com.youke.controller;


import com.youke.common.result.Result;
import com.youke.utils.SMSUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("sms")
public class SMSController {
    @GetMapping("sendSms")
    public Map<String,Object> sendSms(String phone){
        String rev = SMSUtils.send(phone);
        Map<String, Object> map = new HashMap<>();
        map.put("num",rev);
        return map;
    }
}

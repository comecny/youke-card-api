package com.youke.controller;

import com.youke.config.BaseConfiguration;
import com.youke.utils.OpenidUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/openid")
@RestController
public class OpenIdController {

    static Logger logger = LogManager.getLogger(OpenIdController.class.getName());

    @Autowired
    private BaseConfiguration configuration;

    /**
     * 传入:
     *appid、code
     * @param
     * @return
     */
    @ApiOperation(value = "获取openId")
    @PostMapping(value = "/getOpenId/{code}")
    @ResponseBody
    public Map getOpenId(@PathVariable("code") String code) {
        logger.info(code);
       // String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+ configuration.getAppid() +"&secret="+ configuration.getSecret()+"&js_code=" + code + "&grant_type=authorization_code";
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+ configuration.getDevAppid() +"&secret="+ configuration.getDevSecret()+"&js_code=" + code + "&grant_type=authorization_code";
        //获取openId
        Map map =  OpenidUtil.get(url);
        return map;
    }

    /**
     *
     * @param
     * @return
     */
    @ApiOperation(value = "获取access_token")
    @PostMapping(value = "/getAccessToken")
    @ResponseBody
    public Map getAccessToken(String appid,String secret) {
        String grantType = "client_credential";
        String url = "https://api.weixin.qq.com/cgi-bin/token?appid="+ configuration.getAppid()+"&secret="+ configuration.getSecret()+"&grant_type="+grantType;
        //获取openId
        Map map =  OpenidUtil.get(url);
        return map;
    }

}

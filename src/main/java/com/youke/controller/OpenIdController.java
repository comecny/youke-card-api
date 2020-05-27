package com.youke.controller;

import com.youke.utils.OpenidUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/openid")
@RestController
public class OpenIdController {

    static Logger logger = LogManager.getLogger(OpenIdController.class.getName());

    /**
     * 传入:
     *appid、code
     * @param
     * @return
     */
    @ApiOperation(value = "获取openId")
    @PostMapping(value = "/getOpenId")
    @ResponseBody
    public Map getOpenId(String appid,String secret,String code) {
        logger.info(appid);
        logger.info(secret);
        logger.info(code);
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+ appid +"&secret="+ secret+"&js_code=" + code + "&grant_type=authorization_code";
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
        String url = "https://api.weixin.qq.com/cgi-bin/token?appid="+ appid+"&secret="+ secret+"&grant_type="+grantType;
        //获取openId
        Map map =  OpenidUtil.get(url);
        return map;
    }

}

package com.youke.utils;


import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SMSUtils {

    private static final int appid =1400401950;
    private static final String appkey = "42b3cc2c68a59c395f1d873f7aad60ff";

    public static String send(String phoneNumbers) {
        int yzm = random();
        String num = Integer.toString(yzm);
        SmsSingleSenderResult result = null;
        try {
            ArrayList<String> params = new ArrayList<String>();
            params.add(num);
            params.add("2");
            String smsSign = "优客网络"; // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
             result = ssender.sendWithParam("86", phoneNumbers, 632944, params, smsSign, null, null);
            //   result = ssender.send(0,"86", phoneNumbers,smsSign, "", "");
            if (result.result == 0) {
                //发送成功返回验证码
                return num;
            }
        } catch (HTTPException e) {
            //System.out.println("HTTP响应码错误");
            return result.toString();
        } catch (JSONException e) {
            //System.out.println("json解析错误");
            return result.toString();
        } catch (IOException e) {
            //System.out.println(" 网络IO错误");
            return result.toString();
        } catch (com.github.qcloudsms.httpclient.HTTPException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /*
      生成6位验证码
   */
    private static int random() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        int[] c = new int[6];
        for (int i = 0; i < 6; i++) {
            c[i] = r.nextInt(9) + 1;
            sb.append(c[i]);
        }
        return Integer.parseInt(sb.toString());

    }
}

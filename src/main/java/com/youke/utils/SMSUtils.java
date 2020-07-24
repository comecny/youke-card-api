package com.youke.utils;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.youke.Application;
import com.youke.config.BaseConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SMSUtils {

  private static final BaseConfiguration baseConfiguration = Application.applicationContext.getBean(BaseConfiguration.class);
    private static final Logger logger = LogManager.getLogger(SMSUtils.class);
    public static String send(String phoneNumbers) {
        int yzm = random();
        String num = Integer.toString(yzm);
        SmsSingleSenderResult result = null;
        try {
            ArrayList<String> params = new ArrayList<String>();
            params.add(num);
            params.add("2");
            String smsSign = baseConfiguration.getSmsSign();
            SmsSingleSender ssender = new SmsSingleSender(baseConfiguration.getSmsAppid(), baseConfiguration.getSmsAppkey());
            logger.info("send ："+"{ phone:"+phoneNumbers+","+"num ："+num+" }");
             result = ssender.sendWithParam("86", phoneNumbers, 632944, params, smsSign, null, null);
            if (result.result == 0) {
                logger.info("recv ："+"{ "+"num ："+num+" }");
                return num;
            }
        } catch (HTTPException e) {
            logger.error(e);
            return result.toString();
        } catch (JSONException e) {
            logger.error(e);
            return result.toString();
        } catch (IOException e) {
            logger.error(e);
            return result.toString();
        } catch (com.github.qcloudsms.httpclient.HTTPException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return result.toString();
    }

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

package com.youke.controller;


import com.youke.utils.UUIDUtil;
import com.youke.utils.sdkpay.WXPayUtil;
import com.youke.utils.wxPay.WxPaySignatureUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RequestMapping("/pay")
@RestController
public class PayController {

    static Logger logger = LogManager.getLogger(PayController.class.getName());

    @ApiOperation(value = "订单支付接口")
    @PostMapping(value = "/payOrder")
    public Map payOrder(HttpServletRequest request) throws Exception {

        //抽取前端传递过来的订单信息
        String openid = null;
        Integer orderId = null;
        String totalPrice = null;

        //微信支付所需要的信息
        String appid = "wx969ddfb12e79f74a";
        String mch_id = "1566132431";
        String key = "bgrtuoHkGwefwGIOI1236804jVkfqhqf";
        String notify_url = ""; //回调URL
        String TRADETYPE = "JSAPI";
        String uuid = UUIDUtil.get32UUID();
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

        //金额转换
        BigDecimal price = new BigDecimal(totalPrice);
        BigDecimal price1 = new BigDecimal("100");
        BigDecimal bignum3 = null;
        bignum3 = price.multiply(price1);
        int i = bignum3.intValue();
        String total_fee = String.valueOf(i);

        //调起微信支付
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        Map<String, String> map = new TreeMap<String, String>();
        map.put("appid", appid);
        map.put("body", "鲜的FANr");
        map.put("mch_id", mch_id);
        map.put("nonce_str", uuid); // 随机串
        map.put("notify_url", notify_url); // 通知URL地址
        map.put("openid", openid); // 用户微信唯一标识
        String time = String.valueOf(Calendar.getInstance().getTimeInMillis());
        String out_trade_no = WXPayUtil.generateNonceStr();
        map.put("out_trade_no", time); //此参数填写订单的时间戳
        String ip = findIP(request);
        map.put("spbill_create_ip", ip);  //用户端的IP地址
        map.put("total_fee", total_fee); // 此参数为订单金额
        map.put("trade_type", TRADETYPE);
        String str = WxPaySignatureUtils.signature(map, key);
        logger.info("----------------"+str);
        logger.info("-----------订单金额-------------"+total_fee);
        map.put("sign", str);
        logger.info("--------------"+map2xml(map));
        ResponseEntity<String> responseEntity = restTemplate().postForEntity(url, map2xml(map), String.class);
        System.out.println("pay" + responseEntity.getBody());
        Map m = xmlString2Map(responseEntity.getBody());

            Map<String, String> map2 = new TreeMap<String, String>();
            map2.put("appId", appid);
            map2.put("nonceStr", uuid);
            map2.put("package", "prepay_id=" + m.get("prepay_id"));
            map2.put("signType", "MD5");
            map2.put("timeStamp", time);
            String twoSign = WxPaySignatureUtils.signature(map2, key);
            map2.put("paySign", twoSign);
            logger.info("----------------------订单回调信息-----------------------");
            return map2;
    }

    public String payResult(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        StringBuilder xmlStr = new StringBuilder();
        BufferedReader reader = request.getReader();

        String line = null;
        while ((line = reader.readLine()) != null) {
            xmlStr.append(line);
        }
        String result = xmlStr.toString();
        logger.info(result);
        File file = new File("C:" + File.separator + "test.txt");
        if (!file.getParentFile().exists()) {
            boolean mkdirs = file.getParentFile().mkdirs();
        }
        //2：准备输出流
        Writer out = new FileWriter(file);
        out.write(result);
        out.close();
        Map res = xmlString2Map(result);
        logger.info("----------------------------");
        String nonceStr = (String) res.get("nonce_str");//订单随机数
        logger.info("-------------订单随机数---------------"+nonceStr);
        String transaction_id = (String) res.get("transaction_id");  //微信支付订单号
        String out_trade_no = (String) res.get("out_trade_no");    //商户订单号
        logger.info("-------------商户订单号---------------"+out_trade_no);
        String payTime = (String) res.get("time_end");    //支付时间

        //回调成功
            return "<xml>   <return_code><![CDATA[SUCCESS]]></return_code>  <return_msg><![CDATA[OK]]></return_msg></xml>     ";

         //支付回调失败
       // return "<xml>   <return_code><![CDATA[FAIL]]></return_code>  <return_msg><![CDATA订单没有交易单号]></return_msg></xml>     ";

    }

    public static Map<String, Object> xmlString2Map(String xmlStr) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Document doc = DocumentHelper.parseText(xmlStr);
        Element el = doc.getRootElement();
        map = recGetXmlElementValue(el, map);
        return map;
    }

    //查询客户端IP地址
    public static String findIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String map2xml(Map<String, String> map) throws Exception {
        StringBuffer sb = new StringBuffer("");
        sb.append("<xml>");
        Set<String> set = map.keySet();
        for (Iterator<String> it = set.iterator(); it.hasNext(); ) {
            String key = it.next();
            Object value = map.get(key);
            if (!key.equals("sign")) {
                sb.append("<").append(key).append(">").append("<![CDATA[");
                sb.append(value);
                sb.append("]]>").append("</").append(key).append(">");
            } else {
                sb.append("<").append(key).append(">");
                sb.append(value);
                sb.append("</").append(key).append(">");
            }
        }
        sb.append("</xml>");
        return sb.toString();

    }

    private static Map<String, Object> recGetXmlElementValue(Element ele, Map<String, Object> map) {
        List<Element> eleList = ele.elements();
        if (eleList.size() == 0) {
            map.put(ele.getName(), ele.getTextTrim());
            return map;
        } else {
            for (Iterator<Element> iter = eleList.iterator(); iter.hasNext(); ) {
                Element innerEle = iter.next();
                recGetXmlElementValue(innerEle, map);
            }
            return map;
        }
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }
}

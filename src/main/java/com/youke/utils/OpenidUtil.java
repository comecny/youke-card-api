package com.youke.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class OpenidUtil {

    public static Map get(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringBuffer sb = new StringBuffer();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            System.out.println("Res:" + (entity.getContent()));
            InputStreamReader reader = new InputStreamReader(entity.getContent(), "utf-8");
            char[] charbufer;
            while (0 < reader.read(charbufer = new char[10])) {
                sb.append(charbufer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpGet.releaseConnection();
        }
        JSONObject json = new JSONObject(sb.toString());
        System.out.println(json);
        String openid = null;
        String sessionKey = null;
        String accessToken = null;
        if (json.has("access_token")) {
            accessToken = (String) json.get("access_token");
            Map map = new HashMap<>();
            map.put("access_token", accessToken);
            return map;
        } else {
            openid = (String) json.get("openid");
            sessionKey = (String) json.get("session_key");
            Map map = new HashMap();
            map.put("openId", openid);
            map.put("sessionKey", sessionKey);
            return map;
        }
    }
}

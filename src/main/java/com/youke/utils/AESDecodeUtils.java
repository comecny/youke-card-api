package com.youke.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;

public class AESDecodeUtils {

    public static void main(String[] args) throws Exception {
        byte[] encryptedData = Base64.decodeBase64("通过小程序授权获取到的encryptedData");
        byte[] ivData = Base64.decodeBase64("通过小程序授权获取到的iv");
        byte[] session_key = Base64.decodeBase64("通过自建服务器请求微信后台获取到的session_key");
        System.out.println(decrypt(session_key,ivData,encryptedData));
    }

    public static String decrypt(byte[] key, byte[] iv, byte[] encData) throws Exception {
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        //解析解密后的字符串
        return new String(cipher.doFinal(encData),"UTF-8");
    }
}

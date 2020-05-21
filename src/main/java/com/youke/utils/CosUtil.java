package com.youke.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.tencent.cloud.CosStsClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.util.TreeMap;

public class CosUtil {

    static Logger logger = LogManager.getLogger(com.youke.utils.CosUtil.class.getName());

    //腾讯云的SecretId
    private static String secretId ="AKID1yhBUdKEeuvjHdT2xe4Ml26MM9hUDdsw";
    //腾讯云的SecretKey
    private static String secretKey = "34pLlvvaFepzy84irlPuymZBX0jCyUC7";
    //腾讯云的bucket (存储桶)
    private static String bucket = "mytos-1302163130";
    //腾讯云的region(bucket所在地区)
    private static String region = "ap-nanjing";
    //腾讯云的allowPrefix(允许上传的路径)
    private static String allowPrefix = "*";
    //腾讯云的临时密钥时长(单位秒)
    private static String durationSeconds = "1800";
    //腾讯云的访问基础链接:
    private static String baseUrl ="https://mytos-1302163130.cos.ap-nanjing.myqcloud.com";


    public static String upload(MultipartFile file)  {
        String rtValue = null;
        FileOutputStream os = null;
        String filename = file.getOriginalFilename();
        StringBuilder path = new StringBuilder("log/");
        //获取时间戳
        Date fileDate = new Date();
        StringBuilder fileName = new StringBuilder(String.valueOf(fileDate.getTime()));
        //获取时间文件夹,并且与时间戳进行拼接
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = (localDateTime.format(dateTimeFormatter));
        StringBuilder newName = new StringBuilder(date);
        path.append(newName.toString());
        path.append("/");
        path.append(fileName);
        String filename3 = file.getOriginalFilename();
        String extName = filename3.substring(filename3.lastIndexOf("."));
        path.append(extName);
        String yuming1 = path.toString();
        File newFile = new File(filename);

        JSONObject temp = getTempKey();

        String tmpSecretId = temp.getJSONObject("credentials").getString("tmpSecretId");
        String tmpSecretKey = temp.getJSONObject("credentials").getString("tmpSecretKey");
        String sessionToken = temp.getJSONObject("credentials").getString("sessionToken");

        COSCredentials cred = new BasicCOSCredentials(tmpSecretId, tmpSecretKey);

        ClientConfig clientConfig = new ClientConfig(new Region(region));

        COSClient cosclient = new COSClient(cred, clientConfig);

        String bucketName = bucket;

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, yuming1, newFile);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setSecurityToken(sessionToken);
        putObjectRequest.setMetadata(objectMetadata);
        try {
            os = new FileOutputStream(newFile);
            os.write(file.getBytes());
            os.close();
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            // 成功：putobjectResult 会返回文件的 etag
            String etag = putObjectResult.getETag();
            rtValue = baseUrl + path;
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }catch (CosServiceException e) {
                e.printStackTrace();
        }finally {
            //删除本地文件并关闭客户端
            DeleteFileUtil.delete(newFile.getAbsolutePath());
            cosclient.shutdown();
            //返回文件的网络访问url
            return rtValue;
        }
    }

    private static JSONObject getTempKey() {
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        try {//使用永久密钥生成临时密钥
            config.put("SecretId", secretId);
            config.put("SecretKey", secretKey);
            config.put("durationSeconds", Integer.parseInt(durationSeconds));
            config.put("bucket", bucket);
            config.put("region", region);
            config.put("allowPrefix", allowPrefix);
            //密钥的权限列表，其他权限列表请看
            //https://cloud.tencent.com/document/product/436/31923
            String[] allowActions = new String[]{
                    // 简单上传
                    "name/cos:PutObject",
                    // 表单上传、小程序上传
                    "name/cos:PostObject",
                    // 分片上传
                    "name/cos:InitiateMultipartUpload",
                    "name/cos:ListMultipartUploads",
                    "name/cos:ListParts",
                    "name/cos:UploadPart",
                    "name/cos:CompleteMultipartUpload"
            };
            config.put("allowActions", allowActions);
            JSONObject credential = CosStsClient.getCredential(config);
            //成功返回临时密钥信息，如下打印密钥信息
            return credential;
        } catch (Exception e) {
            //失败抛出异常
            throw new IllegalArgumentException("no valid secret !");
        }
    }

}

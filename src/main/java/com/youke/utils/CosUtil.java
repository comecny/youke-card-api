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
import com.qcloud.vod.VodUploadClient;
import com.tencent.cloud.CosStsClient;
import com.youke.Application;
import com.youke.config.BaseConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.util.TreeMap;

public class CosUtil {

    private static final Logger logger = LogManager.getLogger(com.youke.utils.CosUtil.class.getName());
    static BaseConfiguration baseConfiguration = Application.applicationContext.getBean(BaseConfiguration.class);

    public static String upload(MultipartFile file)  {
        String rtValue = null;
        FileOutputStream os = null;
        String filename = file.getOriginalFilename();
        StringBuilder path = new StringBuilder("/static/");
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

        ClientConfig clientConfig = new ClientConfig(new Region(baseConfiguration.getRegion()));

        COSClient cosclient = new COSClient(cred, clientConfig);

        String bucketName = baseConfiguration.getBucket();

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
            rtValue = baseConfiguration.getBaseUrl() + path;
            } catch (IOException | CosServiceException e) {
                e.printStackTrace();
            } finally {
            //删除本地文件并关闭客户端
          DeleteFileUtil.delete(new File(filename).getAbsolutePath());
            cosclient.shutdown();
            //返回文件的网络访问url
            return rtValue;
        }
    }

    private static JSONObject getTempKey() {
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        try {//使用永久密钥生成临时密钥
            config.put("SecretId", baseConfiguration.getSecretId());
            config.put("SecretKey", baseConfiguration.getSecretKey());
            config.put("durationSeconds", Integer.parseInt(baseConfiguration.getDurationSeconds()));
            config.put("bucket", baseConfiguration.getBucket());
            config.put("region", baseConfiguration.getRegion());
            config.put("allowPrefix", baseConfiguration.getAllowPrefix());

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

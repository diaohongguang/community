package com.diao.Provider;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.UUID;

@Component
public class TengXunUpload {
    @Value("${teng.xun.SecretId}")
    private String secretId;

    @Value("${teng.xun.SecretKey}")
    private String secretKey;

    @Value("${teng.xun.bucketName}")
    private String bucketName;
    public String uploadFile( String key, InputStream input,
                             String metadata) {
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region("ap-shanghai");
        ClientConfig clientConfig = new ClientConfig(region);

        COSClient cosClient = new COSClient(cred, clientConfig);
        ObjectMetadata objectMetadata= new ObjectMetadata();
        objectMetadata.setContentType(metadata);
        String[] split = key.split("\\.");
        String name = "";
        if (split.length>1){
            for (int i = 0 ; i< split.length-1 ; i ++){
                name+=split[i];
            }
            name+= UUID.randomUUID().toString()+"."+split[split.length-1];
        }
        PutObjectResult putObjectResult = cosClient.putObject(bucketName, "image/" + name, input, objectMetadata);
        return name;
    }
}

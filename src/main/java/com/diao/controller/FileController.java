package com.diao.controller;

import com.diao.Provider.TengXunUpload;
import com.diao.pojo.User;
import com.diao.pojo.dto.UploadDto;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class FileController {

    @Value("${teng.xun.SecretId}")
    private String secretId;

    @Value("${teng.xun.SecretKey}")
    private String secretKey;

    @Autowired
    private TengXunUpload upload;

    @Value("${teng.xun.imageUrl}")
    private String url;

    @ResponseBody
    @RequestMapping("/file/upload")
    public UploadDto upload(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return null;
        }
        UploadDto uploadDto = new UploadDto();
        if (request instanceof MultipartHttpServletRequest){
            MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
            MultipartFile file = req.getFile("editormd-image-file");
            try {
                System.out.println("这是fileContentType"+file.getContentType());
                String s = upload.uploadFile(file.getOriginalFilename(), file.getInputStream(), file.getContentType());
                uploadDto.setUrl(url+s);
                uploadDto.setSuccess(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        uploadDto.setMessage("演示上传");

        return uploadDto;
    }
}

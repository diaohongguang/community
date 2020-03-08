package com.diao.Provider;

import com.alibaba.fastjson.JSON;
import com.diao.pojo.dto.AccessTokenDto;
import com.diao.pojo.dto.GitHubUserDto;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitProvider {

    public String getAccessToken(AccessTokenDto accessTokenDto) {
        MediaType JSON
                = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        String json = com.alibaba.fastjson.JSON.toJSONString(accessTokenDto);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String[] split = string.split("&");
            String[] split1 = split[0].split("=");
            System.out.println(split1[1]);
            return split1[1];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public GitHubUserDto getUserName(String token) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+token)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            return  JSON.parseObject(string,GitHubUserDto.class);
        } catch (IOException e) {
            return null;

        }
    }
}

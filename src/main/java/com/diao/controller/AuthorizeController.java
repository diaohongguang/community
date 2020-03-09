package com.diao.controller;

import com.diao.Provider.GitProvider;
import com.diao.pojo.dto.AccessTokenDto;
import com.diao.pojo.dto.GitHubUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizeController {
    @Autowired
    GitProvider gitProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.ui}")
    private String redirectUI;
    @GetMapping("/callback")
    public String callback(@RequestParam("code")String code,
                           @RequestParam("state")String state,
                           HttpServletRequest request){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setRedirect_uri(redirectUI);
        GitHubUserDto user = gitProvider.getUserName(gitProvider.getAccessToken(accessTokenDto));
        System.out.println("user1->"+user);
        if(user != null){
            request.getSession().setAttribute("user",user);
            System.out.println("user->"+user);
            return "redirect:index";
        }
        return "index";
    }
}

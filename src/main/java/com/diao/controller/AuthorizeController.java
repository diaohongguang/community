package com.diao.controller;

import com.diao.Provider.GitProvider;
import com.diao.mapper.UserMapper;
import com.diao.pojo.User;
import com.diao.pojo.dto.AccessTokenDto;
import com.diao.pojo.dto.GitHubUserDto;
import com.diao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

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

    @Autowired
    UserService userService;

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
        GitHubUserDto gitUser = gitProvider.getUserName(gitProvider.getAccessToken(accessTokenDto));
        System.out.println("user1->"+gitUser);
        if(gitUser != null){
            User user = new User();
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setName(gitUser.getName());
            user.setAccountId(String.valueOf(gitUser.getId()));
            //令牌是uuid
            user.setToken(UUID.randomUUID().toString());
            userService.insertUser(user);
            request.getSession().setAttribute("user",gitUser);
            System.out.println("user->"+gitUser);
            return "redirect:/";
        }
        return "redirect:/";
    }
}
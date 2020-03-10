package com.diao.controller;

import com.diao.Provider.GitProvider;
import com.diao.pojo.User;
import com.diao.pojo.dto.AccessTokenDto;
import com.diao.pojo.dto.GitHubUserDto;
import com.diao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setRedirect_uri(redirectUI);
        GitHubUserDto gitUser = gitProvider.getUserName(gitProvider.getAccessToken(accessTokenDto));
        System.out.println("user1->" + gitUser);
        if (gitUser != null) {
            User user = new User();
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setName(gitUser.getName());
            user.setAvatarUrl(gitUser.getAvatarUrl());
            user.setAccountId(String.valueOf(gitUser.getId()));
            //令牌是uuid
            user.setToken(UUID.randomUUID().toString());
            response.addCookie(new Cookie("token", user.getToken()));
            userService.insertUser(user);
            request.getSession().setAttribute("user", gitUser);
            System.out.println("user->" + gitUser);
            return "redirect:/";
        }
        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/";
    }
}

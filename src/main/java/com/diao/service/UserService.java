package com.diao.service;

import com.diao.pojo.User;

public interface UserService {
    int insertUser(User user);
    User selectUserByToken(String token);
}

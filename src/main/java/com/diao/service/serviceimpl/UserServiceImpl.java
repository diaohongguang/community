package com.diao.service.serviceimpl;

import com.diao.mapper.UserMapper;
import com.diao.pojo.User;
import com.diao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public User selectUserByToken(String token) {
        return userMapper.selectUserByToken(token);
    }
}

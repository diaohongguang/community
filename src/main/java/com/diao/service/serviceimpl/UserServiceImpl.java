package com.diao.service.serviceimpl;

import com.diao.mapper.UserMapper;
import com.diao.pojo.User;
import com.diao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        user.setGmtCreate(System.currentTimeMillis());
        if (user!=null){
            if (userMapper.hasUser(user.getAccountId())==1){
                return userMapper.updateUserByAccountId(user);
            }
            return userMapper.insertUser(user);
        }
        return 0;
    }

    @Override
    public User selectUserByToken(String token) {
        return userMapper.selectUserByToken(token);
    }
}

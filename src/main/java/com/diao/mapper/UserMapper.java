package com.diao.mapper;

import com.diao.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    int getCount();

    int insertUser(User user);

    User selectUserByToken(@Param("token") String token);
}

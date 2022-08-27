package com.example.ontheway.dao;

import com.example.ontheway.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);
    //更改状态码
    int updateStatus(int id,int status);
    //更改图像
    int updateHeader(int id,String headerUrl);

    //更新密码
    int updatePassword(int id,String password);

}

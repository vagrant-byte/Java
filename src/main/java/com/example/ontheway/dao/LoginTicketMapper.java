package com.example.ontheway.dao;

import com.example.ontheway.entity.LoginTicket;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@Deprecated
public interface LoginTicketMapper {
    int insertLoginTicket(LoginTicket loginTicket);

    LoginTicket selectByTicket(String ticket);

    int updateStatus(String ticket,int status);
}

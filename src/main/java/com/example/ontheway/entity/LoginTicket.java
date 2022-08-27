package com.example.ontheway.entity;

import javax.xml.crypto.Data;
import java.util.Date;

@lombok.Data
public class LoginTicket {
    private int id;
    private int userId;
    private String ticket;
    private int status;
    private Date expired;
}

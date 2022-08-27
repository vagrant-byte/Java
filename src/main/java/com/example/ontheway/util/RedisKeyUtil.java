package com.example.ontheway.util;

import ch.qos.logback.classic.net.SimpleSSLSocketServer;

public class RedisKeyUtil {
    //所有key使用冒号进行拼接
    private static final String SPLIT=":";
    //实体赞的前缀
    private static final String PREFIX_ENTITY_LIKE="like:entity";
    //某个用户的赞的前缀
    private static final String PREFIX_USER_LIKE="like:user";
    //关注目标前缀
    private static final String PREFIX_FOLLOWEE="followee";
    //被关注者前缀
    private static final String PREFIX_FOLLOWER="follower";
    //验证码前缀
    private static final String PREFIX_KAPTCHA="kaptcha";
    //登录凭证
    private static final String PREFIX_TICKET="ticket";
    //用户凭证
    private static final String PREFIX_USER="user";
    //访客
    private static final String PREFIX_UV="uv";
    //日活跃用户
    private static final String PREFIX_DAU="dau";


    //某个实体的赞
    //like:entity:entityType:entityId->set(userId)
    public static String getEntityLikeKey(int entityType,int entityId) {
        return PREFIX_ENTITY_LIKE+SPLIT+entityType+SPLIT+entityId;
    }
    //某个用户的赞
    public static String getUserLikeKey(int userId) {
        return PREFIX_USER_LIKE+SPLIT+userId;
    }
    //某个用户关注的实体
    //followee:userId:entityType->Zset(entityId,now)
    public static String getFolloweeKey(int userId,int entityType) {
        return PREFIX_FOLLOWEE+SPLIT+userId+SPLIT+entityType;
    }
    //某个实体拥有的粉丝
    //follower:entityType:entityId->zset(userId,int entityId)
    public static String getFollowerKey(int entityType,int entityId) {
        return PREFIX_FOLLOWER+SPLIT+entityType+SPLIT+entityId;
    }

    //登录验证码
    public static String getKaptchaKey(String owner) {
        return PREFIX_KAPTCHA+SPLIT+owner;
    }

    //登录凭证
    public static String getTicketKay(String ticket) {
        return PREFIX_TICKET+SPLIT+ticket;
    }

    //用户
    public static String getUserKey(int userId) {
        return PREFIX_USER+SPLIT+userId;
    }

    //单日uv
    public static String getUVKey(String date) {
        return PREFIX_UV+SPLIT+date;
    }
    //区间UV
    public static String getUVKey(String startDate,String endDate) {
        return PREFIX_UV+SPLIT+startDate+SPLIT+endDate;
    }
    //单日活跃用户
    public static String getDAUKey(String date) {
        return PREFIX_DAU+SPLIT+date;
    }
    //区间活跃用户
    public static String getDAUKey(String startDate,String endDate) {
        return PREFIX_DAU+SPLIT+startDate+SPLIT+endDate;
    }
}

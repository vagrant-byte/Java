package com.example.ontheway.service;

import com.example.ontheway.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private RedisTemplate redisTemplate;
    //点赞
    public void like(int userId,int entityType,int entityId,int entityUserId) {
//        String entityLikeKey= RedisKeyUtil.getEntityLikeKey(entityType,entityId);
//        //判断是否以及点过赞 如果点过赞再点一次就是取消赞，没点过就是点赞，
//        //判断当前的userId是否在set集合中
//        boolean isMember=redisTemplate.opsForSet().isMember(entityLikeKey,userId);
//        if(isMember) {
//            //点过赞将其userId去除
//            redisTemplate.opsForSet().remove(entityLikeKey,userId);
//        }else {
//            //没点过赞，将其加入set
//            redisTemplate.opsForSet().add(entityLikeKey,userId);
//        }
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                String entityLikeKey= RedisKeyUtil.getEntityLikeKey(entityType,entityId);
                String userLikeKey=RedisKeyUtil.getUserLikeKey(entityUserId);
                boolean isMember=redisTemplate.opsForSet().isMember(entityLikeKey,userId);
                //开启事务
                operations.multi();
                if(isMember) {
                    operations.opsForSet().isMember(entityLikeKey,userId);
                    operations.opsForValue().decrement(userLikeKey);
                }else {
                    operations.opsForSet().add(entityLikeKey,userId);
                    operations.opsForValue().increment(userLikeKey);
                }
                return operations.exec();
            }
        });
    }
    //查询实体点赞的数量
    public long findEntityLikeCount(int entityType,int entityId) {
        String entityLikeKey= RedisKeyUtil.getEntityLikeKey(entityType,entityId);
        return redisTemplate.opsForSet().size(entityLikeKey);
    }
    //查询某人对某实体的点赞状态
    public int findEntityLikeStatus(int userId,int entityType,int entityId){
        String entityLikeKey= RedisKeyUtil.getEntityLikeKey(entityType,entityId);
        return redisTemplate.opsForSet().isMember(entityLikeKey,userId)?1:0;
    }
    //查询某个用户获得的赞
    public int findUserLikeCount(int userId) {
        String userLikeKey=RedisKeyUtil.getUserLikeKey(userId);
        Integer count= (Integer) redisTemplate.opsForValue().get(userLikeKey);
        return count==null?0:count;
    }
}

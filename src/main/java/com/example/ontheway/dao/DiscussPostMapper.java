package com.example.ontheway.dao;

import com.example.ontheway.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
//    分页显示所有的帖子
//    传入offset和limit参数进行sql的limit分页操作
//    传入的userId 用于后面实现查找所有自己发布帖子的操作
    List<DiscussPost> selectDiscussPost(int userId,int offset,int limit);

//    查询帖子的行数
//    @Param用于给参数取别名，如果只有一个参数，并且在<if>里使用就必须加别名
    int selectDiscussPostRows(@Param(("userId")) int userId);

    //新增帖子
    int insertDiscussPost(DiscussPost discussPost);

    //查询帖子详情
    DiscussPost selectDiscussPostById(int id);

    //更新帖子评论数量
    int updateCommentCount(int id,int commentCount);

    //修改帖子类型
    int updateType(int id,int type);

    //修改帖子状态
    int updateStatus(int id,int status);

}

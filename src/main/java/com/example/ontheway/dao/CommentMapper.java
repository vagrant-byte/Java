package com.example.ontheway.dao;

import com.example.ontheway.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    //查找所有评论
    List<Comment> selectCommentEntity(int entityType,int entityId,int offset,int limit);
    //查询评论数量
    int selectCountByEntity(int entityType,int entityId);
    //新增评论
    int insertComment(Comment comment);
}

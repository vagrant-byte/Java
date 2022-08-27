package com.example.ontheway.service;

import com.example.ontheway.dao.CommentMapper;
import com.example.ontheway.entity.Comment;
import com.example.ontheway.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService implements CommunityConstant {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private DiscussPostService discussPostService;
    //查找所有评论
    public List<Comment> selectCommentEntity(int entityType,int entityId,int offset,int limit) {
        return commentMapper.selectCommentEntity(entityType,entityId,offset,limit);
    }
    //查询评论数量
    public int selectCountByEntity(int entityType,int entityId){
        return commentMapper.selectCountByEntity(entityType,entityId);
    }
    //新增评论
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int insertComment(Comment comment) throws IllegalAccessException {
        if(comment==null) {
            throw new IllegalAccessException("参数不能为空");
        }
        //添加评论
        int row=commentMapper.insertComment(comment);
        //更新帖子评论数量 这里使用事务进行管理，即添加评论和更新帖子评论数量同时完成
        if (comment.getEntityType()==ENTITY_TYPE_POST) {
            int count=commentMapper.selectCountByEntity(comment.getEntityType(),comment.getEntityId());
            discussPostService.updateCommentCount(comment.getEntityId(),count);
        }
        return row;
    }
}

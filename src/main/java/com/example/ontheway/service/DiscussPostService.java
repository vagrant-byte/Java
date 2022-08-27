package com.example.ontheway.service;

import com.example.ontheway.dao.DiscussPostMapper;
import com.example.ontheway.dao.UserMapper;
import com.example.ontheway.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
@Service
public class DiscussPostService {
    @Autowired
    private DiscussPostMapper discussPostMapper;

    public List<DiscussPost> findDiscussPost(Integer userId,Integer offset,Integer limit) {
        return discussPostMapper.selectDiscussPost(userId,offset,limit);
    }

    public int findDiscussPostRows(Integer userId) {
        return discussPostMapper.selectDiscussPostRows(userId);
    }
    //新增帖子
    public int addDiscussPost(DiscussPost post) throws IllegalAccessException {
        if(post==null) {
            throw new IllegalAccessException("参数不合法");
        }
        //转义HTML标记
        post.setTitle(HtmlUtils.htmlEscape(post.getTitle()));
        post.setContent(HtmlUtils.htmlEscape(post.getContent()));
        return discussPostMapper.insertDiscussPost(post);
    }
    //查询帖子
    public DiscussPost findDiscussPostById(int id) {
        return discussPostMapper.selectDiscussPostById(id);
    }
    //更改帖子评论数
    public int updateCommentCount(int id,int commentCount) {
        return discussPostMapper.updateCommentCount(id,commentCount);
    }
    //更改帖子类型
    public int updateType(int id,int type) {
        return discussPostMapper.updateType(id, type);
    }
    //更改帖子状态
    public int updateStatus(int id,int status) {
        return discussPostMapper.updateStatus(id,status);
    }
}

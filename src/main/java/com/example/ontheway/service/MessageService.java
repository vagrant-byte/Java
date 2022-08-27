package com.example.ontheway.service;

import com.example.ontheway.dao.MessageMapper;
import com.example.ontheway.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageMapper messageMapper;

    //查询当前会话列表，针对每一个会话只返回一条最新的私信
    public List<Message> selectConversations(int userId, int offset, int limit){
        return messageMapper.selectConversations(userId,offset,limit);
    }

    //查询当前用户的会话数量
    public int selectConversationCount(int userId){
        return messageMapper.selectConversationCount(userId);
    }

    //查询某个会话所包含的私信列表
    public List<Message> selectLetters(String conversationId,int offset,int limit){
        return messageMapper.selectLetters(conversationId,offset,limit);
    }

    //查询某个会话所包含的私信数量
    public int selectLetterCount(String conversationId){
        return messageMapper.selectLetterCount(conversationId);
    }

    //查询未读私信的数量
    public int selectLetterUnreadCount(int userId,String conversationId){
        return messageMapper.selectLetterUnreadCount(userId,conversationId);
    }

    //新增私信消息
    public int addMessage(Message message) {
        return messageMapper.insertMessage(message);
    }

    //修改消息的状态
    public int readMessage(List<Integer> ids){
        return messageMapper.updateStatus(ids,1);
    }

}

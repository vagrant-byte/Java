package com.example.ontheway.controller;

import com.example.ontheway.entity.Message;
import com.example.ontheway.entity.Page;
import com.example.ontheway.entity.User;
import com.example.ontheway.service.MessageService;
import com.example.ontheway.service.UserService;
import com.example.ontheway.util.CommunityUtil;
import com.example.ontheway.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private UserService userService;
    //处理私信列表
    @RequestMapping(path = "/letter/list",method = RequestMethod.GET)
    public String getLetterList(Model model, Page page) {
        User user=hostHolder.getUser();
        //分页信息
        page.setLimit(5);
        page.setPath("/letter/list");
        page.setRows(messageService.selectConversationCount(user.getId()));
        //会话列表
        List<Message> conversationList = messageService.selectConversations(
                user.getId(), page.getOffset(),page.getLimit());
        //使用map对会话列表中的信息进行存储，如 未读信息数，头像，信息总数等
        List<Map<String,Object>> conversations=new ArrayList<>();
        if(conversationList!=null) {
            for (Message message:conversationList) {
                Map<String,Object> map=new HashMap<>();
                map.put("conversation",message);
                //私信数量
                map.put("letterCount",messageService.selectLetterCount(message.getConversationId()));
                //未读私信数量
                map.put("unreadCount",messageService.selectLetterUnreadCount(user.getId(),message.getConversationId()));
                //图像
                int targetId=user.getId()==message.getFromId()?message.getToId():message.getFromId();
                map.put("target",userService.findById(targetId));
                conversations.add(map);
            }
        }
        model.addAttribute("conversations",conversations);
        //查询未读消息数量
        int letterUnreadCount=messageService.selectLetterUnreadCount(user.getId(),null);
        model.addAttribute("letterUnreadCount",letterUnreadCount);
        return "/site/letter";
    }
    //私信详情
    @RequestMapping(path = "/letter/detail/{conversationId}",method = RequestMethod.GET)
    public String getLetterDetail(@PathVariable("conversationId") String conversationId,Page page,Model model) {
        //设置分页
        page.setLimit(5);
        page.setPath("/letter/detail/"+conversationId);
        page.setRows(messageService.selectLetterCount(conversationId));
        //私信列表
        List<Message> letterList=messageService.selectLetters(conversationId,page.getOffset(),page.getLimit());
        List<Map<String,Object>> letters=new ArrayList<>();
        if(letterList!=null) {
            for (Message message:letterList) {
                Map<String,Object> map=new HashMap<>();
                map.put("letter",message);
                map.put("fromUser",userService.findById(message.getFromId()));
                letters.add(map);
            }
        }
        model.addAttribute("letters",letters);
        model.addAttribute("target",getLetterTarget(conversationId));
        //设置已读
        List<Integer> ids=getLetterIds(letterList);
        if(!ids.isEmpty()) {
            messageService.readMessage(ids);
        }
        return "/site/letter-detail";
    }

    private User getLetterTarget(String conversationId) {
        //获取当前是谁发送的私信
        String[] ids=conversationId.split("_");
        int id0=Integer.parseInt(ids[0]);
        int id1=Integer.parseInt(ids[1]);
        if(hostHolder.getUser().getId()==id0) {
            return userService.findById(id1);
        } else {
            return userService.findById(id0);
        }
    }

    public List<Integer> getLetterIds(List<Message> letterList) {
        //获取未读消息集合
        List<Integer> ids=new ArrayList<>();
        if(letterList!=null) {
            for (Message message:letterList) {
                if(message.getToId()==hostHolder.getUser().getId()
                &&message.getStatus()==0) {
                    ids.add(message.getId());
                }
            }
        }
        return ids;
    }

    //新增消息
    @RequestMapping(path = "/letter/send",method = RequestMethod.POST)
    @ResponseBody
    public String sendLetter(String toName, String content) {
        User target=userService.findByName(toName);
        if(target==null) {
            return CommunityUtil.getJSONString(1,"目标用户不存在");
        }
        Message message=new Message();
        message.setFromId(hostHolder.getUser().getId());
        message.setToId(target.getId());
        if(message.getFromId()<message.getToId()) {
            message.setConversationId(message.getFromId()+"_"+message.getToId());
        }else {
            message.setConversationId(message.getToId()+"_"+message.getFromId());
        }
        message.setContent(content);
        message.setCreateTime(new Date());
        messageService.addMessage(message);
        return CommunityUtil.getJSONString(0);
    }

}

package com.example.ontheway.controller;

import com.example.ontheway.dao.DiscussPostMapper;
import com.example.ontheway.entity.Comment;
import com.example.ontheway.entity.DiscussPost;
import com.example.ontheway.entity.Page;
import com.example.ontheway.entity.User;
import com.example.ontheway.service.*;
import com.example.ontheway.util.CommunityConstant;
import com.example.ontheway.util.CommunityUtil;
import com.example.ontheway.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.List;

@Controller
public class DiscussPostController implements CommunityConstant {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private ElasticsearchService elasticsearchService;

    // ajax示例
    @RequestMapping(path = "/ajax", method = RequestMethod.POST)
    @ResponseBody
    public String testAjax(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return CommunityUtil.getJSONString(0, "操作成功!");
    }
    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String root() {
        return "forward:/index";
    }

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page) {
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");
//        显示帖子前10条数据
        List<DiscussPost> list=discussPostService.findDiscussPost(0,page.getOffset(),page.getLimit());
//        此时list中显示了帖子的内容和用户的Id（userId），但是不知道用户的具体信息
        List<Map<String,Object>> discussPosts=new ArrayList<>();
        if(list!=null) {
            for (DiscussPost post:list) {
                HashMap<String,Object> map=new HashMap<>();
                map.put("post",post);
                User user=userService.findById(post.getUserId());
                map.put("user",user);
                //查询帖子赞数量
                long likeCount=likeService.findEntityLikeCount(ENTITY_TYPE_POST,post.getId());
                map.put("likeCount",likeCount);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        return "index";
    }

    //新增帖子
    @RequestMapping(path = "/discuss/add",method = RequestMethod.POST)
    @ResponseBody
    public String addDiscussPost(String title,String content) throws IllegalAccessException {
        User user=hostHolder.getUser();
        if(user==null) {
            return CommunityUtil.getJSONString(403,"你还没有登录");
        }
        DiscussPost post=new DiscussPost();
        post.setUserId(user.getId());
        post.setTitle(title);
        post.setContent(content);
        post.setCreateTime(new Date());
        discussPostService.addDiscussPost(post);
        // 触发发帖事件，将帖子存在Elasticsearch中
        elasticsearchService.saveDiscussPost(post);
        return CommunityUtil.getJSONString(0,"发布成功");
    }

    //显示帖子
    @RequestMapping(path = "/discuss/detail/{discussPostId}",method = RequestMethod.GET)
    public String getDiscussPost(@PathVariable("discussPostId") int discussPostId,Model model,Page page) {
        //帖子
        DiscussPost Post=discussPostService.findDiscussPostById(discussPostId);
        model.addAttribute("post",Post);
        //作者
        User user=userService.findById(Post.getUserId());
        model.addAttribute("user",user);
        //点赞
        long likeCount=likeService.findEntityLikeCount(ENTITY_TYPE_POST,discussPostId);
        model.addAttribute("likeCount",likeCount);
        //点赞状态
        int likeStatus=hostHolder.getUser()==null?0:likeService.findEntityLikeStatus(hostHolder.getUser().getId(),ENTITY_TYPE_POST,discussPostId);
        model.addAttribute("likeStatus",likeStatus);
        //评论:给帖子评论
        //回复:给评论回复
        //评论列表
        List<Comment> commentList=commentService.selectCommentEntity(ENTITY_TYPE_POST,Post.getId(),page.getOffset(),page.getLimit());
        List<Map<String,Object>> commentVoList=new ArrayList<>();
        if(commentList!=null) {
            for (Comment comment:commentList) {
                //评论
                Map<String,Object> commentVo=new HashMap<>();
                //评论
                commentVo.put("comment",comment);
                //评论的作者
                commentVo.put("user",userService.findById(comment.getUserId()));
                //点赞
                likeCount=likeService.findEntityLikeCount(ENTITY_TYPE_COMMENT,comment.getId());
                commentVo.put("likeCount",likeCount);
                //点赞状态
                likeStatus=hostHolder.getUser()==null?0:likeService.findEntityLikeStatus(hostHolder.getUser().getId(),ENTITY_TYPE_COMMENT,comment.getId());
                commentVo.put("likeStatus",likeStatus);
                //回复列表
                List<Comment> replyList=commentService.selectCommentEntity(ENTITY_TYPE_COMMENT,
                        comment.getId(),0,Integer.MAX_VALUE);
                List<Map<String,Object>> replyVoList=new ArrayList<>();
                if(replyList!=null) {
                    for (Comment reply:replyList) {
                        Map<String,Object> replyVo=new HashMap<>();
                        //回复
                        replyVo.put("reply",reply);
                        //作者
                        replyVo.put("user",userService.findById(reply.getUserId()));
                        //回复目标
                        User target=reply.getTargetId()==0?null:userService.findById(reply.getUserId());
                        replyVo.put("target",target);
                        //点赞
                        likeCount=likeService.findEntityLikeCount(ENTITY_TYPE_COMMENT,reply.getId());
                        replyVo.put("likeCount",likeCount);
                        //点赞状态
                        likeStatus=hostHolder.getUser()==null?0:likeService.findEntityLikeStatus(hostHolder.getUser().getId(),ENTITY_TYPE_COMMENT,reply.getId());
                        replyVo.put("likeStatus",likeStatus);
                        replyVoList.add(replyVo);
                    }
                }
                //评论中的回复
                commentVo.put("replys",replyVoList);
                //回复的数量
                int replyCount=commentService.selectCountByEntity(ENTITY_TYPE_COMMENT,comment.getId());
                commentVo.put("replyCount",replyCount);
                commentVoList.add(commentVo);
            }
        }
        model.addAttribute("comments",commentVoList);
        return "/site/discuss-detail";
    }

    //置顶
    @RequestMapping(path = "/discuss/top",method = RequestMethod.POST)
    @ResponseBody
    public String setTop(int id) {
        discussPostService.updateType(id,1);
        elasticsearchService.saveDiscussPost(discussPostService.findDiscussPostById(id));
        return CommunityUtil.getJSONString(0);
    }
    //加精
    @RequestMapping(path = "/discuss/wonderful",method = RequestMethod.POST)
    @ResponseBody
    public String setWonderful(int id) {
        discussPostService.updateStatus(id,1);
        elasticsearchService.saveDiscussPost(discussPostService.findDiscussPostById(id));
        return CommunityUtil.getJSONString(0);
    }
    //删除
    @RequestMapping(path = "/discuss/delete",method = RequestMethod.POST)
    @ResponseBody
    public String setDelete(int id) {
        discussPostService.updateStatus(id,2);
        elasticsearchService.deleteDiscussPost(id);
        return CommunityUtil.getJSONString(0);
    }

}

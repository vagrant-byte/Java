package com.example.ontheway.controller;

import com.example.ontheway.entity.DiscussPost;
import com.example.ontheway.entity.Page;
import com.example.ontheway.entity.User;
import com.example.ontheway.service.ElasticsearchService;
import com.example.ontheway.service.LikeService;
import com.example.ontheway.service.UserService;
import com.example.ontheway.util.CommunityConstant;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController implements CommunityConstant {
    //搜素后展示
    @Autowired
    private ElasticsearchService elasticsearchService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    //search?keyword=xxx
    @RequestMapping(path = "/search",method = RequestMethod.GET)
    public String search(String keyword, Page page, Model model) {
        //搜素帖子
        org.springframework.data.domain.Page<DiscussPost> searchResult=
        elasticsearchService.searchDiscussPost(keyword,page.getCurrent()-1,page.getLimit());
        //聚合数据
        List<Map<String,Object>> discussPosts=new ArrayList<>();
        if(searchResult!=null) {
            for (DiscussPost post:searchResult) {
                Map<String,Object> map=new HashMap<>();
                //帖子
                map.put("post",post);
                //作者
                map.put("user",userService.findById(post.getUserId()));
                //点赞数量
                map.put("likeCount",likeService.findEntityLikeCount(ENTITY_TYPE_POST,post.getId()));
                discussPosts.add(map);
            }

        }
        model.addAttribute("discussPosts",discussPosts);
        model.addAttribute("keyword",keyword);

        //分页信息
        page.setPath("/search?keyword="+keyword);
        page.setRows(searchResult==null?0: (int) searchResult.getTotalElements());
        return "/site/search";
    }







}

package com.example.ontheway.controller;

import com.example.ontheway.dao.UserMapper;
import com.example.ontheway.entity.User;
import com.example.ontheway.service.FollowService;
import com.example.ontheway.service.LikeService;
import com.example.ontheway.service.UserService;
import com.example.ontheway.util.CommunityConstant;
import com.example.ontheway.util.CommunityUtil;
import com.example.ontheway.util.HostHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@Controller
//@RestController
@RequestMapping("/user")
public class UserController implements CommunityConstant {
    private static final Logger logger= LoggerFactory.getLogger(UserController.class);
    //上传路径
    @Value("${community.path.upload}")
    private String uploadPath;
    //域名
    @Value("${community.path.domain}")
    private String domain;
    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private LikeService likeService;

    @Autowired
    private FollowService followService;

    public User findById(Integer id) {
        if(id==null) {
            System.out.println("用户不存在");
        }
        return userService.findById(id);
    }
    //获取修改页面
    @RequestMapping(path = "/setting",method = RequestMethod.GET)
    public String getSettingPage() {
        return "/site/setting";
    }
    //更改图像
    @RequestMapping(path = "/upload",method = RequestMethod.POST)
    public String uploadHeader(MultipartFile headerImage, Model model) {
        if(headerImage==null) {
            model.addAttribute("error","你还没有选择图片");
            return "/site/setting";
        }
        //上传文件
        //获取文件名
        String fileName=headerImage.getOriginalFilename();
        String suffix=fileName.substring(fileName.lastIndexOf("."));
        if(StringUtils.isBlank(suffix)) {
            model.addAttribute("error","文件的格式不正确");
            return "/site/setting";
        }
        //生成随机文件名
        fileName=CommunityUtil.generateUUID()+suffix;
        //确定文件存放的路径
        File dest=new File(uploadPath+"/"+fileName);
        //将图片内容写入文件
        try {
            headerImage.transferTo(dest);
        } catch (IOException e) {
            logger.error("上传文件失败"+e.getMessage());
            throw new RuntimeException("上传文件失败，服务器发送异常",e);
        }
        //更新当前用户的头像路径（web访问路径)
        //http:/localhost:8080/onetheway/user.header/xxx.png
        User user=hostHolder.getUser();
        String headerUrl=domain+contextPath+"/user/header/"+fileName;
        userService.updateHeader(user.getId(),headerUrl);
        return "redirect:/index";
    }
    //获取头像
    @RequestMapping(path = "/header/{fileName}",method = RequestMethod.GET)
    public void getHeader(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        //服务器存放路径
        fileName=uploadPath+"/"+fileName;
        //文件后缀
        String suffix=fileName.substring(fileName.lastIndexOf("."));
        //相应图片
        try(FileInputStream fileInputStream=new FileInputStream(fileName);
                OutputStream os=response.getOutputStream()) {
            byte[] bytes=new byte[1024];
            int b=0;
            while ((b=fileInputStream.read(bytes))!=-1) {
                os.write(bytes,0,b);
            }

        } catch (IOException e) {
            logger.error("读取图像失败",e.getMessage());
        }
    }
    //修改密码
    @RequestMapping(path = "/updatePassword",method = RequestMethod.POST)
    public String updatePassword(String username,String email,String newPassword,Model model) {
        Map<String,Object> map=userService.updatePassword(username,email,newPassword);
        if(map==null||map.isEmpty()) {
            return "redirect:/login";
        }else {
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            model.addAttribute("emailMsg",map.get("emailMsg"));
            model.addAttribute("newPasswordMsg",map.get("newPasswordMsg"));
            return "/site/setting";
        }
    }
    // 个人主页
    @RequestMapping(path = "/profile/{userId}",method = RequestMethod.GET)
    public String getProfilePage(@PathVariable("userId") int userId,Model model) {
        User user=userService.findById(userId);
        if(user==null) {
            throw new RuntimeException("用户不存在");
        }
        //用户
        model.addAttribute("user",user);
        //点赞数量
        int likeCount=likeService.findUserLikeCount(userId);
        model.addAttribute("likeCount",likeCount);
        //关注数量
        long followeeCount=followService.findFolloweeCount(userId,ENTITY_TYPE_USER);
        model.addAttribute("followeeCount",followeeCount);
        //粉丝数量
        long followerCount=followService.findFollowerCount(ENTITY_TYPE_USER,userId);
        model.addAttribute("followerCount",followerCount);
        //是否已关注
        boolean hasFollowed=false;
        if(hostHolder.getUser()!=null) {
            hasFollowed=followService.hasFollowed(hostHolder.getUser().getId(),ENTITY_TYPE_USER,userId);
        }
        model.addAttribute("hasFollowed",hasFollowed);
        return "/site/profile";
    }







}

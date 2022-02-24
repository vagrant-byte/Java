package dao;

import com.mysql.cj.util.DnsSrv;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogDao {
    //1.插入博客
    public  void insert(Blog blog) {
        System.out.println("插入一个博客");
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            //1.和数据库建立连接
            connection=DBUtil.getConnection();
            //2.拼装sql语句
            String sql="insert into blog values(null,?,?,?,?)";
            statement=connection.prepareStatement(sql);
            statement.setString(1,blog.getTitle());
            statement.setString(2,blog.getContent());
            statement.setTimestamp(3,blog.getPostTime());
            statement.setInt(4,blog.getUserId());
            //3.执行sql
            int ret=statement.executeUpdate();
            if(ret==1) {
                System.out.println("插入博客成功");
            } else {
                System.out.println("插入博客失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBUtil.close(connection,statement,null);
        }
    }
    //2.获取所有博客
    public List<Blog> selectAll() {
        System.out.println("获取所有博客");
        List<Blog> list=new ArrayList<Blog>();
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            //1.建立连接
            connection=DBUtil.getConnection();
            //2.拼装sql
            String sql="select * from blog order by postTime desc";
            statement=connection.prepareStatement(sql);
            //执行sql
            resultSet=statement.executeQuery();
            //4.遍历结果
            while (resultSet.next()) {
                Blog blog=new Blog();
                blog.setBlogId(resultSet.getInt("blogId"));
                blog.setTitle(resultSet.getString("title"));
                String content=resultSet.getString("content");
                if(content.length()>130) {
                    blog.setContent(content.substring(0,130)+"....");
                }
                blog.setContent(content);
                blog.setPostTime(resultSet.getTimestamp("postTime"));
                blog.setUserId(resultSet.getInt("userId"));
                list.add(blog);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }
    //3.查找blogId查找指定博客
    public Blog selectOne(int blogId) {
        System.out.println("查找指定博客");
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            connection=DBUtil.getConnection();
            String sql="select * from blog where blogId=?";
            statement=connection.prepareStatement(sql);
            statement.setInt(1,blogId);
            resultSet=statement.executeQuery();
            if(resultSet.next()) {
                Blog blog=new Blog();
                blog.setBlogId(resultSet.getInt("blogId"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setPostTime(resultSet.getTimestamp("postTime"));
                blog.setUserId(resultSet.getInt("userId"));
                return blog;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }
    //4.根据blogId删除指定博客
    public void delete(int blogId) {
        System.out.println("删除指定博客");
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            connection=DBUtil.getConnection();
            String sql="delete from blog where blogId=?";
            statement=connection.prepareStatement(sql);
            statement.setInt(1,blogId);
            int ret=statement.executeUpdate();
            if(ret==1) {
                System.out.println("删除博客成功");
            } else {
                System.out.println("删除博客失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }
    public static void testInsert() {
        BlogDao blogDao=new BlogDao();
        Blog blog=new Blog();
        blog.setTitle("我的第一篇博客");
        blog.setContent("光阴里的故事光阴里的故事光阴里的故事光阴里的故事光阴里的故事光阴里的故事光阴里的故事光阴里的故事");
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
        blog.setUserId(1);
        blogDao.insert(blog);
    }
    public static void testDelete() {
        BlogDao blogDao=new BlogDao();
        blogDao.delete(1);
    }
    public static void testSelectAll() {
        BlogDao blogDao=new BlogDao();
        List<Blog> list=blogDao.selectAll();
        System.out.println(list);
    }
    public static void testSelectOne() {
        BlogDao blogDao=new BlogDao();
        Blog blog=blogDao.selectOne(1);
        System.out.println(blog);
    }

    public static void main(String[] args) {
//        BlogDao blogDao=new BlogDao();
//        Blog blog=new Blog();
//        blog.setTitle("我的第一篇博客");
//        blog.setContent("从今天开始我要好好写博客");
//        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
//        blog.setUserId(2);
//        blogDao.insert(blog);
//        List<Blog> list=blogDao.selectAll();
//        System.out.println(list);
//        Blog blog=blogDao.selectOne(1);
//        System.out.println(blog);
    }
}

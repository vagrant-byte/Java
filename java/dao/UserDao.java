package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    //1.实现新增用户
    public void insert(User user) {
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            //数据库连接
            connection=DBUtil.getConnection();
            //编辑sql语句
            String sql="insert into user values(null,?,?)";
            //sql语句拼装
            statement=connection.prepareStatement(sql);
            statement.setString(1,user.getUsername());
            statement.setString(2,user.getPassword());
            int ret=statement.executeUpdate();
            if(ret==1) {
                System.out.println("插入用户成功");
            } else {
                System.out.println("插入用户失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }
    //2.根据username查找用户
    public User select(String username) {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            connection=DBUtil.getConnection();
            String sql="select * from user where username=?";
            statement=connection.prepareStatement(sql);
            statement.setString(1,username);
            resultSet=statement.executeQuery();
            if(resultSet.next()) {
                User user=new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }
    //3.根据userId查找用户对象
    public User selectById(int userId) {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            connection=DBUtil.getConnection();
            String sql="select * from user where userId=?";
            statement=connection.prepareStatement(sql);
            statement.setInt(1,userId);
            resultSet=statement.executeQuery();
            if(resultSet.next()) {
                User user=new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }
    public static void testInsert() {
        UserDao userDao=new UserDao();
        User user=new User();
        user.setUsername("zhangsan");
        user.setPassword("1234");
        userDao.insert(user);
    }
    public static void testSelect() {
        UserDao userDao=new UserDao();
        User user=userDao.select("zhangsan");
        System.out.println(user);
    }
    public static void testSelectById() {
        UserDao userDao=new UserDao();
        User user=userDao.selectById(1);
        System.out.println(user);
    }

    public static void main(String[] args) {
        testInsert();
//        UserDao userDao=new UserDao();
//        User user=new User();
//        user.setUsername("mouyulou");
//        user.setPassword("1234");
//        userDao.insert(user);
//        User user=userDao.select("admin");
//        System.out.println(user);
//        User user=userDao.selectById(1);
//        System.out.println(user);
    }
}

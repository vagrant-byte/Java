package dao;

import common.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class UserDao {
    //新增用户
    public void insert(User user) {
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            connection= DBUtil.getConnection();
            String sql="insert into user values(null,?,?)";
            statement=connection.prepareStatement(sql);
            statement.setString(1,user.getUsername());
            statement.setString(2,user.getPassword());
            int ret=statement.executeUpdate();
            if(ret!=1) {
                System.out.println("新增用户失败");
            } else {
                System.out.println("新增用户成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }
    //删除用户
    public void delete(int userId) {
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            connection=DBUtil.getConnection();
            String sql="delete  from user where userId=?";
            statement=connection.prepareStatement(sql);
            statement.setInt(1,userId);
            int ret=statement.executeUpdate();
            if(ret!=1) {
                System.out.println("删除用户失败");
            } else {
                System.out.println("删除用户成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }
    //根据用户名更改密码
    public void changePassword(String username,String password) {
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            connection=DBUtil.getConnection();
            String sql="update user set password=? where username=?";
            statement=connection.prepareStatement(sql);
            statement.setString(1,password);
            statement.setString(2,username);
            int ret=statement.executeUpdate();
            if(ret!=1) {
                System.out.println("更改密码失败");
            } else {
                System.out.println("更改密码成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }
    //根据用户名查询用户
    public User select(String username) {
        User user=new User();
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
    //根据用户id查询用户
    public User selectId(int userId) {
        User user=new User();
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

    private static void testInsert() {
        UserDao userDao=new UserDao();
        User user=new User();
        user.setUsername("zhangsan");
        user.setPassword("1234");
        userDao.insert(user);
    }
    private static void testDelete(int id) {
        UserDao userDao=new UserDao();
        userDao.delete(id);
    }
    private static void testSelect(String name) {
        UserDao userDao=new UserDao();
        User user=userDao.select(name);
        System.out.println(user);
    }
    private static void testSelectId(int Id) {
        UserDao userDao=new UserDao();
        User user=userDao.selectId(Id);
        System.out.println(user);
    }

    public static void main(String[] args) {
        UserDao userDao=new UserDao();
        User user=new User();
//        user.setUsername("zhangsan");
//        user.setPassword("1234");
//        userDao.insert(user);
//        userDao.delete(3);
//        userDao.changePassword("zhangsan","5678");
        user=userDao.selectId(6);
        System.out.println(user);
    }
}

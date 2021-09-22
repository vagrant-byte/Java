package jdbc;

import com.mysql.cj.MysqlConnection;
import com.mysql.cj.jdbc.ha.MultiHostMySQLConnection;
import jdbc.operation.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//用来访问Book数据的对象
public class BookDao {
    //1.新增书籍（插入）
    public boolean add(Book book) {
        //把book对象插入到数据库的Book表中
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            //1.和数据库建立连接
            connection= DBUtil.getConnection();
            //2.拼装SQL语句
            String sql="insert into book values(null,?,?,?,?,?)";
            statement=connection.prepareStatement(sql);
            statement.setString(1,book.getName());
            statement.setString(2,book.getAuthor());
            statement.setInt(3,book.getPrice());
            statement.setString(4,book.getType());
            statement.setInt(5,book.isBorrow() ? 1:0);
            //执行SQL
            int ret=statement.executeUpdate();
            if(ret!=1) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
        return false;
    }
    //2.查看所有书籍（查找)
    public void search() {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            //1.和数据库建立连接
            connection=DBUtil.getConnection();
            //2.拼装SQL语句
            String sql="select * from book";
            statement=connection.prepareStatement(sql);
            //3.执行SQL语句
            resultSet=statement.executeQuery();
            //4.遍历结果集
            while (resultSet.next()) {
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String author=resultSet.getString("author");
                int price=resultSet.getInt("price");
                String type=resultSet.getString("type");
                int isBorrow=resultSet.getInt("isBorrow");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
    }
    //3.根据书名找书籍
    public int find(Book book) {
        //查找book书籍
        Connection connection=null;
        PreparedStatement statement=null;
        int ret=0;
        try {
            //与数据库建立连接
            connection=DBUtil.getConnection();
            //拼装SQL语句
            String sql="select * from book where name=book.name";
            //执行SQL语句
            ret=statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
        return ret;
    }
    //4.删除书籍
    public int del(Book book) {
        Connection connection=null;
        PreparedStatement statement=null;
        int ret=0;
        try {
            connection=DBUtil.getConnection();
            String sql="delete from book where name=book.name ";
            ret=statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
        return ret;
    }
    //5.借书
    //6.还书
    public int Return () {
        Connection connection=null;
        PreparedStatement statement=null;
        int ret=0;
        try {
            connection=DBUtil.getConnection();
            String name="围城";
            String author="钱钟书";
            int price=35;
            String type="文学小说";
            int isBorrow=1;
            String sql="insert into book values(null,?,?,?,?,?)";
            statement.setString(2,name);
            statement.setString(3,author);
            statement.setInt(4,price);
            statement.setString(5,type);
            statement.setInt(6,isBorrow);
            ret=statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
        return ret;
    }

}

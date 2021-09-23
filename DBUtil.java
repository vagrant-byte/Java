package jdbc;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.xdevapi.SqlDataResult;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//使用这个类，来封装建立数据库建立连接
public class DBUtil {
    private static final String URL="jdbc:mysql:///java0921?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC&useSSL=false";
    private static final String USERNAME="root";
    private static final String PASSWORD="1234";
//饿汉模式
   // private static DataSource dataSource=new MysqlDataSource();
    //使用一个构造方法，来针对DataSource 进行设置属性 不可行
    //静态代码块
    //static {
      //  ((MysqlDataSource)dataSource).setUrl(URL);
      //    ((MysqlDataSource)dataSource).setUser(USERNAME);
      //  ((MysqlDataSource)dataSource).setPassword(PASSWORD);
    //}
    //懒汉模式
    //首次调用getDataSource 才会被实例化
    private static DataSource dataSource=null;
    private static DataSource getDataSource() {
        if(dataSource==null) {
            dataSource=new MysqlDataSource();
            ((MysqlDataSource)dataSource).setUrl(URL);
            ((MysqlDataSource)dataSource).setUser(USERNAME);
            ((MysqlDataSource)dataSource).setPassword(PASSWORD);
        }
        return dataSource;
    }

    //提供一个方法来连接
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    //释放资源
    public static  void close(Connection connection, PreparedStatement statement, ResultSet
                              resultSet) {
        if(resultSet!=null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement!=null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }






}

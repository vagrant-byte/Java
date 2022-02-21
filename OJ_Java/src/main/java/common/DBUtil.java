package common;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.sun.org.apache.xalan.internal.xslt.Process;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
    private static final String url="jdbc:mysql:///oj_databases?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC&useSSL=false";
    private static final String USERNAME="root";
    private static final String PASSWORD="1234";

    private static volatile DataSource dataSource=null;
    //实现getDataSource，线程安全单例模式
    public static DataSource getDataSource() {
        if(dataSource==null) {
            synchronized (DBUtil.class) {
                if(dataSource==null) {
                    dataSource=new MysqlDataSource();
                    ((MysqlDataSource)dataSource).setURL(url);
                    ((MysqlDataSource)dataSource).setUser(USERNAME);
                    ((MysqlDataSource)dataSource).setPassword(PASSWORD);
                }
            }
        }
        return dataSource;
    }
    //实现getConnection
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
    //实现close
    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet){
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

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestMysql {
    public static void main(String[] args) throws SQLException {
        DataSource dataSource=new MysqlDataSource();
        ((MysqlDataSource)dataSource).setURL("jdbc:mysql:///myl?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("1234");
        Connection connection=dataSource.getConnection();
        String sql="select * from myl";
        PreparedStatement statement=connection.prepareStatement(sql);
        //执行sql
        ResultSet resultSet=statement.executeQuery();
        //遍历结果
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("id"));
            System.out.println(resultSet.getString("date"));
        }
        //关闭链接
        resultSet.close();
        statement.close();
        connection.close();

    }

}

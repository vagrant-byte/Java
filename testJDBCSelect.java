import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class testJDBCSelect {
    public static void main(String[] args) throws SQLException {
        //创建DataSource对象

        DataSource dataSource=new MysqlDataSource();
        ((MysqlDataSource)dataSource).setURL("jdbc:mysql:///ebook?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("1234");

        //创建Connection对象，和数据库建立连接
        Connection connection=dataSource.getConnection();
        //借助PrepareStatement 拼装sql语句
        String sql="select * from borrow_info";
        PreparedStatement statement=connection.prepareStatement(sql);
        //执行SQL语句
        ResultSet resultSet=statement.executeQuery();
        //遍历结果集
        while (resultSet.next()) {
            int id=resultSet.getInt("id");
            int book_id=resultSet.getInt("book_id");
            int student_id=resultSet.getInt("student_id");
            String start_time=resultSet.getString("start_time");
            String end_time=resultSet.getString("end_time");
            System.out.println("id  "+id+"book_id  "+book_id+"student_id  "+student_id+"start_time  "+start_time+"end_time  "+end_time);
        }
        //关闭释放资源
        resultSet.close();
        statement.close();
        connection.close();

    }
}

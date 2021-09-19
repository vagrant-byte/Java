import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class testJDBC {
    public static void main(String[] args) throws SQLException {
        //创建DataSource对象
        DataSource dataSource=new MysqlDataSource();
        //对DataSource进行配置，以便后边访问数据库服务器
        ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/java?characterEncoding=utf-8&&useSSL=true");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("1234");
        //和数据库建立连接
        Connection connection=dataSource.getConnection();
        //拼装SQL语句 用到PrepareStatement对象
        //动态拼接
        int id=1;
        String name="曹操";
        //? 是一个占位符，把具体的变量值替换到？位置
        String sql="insert into student values(?,?)";
        PreparedStatement statement=connection.prepareStatement(sql);
        //这里的1 2 相当于？的下标
        statement.setInt(1,id);
        statement.setString(2,name);
        //拼装完毕 执行SQL
        //insert delete update 都使用executeUpdate 方法来执行
        //select 就使用 executeQuery来执行
        //返回值表示此次操作修改了多少行
        int ret=statement.executeUpdate();
        System.out.println("ret"+ret);
        //执行完毕 关闭释放相关资源
        //后创建的先释放
        statement.close();
        connection.close();

    }
}

package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<Book> search() {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        List<Book> books=new ArrayList<>();
        try {
            //1.和数据库建立连接
            connection=DBUtil.getConnection();
            //2.拼装SQL语句
            String sql="select * from book";
            //3.执行SQL语句
            statement=connection.prepareStatement(sql);
            resultSet=statement.executeQuery();
            //4.遍历结果集
            while (resultSet.next()) {
                Book book=new Book();
                book.setBookid(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                book.setPrice(resultSet.getInt("price"));
                book.setType(resultSet.getString("type"));
                book.setBorrow(resultSet.getInt("isBorrow")==1);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return books;
    }
    //3.根据书名找书籍
    public List<Book> find(String name) {
        //查找book书籍
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        List<Book> books=new ArrayList<>();
        try {
            //与数据库建立连接
            connection=DBUtil.getConnection();
            //拼装SQL语句
            String sql="select * from book where name =?";
            //执行SQL语句
            statement=connection.prepareStatement(sql);
            statement.setString(1,name);
            resultSet=statement.executeQuery();
            while (resultSet.next()) {
                Book book=new Book();
                book.setBookid(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                book.setPrice(resultSet.getInt("price"));
                book.setType(resultSet.getString("type"));
                book.setBorrow(resultSet.getInt("isBorrow")==1);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
        return books;
    }
    //4.删除书籍
    public boolean del(int bookid) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            String sql="delete from book where id=?";
            statement=connection.prepareStatement(sql);
            statement.setInt(1,bookid);
            int ret=statement.executeUpdate();
            if(ret!=1) {
                return false;
            }
            return true;
        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
        return false;
    }
    //5.借书
    public boolean borrow(int bookid) {
        Connection connection=null;
        PreparedStatement statement1=null;
        PreparedStatement statement2=null;
        ResultSet resultSet=null;
        try {
            connection=DBUtil.getConnection();
            String sql="select * from book where id=?";
            statement1=connection.prepareStatement(sql);
            statement1.setInt(1,bookid);
            resultSet=statement1.executeQuery();
            if(resultSet.next()) {
                boolean isBorrow=(resultSet.getInt("isBorrow")==1);
                if(isBorrow) {
                    System.out.println("书已借出，无法借阅");
                    return false;
                }
            } else {
                System.out.println("书不存在，无法借阅");
                return false;
            }
            sql="update book set isBorrow = 1 where id=?";
            statement2=connection.prepareStatement(sql);
            statement2.setInt(1,bookid);
            int ret=statement2.executeUpdate();
            if(ret!=1) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement2 != null) {
                try {
                    statement2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement1 != null) {
                try {
                    statement1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
        }
    //6.还书
    public boolean Return(int bookid) {
        Connection connection=null;
        PreparedStatement statement1=null;
        PreparedStatement statement2=null;
        ResultSet resultSet=null;
        try {
            connection=DBUtil.getConnection();
            String sql="select * from book where id=?";
            statement1=connection.prepareStatement(sql);
            statement1.setInt(1,bookid);
            resultSet=statement1.executeQuery();
            if(resultSet.next()) {
                boolean isBorrow=(resultSet.getInt("isBorrow")==1);
                if(!isBorrow) {
                    System.out.println("书已存在，无法归还");
                    return false;
                }
            } else {
                System.out.println("书不存在，无法归还");
                return false;
            }
            sql="update book set isBorrow = 0 where id=?";
            statement2=connection.prepareStatement(sql);
            statement2.setInt(1,bookid);
            int ret=statement2.executeUpdate();
            if(ret!=1) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement2 != null) {
                try {
                    statement2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement1 != null) {
                try {
                    statement1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}


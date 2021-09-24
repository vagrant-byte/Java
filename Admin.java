package jdbc;

import jdbc.operation.*;

import java.util.Scanner;

//管理员用户
public class Admin extends User {
    public Admin() {
        this.operations=new IOperation[] {
          new ExitOperation(),
          new DisplayOperation(),
          new FindOperation(),
          new AddOperation(),
          new DelOperation()
        };
    }

    @Override
    public int menu() {
        //打印管理员用户菜单
        System.out.println("====================");
        System.out.println("欢饮您，管理员用户"+this.getUsername()+"!");
        System.out.println("1.查看书籍列表");
        System.out.println("2.查找指定书籍");
        System.out.println("3.新增书籍");
        System.out.println("4.删除书籍");
        System.out.println("0.退出系统");
        System.out.println("====================");
        System.out.println("请输入选项");
        Scanner scanner=new Scanner(System.in);
        int choice=scanner.nextInt();
        return choice;
    }
}

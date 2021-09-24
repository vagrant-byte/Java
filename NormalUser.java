package jdbc;

import jdbc.operation.*;

import java.util.Scanner;

public class NormalUser extends User {
    public NormalUser() {
        //初始化operations
        this.operations=new IOperation[] {
                new ExitOperation(),
                new DisplayOperation(),
                new FindOperation(),
                new BorrowOperation(),
                new ReturnOperation()
        };
    }

    @Override
    public int menu() {
        //打印普通用户菜单
        System.out.println("====================");
        System.out.println("欢饮您，"+this.getUsername()+"!");
        System.out.println("1.查看书籍列表");
        System.out.println("2.查找指定书籍");
        System.out.println("3.借阅书籍");
        System.out.println("4.归还书籍");
        System.out.println("0.退出系统");
        System.out.println("====================");
        System.out.println("请输入选项：");
        Scanner scanner=new Scanner(System.in);
        int choice=scanner.nextInt();
        return choice;
    }
}

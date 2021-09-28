package 线程;

import com.sun.deploy.net.proxy.ProxyUnavailableException;

//进程
// Thread 是 Java 标准库中描述的一个关于线程的类
// 常用的方式就是自己定义一个类继承 Thread.
// 重写 Thread 中的 run 方法. run 方法就表示线程要执行的具体任务(代码).
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("hehe");
    }
}
public class ThreadDemo {
    public static void main(String[] args) {
        Thread thread=new MyThread();
        // start 方法, 就会在操作系统中真的创建一个线程出来. (内核中搞个 PCB, 加入到双线链表中)
        // 这个新的线程, 就会执行 run 中所描述的代码.
        thread.start();
    }
}

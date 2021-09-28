package 线程;

public class ThreadDemo4 {
    public static void main(String[] args) {
        // 这个语法就是匿名内部类~
        // 相当于是创建了一个匿名的类, 这个类继承了 Thread.
        // 此处咱们 new 的实例, 其实是 new 了这个新的子类的实例.
        Thread t=new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("hehe");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }
}

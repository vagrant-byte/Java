package 线程;

public class ThreadDemo11 {
    public static void main(String[] args) {
        Thread t=new Thread() {
            @Override
            public void run() {
                //默认情况下 isInterrupted 值为 false
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("线程运行中");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        //在这里加个break就可以保证循环结束
                        break;
                    }
                }
            }
        };
        t.start();

        //在主线程中，通过t.interrupt()方法来设置这个标记位
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 这个操作就是把 Thread.currentThread().isInterrupted() 给设成 true
        t.interrupt();
    }
}

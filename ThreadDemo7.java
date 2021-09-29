package 线程;

public class ThreadDemo7 {
    private static final long count=10_0000_0000;
    // 串行的来针对 a 和 b 进行自增
    public static void serial() {
        // System.currentTimeMillis 获取到当前系统的毫秒级时间戳.
        long beg= System.currentTimeMillis();
        int a=0;
        for (int i = 0; i < count; i++) {
            a++;
        }
        int b=0;
        for (int i = 0; i < count; i++) {
            b++;
        }
        long end=System.currentTimeMillis();
        System.out.println("time= "+ (end-beg));
    }
    public static void concurrency() {
        long beg=System.currentTimeMillis();
        Thread t1=new Thread() {
            @Override
            public void run() {
                int a=0;
                a++;
            }
        };
        t1.start();
      Thread t2=new Thread() {
          @Override
          public void run() {
              int b=0;
              b++;
          }
      };
      t2.start();
        // 需要保证 t1 和 t2 都执行完了之后
        // 再结束计时
        try {
            // join 就是等待对应的线程结束.
            // 当 t1 和 t2 没执行完之前, join 方法就会阻塞等待.
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - beg));
    }

    public static void main(String[] args) {
        concurrency();
    }
}

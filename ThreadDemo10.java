package 线程;

public class ThreadDemo10 {
    private static boolean flag=true;

    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread() {
            @Override
            public void run() {
                while (flag) {
                    System.out.println("线程运行中....");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("线程结束");
            }
        };
        t.start();
        //主循环中等待三秒
        Thread.sleep(3000);
        //三秒中之后，就把flag改成false
        flag=false;
    }
}

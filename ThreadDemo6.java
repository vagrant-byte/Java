package 线程;
//lambda表达式
public class ThreadDemo6 {
    public static void main(String[] args) {
        Thread t=new Thread(()-> {
            while (true) {
                System.out.println("hehe");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}

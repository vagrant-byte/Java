package 线程;

class MyThread2 extends Thread{
    @Override
    public void run() {
        System.out.println("hello");
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class ThreadDemo2 {
    public static void main(String[] args) {
        Thread t=new MyThread2();
        t.start();
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println("word");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

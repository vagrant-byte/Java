package 线程;

public class ThreadDemo12 {
    static class count {
        public int count=0;
        synchronized public void increase() {
            count++;
        }
    }
    static count count=new count();

    public static void main(String[] args) {
        Thread t1=new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <5000; i++) {
                    count.increase();
                }
            }
        };

        Thread t2=new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <5000; i++) {
                    count.increase();
                }
            }
        };

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count.count);
    }






 }


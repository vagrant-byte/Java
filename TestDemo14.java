import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestDemo14 {
    public static void main(String[] args) throws InterruptedException {
        //创建一个阻塞duilie
        BlockingQueue<Integer> queue=new LinkedBlockingQueue<>();

        //创建一个生产者
        Thread producer=new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <1000 ; i++) {
                    System.out.println("生产者"+i);
                    try {
                        queue.put(i);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        producer.start();

        //创建一个消费者
        Thread consumer=new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        int num=queue.take();
                        System.out.println("消费者"+num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        consumer.start();


        producer.join();
        consumer.join();
    }
}

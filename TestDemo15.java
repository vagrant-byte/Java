import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class TestDemo15 {
    //使用循环队列实现阻塞队列
     static class BlockingQueue {
         private int[] items=new int[1000];
         private int head=0;
         private int tail=0;
         private int size=0;
         private Object object=new Object();

        //入队列
        private void put(int val) throws InterruptedException{
            synchronized (object) {
                while (size==items.length) {
                    //元素已满 进行阻塞
                    object.wait();
                }
                items[tail]=val;
                tail++;
                if(tail>=items.length) {
                    tail=0;
                }
                size++;
                //唤醒take中的wait
                object.notify();
            }

        }

        //出队列
        public int take() throws InterruptedException {
            int res=0;
            synchronized (object) {
                while (size==0) {
                    //队列已空 无法出队列 进行阻塞
                    object.wait();
                }
                res=items[head];
                head++;
                if(head>=items.length) {
                    head=0;
                }
                size--;
                //唤醒put中的wait
                object.notify();
            }
            return res;
        }
     }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue=new BlockingQueue();
        //消费者
        Thread consumer=new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        int ret=blockingQueue.take();
                        System.out.println("消费者"+ret);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        consumer.start();
        //生产者
        Thread producer=new Thread() {
            @Override
            public void run() {
                for (int i = 1; i <1000 ; i++) {
                    System.out.println("生产者"+i);
                    try {
                        blockingQueue.put(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        producer.start();

        consumer.join();
        producer.join();
    }


}

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class TestDemo13 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue=new LinkedBlockingDeque<>();
        //put带有阻塞功能，但offer不带，使用阻塞队列一般使用put
        queue.put("hehe");
        String elem=queue.take();
        System.out.println(elem);
        elem=queue.take();
        System.out.println(elem);
    }

}

import java.util.Timer;
import java.util.TimerTask;

public class TestDemo16 {
    public static void main(String[] args) {
        System.out.println("代码开始执行");
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("触发定时器");
            }
        },3000);
    }
}

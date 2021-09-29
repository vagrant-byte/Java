package 线程;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

//线程定义名字
public class ThreadDemo8 {
    public static void main(String[] args) {
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("hehe");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"myThread");
        t.start();
    }
}

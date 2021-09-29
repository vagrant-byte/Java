package 线程;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class ThreadDemo9 {
    public static void main(String[] args) {
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // 打印当前线程的名字
                    // Thread.currentThread 这个静态方法, 获取到当前线程实例.
                    // 哪个线程调用的这个方法, 就能获取到对应的实例.
                    System.out.println(Thread.currentThread());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"myThread");
        t.start();
        // 在这里也打印一下这个线程的属性.
        System.out.println("id: "+t.getId());//Id
        System.out.println("name: " + t.getName());//名称
        System.out.println("state:" + t.getState());//状态
        System.out.println("priority: " + t.getPriority());//优先级
        System.out.println("isDaemon: " + t.isDaemon());//是否后台线程
        System.out.println("isInterrupted: " + t.isInterrupted());//是否被中段
        System.out.println("isAlive: " + t.isAlive());//是否存活

    }




}

package 线程;


import java.util.concurrent.PriorityBlockingQueue;

public class ThreadDemo13 {
    //定时器实现
    static class Task implements Comparable<Task> {
       //command表示任务是什么
        private Runnable command;
        //time表示这个任务什么时候到时间
        private long time;

        public Task(Runnable command,long time) {
            this.command=command;
            this.time=System.currentTimeMillis()+time;
        }
        public void run() {
            command.run();
        }

        @Override
        public int compareTo(Task o) {
            return (int)(this.time-o.time);
        }
    }

    static class Timer {
        private PriorityBlockingQueue<Task> queue=new PriorityBlockingQueue<>();
        //使用locker对象解决忙等
        public Object locker=new Object();

        public void schedule(Runnable command,long delay) {
            Task task=new Task(command,delay);
            queue.put(task);

            // 每次插入新的任务都要唤醒扫描线程. 让扫描线程能够重新计算 wait 的时间, 保证新的任务也不会错过~~
            synchronized (locker) {
                locker.notify();
            }
        }

        public Timer() {
            //创建扫描线程，判断当前任务是不是已经到时间执行
            Thread t=new Thread() {
                @Override
                public void run() {
                    while (true) {
                        //取队首元素判断时间是不是到了
                        try {
                            Task task=queue.take();
                            long curTime=System.currentTimeMillis();
                            if(task.time>curTime) {
                                //时间没有到不执行重新插入队列
                                queue.put(task);
                                //根据时间差来进行等待
                                synchronized (locker) {
                                    locker.wait(task.time-curTime);
                                }
                            } else {
                                //时间到
                                task.run();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }
            };
            t.start();

        }
    }

    public static void main(String[] args) {
        System.out.println("程序启动");
        Timer timer=new Timer();
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        },3000);
    }



}

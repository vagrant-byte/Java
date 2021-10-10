//单例模式
public class Singleton {
    private  Singleton() {

    }
    /*饿汉模式 线程安全
    //private static Singleton instance=new Singleton();

    public static Singleton getInstance() {
        return instance;
    }*/

    /*懒汉模式 线程不安去
    private static Singleton instance=null;
    public static Singleton getInstance() {
        if(instance==null) {
            instance=new Singleton();
        }
        return instance;
    }*/

    //懒汉模式 线程安全
    private static Singleton instance=null;
    public static Singleton getInstance() {
        //区别当前是首批调用还是后续调用决定要不要加锁
        if(instance==null) {
            //进行加锁操作
            synchronized (Singleton.class) {
                if(instance==null) {
                    instance=new Singleton();
                }
            }
        }
        return instance;
    }

}

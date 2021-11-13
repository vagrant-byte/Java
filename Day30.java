import sun.security.krb5.SCDynamicStoreConfig;

import java.util.Scanner;

public class Day30 {
    //因子个数
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            int n=scanner.nextInt();
            int count=0;
            for (int i = 2; i <Math.sqrt(n) ; i++) {
                if(n%i==0) {
                    while (n%i==0) {
                        n/=i;
                    }
                    count++;
                }
            }
            if(n!=1) {
                count++;
            }
            System.out.println(count);
        }
    }

    //最难的问题
    public static void main2(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            String string=scanner.nextLine();
            char[] real={'V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U'};
            char[] original={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
            StringBuffer res=new StringBuffer();
            for (int i = 0; i <string.length() ; i++) {
                for (int j = 0; j <original.length ; j++) {
                    if(string.charAt(i)==original[j]) {
                        res.append(real[j]);
                    }
                }
                if(string.charAt(i)==' ') {
                    res.append(' ');
                }
            }
            System.out.println(res.toString());
        }
    }
    public static void main1(String[] args) throws InterruptedException {
        final Object object=new Object();
        Thread t1=new Thread() {
            @Override
            public void run() {
                synchronized (object) {
                    try {
                        object.wait();
                        System.out.println("t1");
                    } catch (InterruptedException e) {

                    }
                }
            }
        };
        t1.start();
        Thread.sleep(1000);
        Thread t2=new Thread() {
            @Override
            public void run() {
                synchronized (object) {
                    object.notifyAll();
                    System.out.println("t2");
                }
            }
        };
        t2.start();

    }


}

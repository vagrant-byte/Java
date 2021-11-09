import java.util.Scanner;

public class Day26 {
    //快到碗里来
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            double n=scanner.nextDouble();
            double r=scanner.nextDouble();
            double L=2*3.14*r;
            if(n<=L) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
    //变态青蛙跳台阶
    public static int fib(int n) {
        if(n<=0) {
            return 0;
        }
        if(n==1||n==2) {
            return n;
        }
        return 2*fib(n-1);
    }
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        System.out.println(fib(n));
    }
}

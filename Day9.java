import java.util.Scanner;

public class Day9 {
    //走方格方案数
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            int n=scanner.nextInt();
            int m=scanner.nextInt();
            int num=step(n,m);
            System.out.println(num);
        }

    }

    private static int step(int n, int m) {
        if(n==1||m==1) {
            return n+m;
        }
        return step(n-1,m)+step(n,m-1);
    }

    //A+B的值
    public static void main1(String[] args) {
      int a=2;
      int b=3;
      int sum=addAB(a,b);
        System.out.println(sum);
    }
    public static int addAB(int A,int B) {
        if(B==0) {
            return A;
        }
        int c=A^B;
        int d=(A&B)<<1;
        return addAB(c,d);
    }
}

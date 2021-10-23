import java.util.Scanner;

public class Day12 {
    //二进制插入
    public int binInsert(int n, int m, int j, int i) {
        // write code here
        m=m<<j;
        return n|m;
    }
    //任意一个偶数都可以由两个素数组成
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int num=scanner.nextInt();
        int result=0;
        for (int i = 2; i <=num/2 ; i++) {
            if(prime(i)) {
                if(prime(num-i)) {
                    result=i;
                }
            }
        }
        System.out.println(result);
        System.out.println(num-result);
    }

    private static boolean prime(int i) {
        for (int j = 2; j < i; j++) {
            if(i%j==0) {
                return false;
            }
        }
        return true;
    }
}

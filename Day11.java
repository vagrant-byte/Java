import java.util.HashMap;
import java.util.Scanner;

public class Day11 {
    //求最大连续bit数
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            int num=scanner.nextInt();
            int count=0;
            int max=0;
            while (num>0) {
                if((num&1)==1) {
                    count++;
                    if(max<count) {
                        max=count;
                    }
                } else {
                    count=0;
                }

                num>>=1;
            }
            System.out.println(max);
        }

    }
    //最近公共祖先
    public static int getLCA(int a,int b) {
        while (a!=b) {
            if(a>b) {
                a=a/2;
            } else {
                b=b/2;
            }
        }
        return a;
    }

}

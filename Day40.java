import java.util.Scanner;

public class Day40 {
    //最长上升子序列LIS
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            int n=scanner.nextInt();
            int[] height=new int[n];
            int[] f=new int[1001];
            for (int i = 0; i <n ; i++) {
                height[i]=scanner.nextInt();
                f[i]=1;
            }
            for (int i = 0; i <n ; i++) {
                for (int j = 0; j <i; j++) {
                    if(height[j]<height[i]) {
                        f[i]=Math.max(f[i],f[j]+1);
                    }
                }
            }
            int max=0;
            for (int i = 0; i <n; i++) {
                max=Math.max(max,f[i]);
            }
            System.out.println(max);
        }
    }

    //错发邮件
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        long[] error=new long[21];
        error[0]=0;
        error[1]=0;
        error[2]=1;
        for (int i = 3; i <21 ; i++) {
            error[i]=(i-1)*(error[i-1]+error[i-2]);
        }
        while (scanner.hasNext()) {
            int n=scanner.nextInt();
            System.out.println(error[n]);
        }
    }
}

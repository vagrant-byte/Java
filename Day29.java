import java.util.Scanner;

public class Day29 {
    //假币
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            int n=scanner.nextInt();
            if(n==0) {
                return;
            }
            int ret=1;
            while(n!=1) {
                if(n%3==0) {
                    n/=3;
                } else {
                    n=n/3+1;
                }
                ret++;
            }
            System.out.println(ret);
        }
    }
    //0-1背包问题
    public static int getFirstUnFormedNum(int[] arr) {
        int min=arr[0];
        int max=arr[0];
        for (int i = 1; i <arr.length ; i++) {
            if(arr[i]<min) {
                min=arr[i];
            }
            max+=arr[i];
        }
        boolean[] dp=new boolean[max+1];
        dp[0]=true;
        for (int i = 0; i <arr.length ; i++) {
            for (int j = max; j >=arr[i] ; j--) {
                dp[j]=dp[j-arr[i]]?true:dp[j];
            }
        }
        for (int i = min; i <=max ; i++) {
            if(!dp[i]) {
                return i;
            }
        }
        return max+1;





    }

    public static void main1(String[] args) {
        int[] a={3,2,5};
        System.out.println(getFirstUnFormedNum(a));
    }
}

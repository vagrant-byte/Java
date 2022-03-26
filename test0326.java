import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class test0326 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()) {
            int n=sc.nextInt();
            int[] tmp=new int[n];
            for (int i = 0; i <n ; i++) {
                tmp[i]=sc.nextInt();
            }
            Arrays.sort(tmp);
            int sum=0;
            for (int i = 0; i <n ; i++) {
                sum+=tmp[i];
            }
            if(n<4) {
                System.out.println(sum);
            }
            if(n%2==0) {
                sum+=tmp[n/2]+tmp[n/2-1];
            } else {
                sum+=tmp[n/2];
            }
            System.out.println(sum);

        }
    }
    public static void main3(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()) {
            int n=sc.nextInt();
            int[] tmp=new int[n];
            for (int i = 0; i <n ; i++) {
                tmp[i]=sc.nextInt();
            }
            int[][] dp=new int[n][n];
            int max=0;
            for (int i = 0; i <n ; i++) {
                for (int j = 0; j <n ; j++) {
                    if(i==0||j==0) {
                        dp[i][j]=tmp[i]+tmp[j];
                    } else {
                        dp[i][j]=dp[i-1][j]+dp[i][j-1];
                    }
                }
            }
            for (int i = 0; i <n ; i++) {
                for (int j = 0; j <n ; j++) {
                    if(dp[i][j]%7==0&&dp[i][j]>max) {
                        max=dp[i][j];
                    }
                }
            }
            if(max>=0) {
                System.out.println(max);
            } else {
                System.out.println(0);
            }
        }
    }
    public static void main2(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()) {
            int n=sc.nextInt();
            int[] tmp=new int[n];
            for (int i = 0; i <n ; i++) {
                tmp[i]=sc.nextInt();
            }
            if(n==2) {
                System.out.println(tmp[1]-tmp[0]);
            } else {
                int mid=(tmp[n-2]+tmp[0])/2;
                System.out.println(mid-tmp[0]);
            }
        }

    }
    public static void main1(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()) {
            String s=sc.next();
            HashMap<Character,Integer> map=new HashMap<>();
            char[] chars=s.toCharArray();

            for (int i = 0; i <chars.length ; i++) {
                if(map.containsKey(chars[i])) {
                    map.put(chars[i],map.get(chars[i])+1);
                } else {
                    map.put(chars[i],1);
                }
            }
            int numa=0;
            int numb=0;
            int numc=0;
            for (Map.Entry<Character,Integer> entry:map.entrySet()) {
                if(entry.getKey()=='a') {
                    numa=entry.getValue();
                }
                if(entry.getKey()=='b') {
                    numb=entry.getValue();
                }
                if(entry.getKey()=='c') {
                    numc=entry.getValue();
                }
            }
            numa/=2;
            numc/=3;
            int max=Math.max(numa,numb);
            max=Math.min(max,numc);
            System.out.println(max);
        }
    }
}

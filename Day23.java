import java.util.Arrays;
import java.util.Scanner;

public class Day23 {
    //字符串编辑距离
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            String str1=scanner.next();
            String str2=scanner.next();
            int[][] res=new int[str1.length()+1][str2.length()+1];
            for (int i = 0; i <=str1.length() ; i++) {
                res[i][0]=i;
            }
            for (int i = 0; i <=str2.length() ; i++) {
                res[0][i]=i;
            }
            for (int i = 1; i <=str1.length() ; i++) {
                for (int j = 1; j <=str2.length() ; j++) {
                    if(str1.charAt(i-1)==str2.charAt(j-1)) {
                        res[i][j]=res[i-1][j-1];
                    } else {
                        res[i][j]=Math.min(res[i-1][j-1],Math.min(res[i-1][j],res[i][j-1]))+1;
                    }
                }
            }
            System.out.println(res[str1.length()][str2.length()]);
        }
    }
    //微信红包
    public static int getValue(int[] gifts, int n) {
        // write code here
        Arrays.sort(gifts);
        int num=gifts[n/2];
        int count=0;
        for (int i = 0; i <n ; i++) {
            if(gifts[i]==num) {
                count++;
            }
        }
        if(count>n/2) {
            return num;
        }
        return 0;
    }
    public static void main1(String[] args) {
       int[] a={1,1,2,2,3,3};
       int n=6;
        System.out.println(getValue(a, n));
    }
}

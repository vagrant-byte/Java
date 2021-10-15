import java.util.Scanner;

public class Day5 {
    //回文数字
    public static boolean noon(String str) {
        int left=0;
        int right=str.length()-1;
        while (left<right) {
            char[] chars=str.toCharArray();
            if(chars[left]==chars[right]) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            String A=scanner.nextLine();
            String B=scanner.nextLine();
            int count=0;
            for (int i = 0; i <=A.length() ; i++) {
                StringBuilder stringBuilder=new StringBuilder(A);
                stringBuilder.insert(i,B);
                if(noon(stringBuilder.toString())) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
    //最大子序和
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] res=new int[n];
        int max=Integer.MIN_VALUE;
        int result=0;
        for (int i = 0; i <n ; i++) {
            res[i]=scanner.nextInt();
            if(result>=0) {
                result+=res[i];
            } else {
                result=res[i];
            }
            if(result>max) {
                max=result;
            }
        }
        System.out.println(max);
    }
}

import java.util.Scanner;

public class Day20 {
    //公共子串计算
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String str1=scanner.next();
        String str2=scanner.next();
        char[] chars1=str1.toCharArray();
        char[] chars2=str2.toCharArray();
        int max=0;
        int[][] res=new int[str1.length()+1][str2.length()+1];
        for (int i = 0; i <=str1.length()  ; i++) {
            res[i][0]=0;
        }
        for (int j = 0; j <=str2.length() ; j++) {
            res[0][j]=0;
        }
        for (int i = 1; i <=str1.length() ; i++) {
            for (int j = 1; j <=str2.length() ; j++) {
                if(chars1[i-1]==chars2[j-1]) {
                    res[i][j] = res[i - 1][j - 1] + 1;
                    if(max<res[i][j]) {
                        max=res[i][j];
                    }

                }else {
                    res[i][j]=0;
                }
            }
        }
        System.out.println(max);

    }
    //反转字符串
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String str=scanner.next();
        int left=0;
        int right=str.length()-1;
        char[] chars=str.toCharArray();
        while (left<right) {
            char tmp=chars[left];
            chars[left]=chars[right];
            chars[right]=tmp;
            left++;
            right--;
        }
        System.out.println(new String(chars));
    }
}

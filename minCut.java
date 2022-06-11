package 动态规划;
//分割回文串  最少的分割次数
public class minCut {
    public static int minCut(String s) {
        int len=s.length();
        //存放分割次数
        int[] arr=new int[len+1];
        for (int i = 0; i <arr.length ; i++) {
            //存放最多的分割次数，即每个字符都进行分割
            arr[i]=i-1;
        }
        for (int i = 1; i <arr.length ; i++) {
            for (int j = 0; j <i ; j++) {
                String s1=s.substring(j,i);
                //判断[j+1,i]是否是回文串
                if(ispol(s1)) {
                    arr[i]=Math.min(arr[j]+1,arr[i]);
                }
            }
        }
        return arr[len];
    }

    private static boolean ispol(String s1) {
        int left=0;
        int right=s1.length()-1;
        char[] chars=s1.toCharArray();
        while (left<=right) {
            if(chars[left]==chars[right]) {
                left++;
                right--;
            }else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s="ab";
        int n= minCut(s);
        System.out.println(n);
    }
}

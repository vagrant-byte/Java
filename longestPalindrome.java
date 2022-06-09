package 字符串;
//最长回文子串
public class longestPalindrome {
    public static String longestPalindrome(String s) {
        if(s.equals("")) {
            return "";
        }
        int len=s.length();
        if(len==1) {
            return s;
        }
        String origin=s;
        StringBuffer stringBuffer=new StringBuffer(s);
        String s1=stringBuffer.reverse().toString();
        int end=0;
        int maxLen=0;
        int[][] dp=new int[len][len];
        for (int i = 0; i <len ; i++) {
            for (int j = 0; j <len ; j++) {
                if(origin.charAt(i)==s1.charAt(j)) {
                    if(i==0||j==0) {
                        dp[i][j]=1;
                    }else {
                        dp[i][j]=dp[i-1][j-1]+1;
                    }
                }
                if(maxLen<dp[i][j]) {
                    int beforeRev = len - 1 - j;
                    //"aacabdkacaa" 不判断的话这种情况有问题
                    if (beforeRev + dp[i][j] - 1 == i) { //判断下标是否对应
                        maxLen = dp[i][j];
                        end = i;
                    }
                }
            }
        }
        return s.substring(end-maxLen+1,end+1);


    }

    public static void main(String[] args) {
        String s="abbac";
        System.out.println(longestPalindrome(s));
    }
}

package 动态规划.多样本位置全对应的尝试模型;

public class 最长公共子序列 {
    public int lcse(char[] str1,char[] str2) {
        int[][] dp=new int[str1.length][str2.length];
        dp[0][0]=str1[0]==str2[0]?1:0;
        for (int i = 1; i <str1.length ; i++) {
            dp[i][0]=Math.max(dp[i-1][0],str1[i]==str2[0]?1:0);
        }
        for (int i = 1; i <str2.length ; i++) {
            dp[0][i]=Math.max(dp[0][i],str1[0]==str2[i]?1:0);
        }
        for (int i = 1; i <str1.length ; i++) {
            for (int j = 1; j < str2.length; j++) {
                dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                if(str1[i]==str2[j]) {
                    dp[i][j]=Math.max(dp[i][j],dp[i-1][j-1]+1);
                }
            }
        }
        return dp[str1.length-1][str2.length-1];
    }
}

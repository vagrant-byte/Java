package 动态规划;
//背包问题
public class backPackII {
    public int backPackII(int m, int[] a, int[] v) {
        int n=a.length;
        int[][] dp=new int[n+1][m+1];
        for (int i = 0; i <n+1 ; i++) {
            dp[i][0]=0;
        }
        for (int i = 0; i <m ; i++) {
            dp[0][i]=0;
        }
        for (int i = 1; i <=n ; i++) {
            for (int j = 1; j <=m ; j++) {
                if(a[i-1]<j) {
                    dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-a[i-1]]+v[i-1]);
                } else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[n][m];
    }
}

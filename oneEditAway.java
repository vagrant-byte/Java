public class oneEditAway {
    //一次编辑
    public boolean oneEditAway(String first, String second) {
        int n=first.length();
        int m=second.length();
        int[][] dp=new int[n+1][m+1];
        for (int i = 0; i <=n ; i++) {
            dp[i][0]=i;
        }
        for (int i = 0; i <=m ; i++) {
            dp[0][i]=i;
        }
        for (int i = 1; i <=n ; i++) {
            for (int j = 1; j <=m ; j++) {
                if(first.charAt(i-1)==second.charAt(j-1)) {
                    dp[i][j]=dp[i-1][j-1];
                } else {
                    dp[i][j]=Math.min(Math.min(dp[i-1][j]+1,dp[i][j-1]+1),dp[i-1][j-1]+1);
                }
            }
        }
        return dp[n][m]<=1;
    }
}

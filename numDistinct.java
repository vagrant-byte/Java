package 动态规划;
//不同的子序列
public class numDistinct {
    public int numDistinct(String s, String t) {
        int col=s.length();
        int row=t.length();
        int[][] dp=new int[col+1][row+1];
        dp[0][0]=1;
        for (int i = 1; i <=col ; i++) {
            dp[i][0]=1;
        }
        for (int i = 1; i <=row ; i++) {
            dp[0][i]=0;
        }
        for (int i = 1; i <=col ; i++) {
            for (int j = 1; j <=row ; j++) {
                if(s.charAt(i-1)==t.charAt(j-1)) {
                    dp[i][j]=dp[i-1][j-1] +dp[i-1][j];
                }else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[col][row];
    }
    //一维数组实现
    public int numDistinct1(String s, String t) {
        int col=s.length();
        int row=t.length();
        int[] dp=new int[row+1];
        dp[0]=1;
        for (int i = 1; i <=row ; i++) {
            dp[i]=0;
        }
        for (int i = 1; i <=col ; i++) {
            for (int j = row; j >0 ; j--) {
                if(s.charAt(i-1)==t.charAt(j-1)) {
                    dp[j]=dp[j-1]+dp[j];
                }
            }
        }
        return dp[row];
    }
}

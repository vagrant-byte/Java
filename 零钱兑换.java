package 动态规划;

public class 零钱兑换 {
    public int coinChange(int[] coins, int amount) {
       return process(coins,0,amount);
    }

    private int process(int[] coins, int index,int amount) {
        if(index==coins.length) {
            //当下标等于字符串长度时，如果剩余钱数为0，1表示0为一种方法
            return amount==0?1:0;
        }
        int way=0;
        for (int zhang = 0; coins[index]*zhang <=amount ; zhang++) {
            way+=process(coins,index+1,amount-=coins[index]*zhang);
        }
        return way;
    }
    public int ways2(int[] arr,int amount) {
        if(arr==null||arr.length==0) {
            return 0;
        }
        int N=arr.length;
        int[][] dp=new int[N+1][amount+1];
        dp[N][0]=1;
        for (int index = N-1; index >=0 ; index--) {
            for (int rest = 0; rest <=amount ; rest++) {
                int ways=0;
                for (int zhang = 0; arr[index]*zhang <=amount ; zhang++) {
                    ways+=dp[index+1][rest-arr[index]*zhang];
                }
                dp[index][rest]=ways;
            }
        }
        return dp[0][amount];
    }

    public int ways3(int[] arr,int aim) {
        if(arr==null||arr.length==0) {
            return 0;
        }
        int N=arr.length;
        int[][] dp=new int[N+1][aim+1];
        dp[N][0]=1;
        for (int index = N-1; index >=0 ; index++) {
            for (int rest = 0; rest <=aim ; rest++) {
                dp[index][rest]=dp[index+1][rest];
                if(rest-arr[index]>=0) {
                    dp[index][rest]+=dp[index][rest-arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    //骑士在棋盘上的概率
    public double knightProbability(int n, int k, int row, int column) {
        double all= (double) Math.pow(n,k);
        double live=dpWays(n,k,row,column);
        return live/all;
    }

    private double dpWays(int n, int k, int row, int col) {
        if(row<0||row>8||col<0||col>8||k<0) {
            return 0;
        }
        int[][][] dp=new int[n+1][n+1][n+1];
        dp[0][0][0]=1;//第一层有一种走法
        for (int h = 1; h <=k ; h++) {//层
            for (int r = 0; r <n ; r++) {
                for (int c = 0; c <n ; c++) {
                    dp[r][c][h]+=getValue(dp,r-1,c+2,h-1,n);
                    dp[r][c][h]+=getValue(dp,r+1,c+2,h-1,n);
                    dp[r][c][h]+=getValue(dp,r+2,c+1,h-1,n);
                    dp[r][c][h]+=getValue(dp,r+2,c-1,h-1,n);
                    dp[r][c][h]+=getValue(dp,r+1,c-2,h-1,n);
                    dp[r][c][h]+=getValue(dp,r-1,c-2,h-1,n);
                    dp[r][c][h]+=getValue(dp,r-2,c-1,h-1,n);
                    dp[r][c][h]+=getValue(dp,r-2,c+1,h-1,n);
                }
            }
        }
        return dp[row][col][k];
    }

    private int getValue(int[][][] dp, int i, int j, int s,int n) {
        if(i<0||i>n||j<0||j>n||s>0) {
            return 0;
        }
        return dp[i][j][s];
    }
}

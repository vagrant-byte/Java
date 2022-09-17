package 动态规划;

public class 机器人 {
    //一共1-N这个个位置 固定参数
    //最终的目标是E 固定参数
    //还剩rest步需要走
    //当前在cur位置
    public int f(int N,int E,int rest,int cur) {
        if(rest==0) {
            //只剩0步时，如果当前位置等于结束位置返回1，否则0
            return cur==E?1:0;
        }
        if(cur==1) {
            //如果当前位置在1位置只能向右走
            return f(N,E,rest-1,2);
        }
        if(cur==N) {
            //如果当前位置在数组的末尾位置，只能向左走
            return f(N,E,rest-1,cur-1);
        }
        return f(N,E,rest-1,cur-1)+f(N,E,rest-1,cur+1);
    }
    public int f2(int N,int E,int rest,int cur,int[][] dp) {
        if (dp[rest][cur] != -1) {
            return dp[rest][cur];
        }
        //没有命中缓存
        if (cur == 1) {
            dp[rest][cur] = f2(N, E, rest - 1, 2, dp);
        } else if (cur == N) {
            dp[rest][cur] = f2(N, E, rest - 1, N - 1, dp);
        } else {
            dp[rest][cur] = f2(N, E, rest - 1, cur - 1, dp) + f2(N, E, rest - 1, cur + 1, dp);
        }
        return dp[rest][cur];
    }
    public int dpWay(int N,int P,int M,int K) {
        int[][] dp=new int[K+1][N+1];
        dp[0][P]=1;
        for (int rest = 1; rest <=K ; rest++) {
            for (int cur = 1; cur <=N ; cur++) {
                if(cur==1) {
                    dp[rest][cur]=dp[rest-1][2];
                }else if(cur==N) {
                    dp[rest][cur]=dp[rest-1][N-1];
                }else {
                    dp[rest][cur]=dp[rest-1][cur-1]+dp[rest-1][cur+1];
                }
            }
        }
        return dp[M][K];
    }
}

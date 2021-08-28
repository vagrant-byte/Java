package 剑指offer;
//动态规划
public class dynamicProgramming {
    //斐波那契数列 青蛙跳台阶
    public int numWays(int n) {
        if(n<2) {
            return 1;
        }
        int [] num=new int[n+1];
        num[0]=1;
        num[1]=1;
        for(int i=2;i<=n;i++) {
            num[i]=(num[i-1]+num[i-2])%1000000007;
        }
        return num[n];
    }
    //股票最大利润
    public int maxProfit(int[] prices) {
        if(prices==null||prices.length<=1) {
            return 0;
        }
        int res=0;
        int min=prices[0];
        for (int i = 1; i <prices.length ; i++) {
            if(prices[i]<min) {
                min=prices[i];
            } else {
                res=Math.max(res,prices[i]-min);
            }
        }
        return res;
    }
}

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
    //连续子数组最大和
    public int maxSubArray(int[] nums) {
        int sum=0;
        int max=Integer.MIN_VALUE;
        for (int i = 0; i <nums.length ; i++) {
            if(sum<=0) {
                sum=nums[i];
            } else {
                sum+=nums[i];
            }
            max=Math.max(sum,max);
        }
        return max;
    }
    //礼物的最大价值
    public int maxValue(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int []dp = new int[n];
        for(int i = 0; i < m; i++){
            dp[0] = dp[0] + grid[i][0];
            for(int j = 1; j < n; j++){
                dp[j] = Math.max(dp[j], dp[j-1]) + grid[i][j];
            }
        }
        return dp[n-1];
    }
    //把数字翻译成字符串
    public int translateNum(int num) {
        if(num<=9) {
            return 1;
        }
        int ba=num%100;
        if(ba<=9||ba>=26) {
            return translateNum(num/10);
        } else {
            return translateNum(num/10)+translateNum(num/100);
        }

    }
    //最长不含重复字符的子字符串
    public int lengthOfLongestSubstring(String s) {
        if(s.length()==0||s.length()==1) {
            return s.length();
        }
        int start=0;
        int maxLength=0;
        char[] ch=s.toCharArray();
        for(int i=1;i<s.length();i++) {
            for(int j=start;j<i;j++) {
                if(ch[j]==ch[i]) {
                    start=j+1;
                    break;
                }
            }
            maxLength=Math.max(maxLength,i-start+1);
        }
        return maxLength;

    }
}

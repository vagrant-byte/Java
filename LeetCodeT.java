package 贪心;

public class LeetCodeT {
    //跳跃游戏
    public boolean canJump(int[] nums) {
        if(nums.length<=1) {
            return true;
        }
        int cover=0;
        for (int i = 0; i <nums.length ; i++) {
            if(i<=cover) {
                cover=Math.max(cover,i+nums[i]);
                if(cover>=nums.length-1) {
                    return true;
                }
            }

        }
        return false;

    }
    //平衡字符串
    public int balancedStringSplit(String s) {
        int count=0;
        int res=0;
        for (int i = 0; i <s.length() ; i++) {
            if(s.charAt(i)=='R') {
                res++;
            } else if(s.charAt(i)=='L') {
                res--;
            }
            if(res==0) {
                count++;
            }
        }
        return count;

    }
    //买股票的最佳时机
    public int maxProfit(int[] prices) {
        int peace=0;
        for (int i = 1; i <prices.length; i++) {
            if(prices[i]-prices[i-1]>0) {
                peace+=prices[i]-prices[i-1];
            }
        }
        return peace;
    }
}

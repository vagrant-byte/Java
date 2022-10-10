package 动态规划;

public class 目标和 {
    public int findTargetSumWays(int[] nums, int target) {
        return process(nums,0,target);
    }

    private int process(int[] nums, int index, int s) {
        if(index==nums.length) {
            return s==0?1:0;
        }
        return process(nums,index+1,s-nums[index])+process(nums,index+1,s+nums[index]);
    }
}

public class canJump {
    //跳跃游戏
    public boolean canJump(int[] nums) {
        int n=nums.length;
        if(n<=1) {
            return true;
        }
        int max=0;
        for (int i = 0; i <n ; i++) {
            if(i<=max) {
                max=Math.max(nums[i]+i,max);
                if(max>=n-1) {
                    return true;
                }
            }
        }
        return false;
    }
}

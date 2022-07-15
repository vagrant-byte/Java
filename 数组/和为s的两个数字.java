package 数组;

public class 和为s的两个数字 {
    public int[] twoSum(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;
        int[] res=new int[2];
        while (left<right) {
            int tmp=nums[left]+nums[right];
            if(tmp<target) {
                left++;
            }else if(tmp>target) {
                right--;
            }else {
                res[0]=nums[left];
                res[1]=nums[right];
                break;
            }
        }
        return res;
    }
}

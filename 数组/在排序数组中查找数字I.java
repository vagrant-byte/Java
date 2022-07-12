package 数组;

public class 在排序数组中查找数字I {
    public int search(int[] nums, int target) {
        if(nums.length==0) {
            return 0;
        }
        if(nums[0]>target||target>nums[nums.length-1]) {
            return 0;
        }
        int count=0;
        for (int i = 0; i <nums.length ; i++) {
            if(nums[i]==target) {
                count++;
            }
        }
        return count;
    }
}

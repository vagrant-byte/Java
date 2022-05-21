import java.util.Arrays;

public class repeatedNTimes {
    //在长度2N的数组中找出重复N次的元素
    //有一半的数相等，那么排列中要么所有相同的数都不相邻，要么就必定存在相邻并相等的情形。
    public int repeatedNTimes(int[] nums) {
        if(nums[1]==nums[3]) {
            return nums[1];
        }
        for (int i = 0; i <nums.length-1 ; i++) {
            if(nums[i]==nums[i+1]) {
                return nums[i];
            }
        }
        return nums[0];
    }
}

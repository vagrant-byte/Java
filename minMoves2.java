import java.util.Arrays;

public class minMoves2 {
    //最少移动次数使数组元素相等
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int tmp=nums[nums.length/2];
        int count=0;
        for (int i = 0; i <nums.length/2 ; i++) {
            count+=tmp-nums[i];
        }
        for (int i = nums.length/2+1; i <nums.length ; i++) {
            count+=nums[i]-tmp;
        }
        return count;
    }
}

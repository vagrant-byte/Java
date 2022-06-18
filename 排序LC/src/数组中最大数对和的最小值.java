import java.util.Arrays;

public class 数组中最大数对和的最小值 {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int res=0;
        for (int i = 0,j=nums.length-1; i <j ; i++,j--) {
            res=Math.max(res,nums[j]+nums[i]);
        }
        return res;
    }
}

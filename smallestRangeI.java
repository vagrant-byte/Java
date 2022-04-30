import java.util.Arrays;

public class smallestRangeI {
    //最小差值
    public int smallestRangeI(int[] nums, int k) {
        int n=nums.length;
        if(n==1) {
            return 0;
        }
        Arrays.sort(nums);
        int a=nums[0]+k;
        int b=nums[n-1]-k;
        return b-a<0?0:b-a;
    }
}

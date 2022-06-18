import java.util.Arrays;

public class 两个数对之间的最大乘积差 {
    public int maxProductDifference(int[] nums) {
        for (int i = 0; i <nums.length-1 ; i++) {
            boolean flag=false;
            for (int j = 0; j <nums.length-1-i ; j++) {
                if(nums[j]>nums[j+1]) {
                    flag=true;
                    int tmp=nums[j];
                    nums[j]=nums[j+1];
                    nums[j+1]=tmp;
                }
            }
            if(!flag) {
                break;
            }
        }
        int w=nums[0];
        int x=nums[1];
        int y=nums[nums.length-1];
        int z=nums[nums.length-2];
        return y*z-x*w;
    }
}

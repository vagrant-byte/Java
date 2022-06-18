import java.util.Arrays;

public class 数组拆分1 {
    public static int arrayPairSum(int[] nums) {
        int len=nums.length/2;
        for (int i = 0; i <nums.length ; i++) {
            for (int j = i+1; j <nums.length ; j++) {
                if(nums[i]>nums[j]) {
                    int tmp=nums[i];
                    nums[i]=nums[j];
                    nums[j]=tmp;
                }
            }
        }
        int res=0;
        int i=nums.length-2;
        if(i>=0) {
            while (len>0) {
                res+=nums[i];
                i-=2;
                len--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] r={6,2,6,5,1,2};
        System.out.println(arrayPairSum(r));
    }
}

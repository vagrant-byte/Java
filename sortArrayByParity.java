import java.util.Arrays;

public class sortArrayByParity {
    //按奇偶排序数组
    public static int[] sortArrayByParity(int[] nums) {
        int n=nums.length;
        if(nums==null||n==1) {
            return nums;
        }
        int left=0;
        int right=n-1;
        while (left<right) {
            if(nums[left]%2==1&&nums[right]%2==0) {
                int tmp=nums[left];
                nums[left]=nums[right];
                nums[right]=tmp;
            }
            if (nums[left]%2==0) {
                left++;
            }
            if (nums[right]%2!=0) {
                right--;
            }


        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums={3,1,2,4};
        int[] r=sortArrayByParity(nums);
        for (int n:r) {
            System.out.println(n);
        }
    }
}

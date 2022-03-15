import java.util.Arrays;

//下一个排序
public class nextPermutation {
    public void nextPermutation(int[] nums) {
        for (int i = nums.length-1; i >=0 ; i--) {
            for (int j = nums.length-1; j >i ; j--) {
                if(nums[j]>nums[i]) {
                    swap(nums,j,i);
                    Arrays.sort(nums,i+1,nums.length);
                    return;
                }
            }
        }
        Arrays.sort(nums);
    }

    private void swap(int[] nums, int j, int i) {
        int tmp=nums[j];
        nums[i]=nums[j];
        nums[j]=tmp;
    }


}

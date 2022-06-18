import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 找出数组排序后的目标下标 {
    public static List<Integer> targetIndices(int[] nums, int target) {
        for (int i = 1; i <nums.length ; i++) {
            int tmp=nums[i];
            int j =i-1;
            for ( ; j >=0 ; j--) {
                if(nums[j]>nums[j+1]) {
                    nums[j+1]=nums[j];
                    nums[j]=tmp;
                } else {
                    break;
                }
            }
            nums[j+1]=tmp;
        }
        List<Integer> list1=new ArrayList<>();
        for (int i = 0; i <nums.length ; i++) {
            if(nums[i]==target) {
                list1.add(i);
            }
        }
        return list1;
    }

    public static void main(String[] args) {
        int[] arr={1,2,5,2,3};
        int target=2;
        List<Integer> res=targetIndices(arr,target);
    }
}

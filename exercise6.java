import java.util.*;

public class exercise6 {
    //数组中的第K个最大元素
    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }

    public static void main(String[] args) {
        int[] nums={3,2,3,1,2,4,5,5,6};
        System.out.println(findKthLargest(nums, 4));
    }
    //三数之和
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(nums);
        if(nums.length<3||nums[0]>0) {
            return res;
        }
        for (int i = 0; i <nums.length-2 ; i++) {
            if(i>0&&nums[i]==nums[i-1]) {
                continue;
            }
            int m=nums.length-1;
            for (int j = i+1; j <nums.length; j++) {
                if(j>i+1&&nums[j]==nums[j-1]) {
                    continue;
                }
                while (m>j&&nums[j]+nums[m]>-nums[i]) {
                    m--;
                }
                if(j==m) {
                    break;
                }
                if(nums[j]+nums[m]+nums[i]==0) {
                    List<Integer> list1=new ArrayList<>();
                    list1.add(nums[i]);
                    list1.add(nums[j]);
                    list1.add(nums[m]);
                    res.add(list1);
                }
            }
        }

        return res;
    }
}

package 二分查找;

import java.util.*;

public class Solution1 {
    //排序数组中两个数字之和
    public int[] twoSum(int[] numbers, int target) {
        int left=0;
        int right=numbers.length-1;
        int[] res=new int[2];
        while (left<right) {
            if(numbers[left]+numbers[right]==target) {
                res[0]=left;
                res[1]=right;
                break;
            } else if(numbers[left]+numbers[right]>target) {
                right--;
            } else if(numbers[left]+numbers[right]<target) {
                left++;
            }
        }
        return res;
    }
    //数组中和为0的三个数
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();
        int len=nums.length;
        if(len<3) {
            return list;
        }
        Arrays.sort(nums);
        for (int i = 0; i <len-2 ; i++) {
            //去重
            if(i>0&&nums[i]==nums[i-1]) {
                continue;
            }
            int left=i+1;
            int right=len-1;
            while (left<right) {
                if(nums[left]+nums[right]==-nums[i]) {
                    List<Integer> list1=new ArrayList<>();
                    list1.add(nums[i]);
                    list1.add(nums[left]);
                    list1.add(nums[right]);
                    list.add(list1);
                    while (left<right&&nums[left]==nums[left+1]) {
                        left++;
                    }
                    left++;
                    while (left<right&&nums[right]==nums[right-1]) {
                        right--;
                    }
                    right--;
                } else if(nums[left]+nums[right]>-nums[i]) {
                    right--;
                } else if(nums[left]+nums[right]<-nums[i]) {
                    left++;
                }
            }
        }
        return list;
//        List<List<Integer>> list=new ArrayList<>();
//        if(nums.length<3) {
//            return list;
//        }
//        Arrays.sort(nums);
//        Set<List<Integer>> set=new HashSet<>();
//        for (int i = 0; i <nums.length-2 ; i++) {
//            for (int j = i+1; j <nums.length-1 ; j++) {
//                for (int k = j+1; k <nums.length ; k++) {
//                    if(nums[i]+nums[j]+nums[k]==0) {
//                        List<Integer> list1=new ArrayList<>();
//                        list1.add(nums[i]);
//                        list1.add(nums[j]);
//                        list1.add(nums[k]);
//                        if(!set.contains(list1)) {
//                            list.add(list1);
//                            set.add(list1);
//                        }
//                    }
//                }
//            }
//        }
//        return list;
    }
}

package 剑指offer;

import java.util.HashMap;
import java.util.HashSet;

//查找算法
public class lookupAlgorithm {
    //字符串中重复的数字
    public int findRepeatNumber(int[] nums) {
       /* HashSet<Integer> set=new HashSet<>();
        for (int i = 0; i <nums.length ; i++) {
            if(set.contains(nums[i])) {
                return nums[i];
            }
            set.add(nums[i]);
        }
        return -1;*/
        int[] arr=new int[nums.length];
        for (int i = 0; i <nums.length ; i++) {
            arr[nums[i]]++;
            if(arr[nums[i]]>1) {
                return nums[i];
            }
        }
        return -1;
    }
    //在排序数组中查找数字
    public int search(int[] nums, int target) {
        //二分查找
        int left=0;
        int right=nums.length-1;
        int count=0;
        while (left<right) {
            int mid=(right+left)/2;
            if(nums[mid]<target) {
                left=mid+1;
            }
            if(nums[mid]>=target) {
                right=mid;
            }
        }
        while (left<nums.length&&nums[left++]==target) {
            count++;
        }
        return count;
    }
    //0-n-1中缺失的数字
    public int missingNumber(int[] nums) {
        int num=nums.length;
        for (int i = 0; i <nums.length; i++) {
            if(i!=nums[i]) {
                return i;
            }
        }
        return num;
    }
}

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
    //二维数组中查找
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix==null||matrix.length==0) {
            return false;
        }
        int i=matrix.length;
        int j=matrix[0].length;
        int row=0;
        int col=j-1;
        while (row<i&&col>=0) {
            if(matrix[row][col]>target) {
                col--;
            } else if(matrix[row][col]<target) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }
    //旋转数组的最小数
    public int minArray(int[] numbers) {
        for (int i = 1; i < numbers.length; i++)
            if (numbers[i] < numbers[i - 1])
                return numbers[i];
        return numbers[0];
    }
    //第一次只出现一次的字符
    public char firstUniqChar(String s) {
        int[] count = new int[256];
        char[] chars = s.toCharArray();
        for(char c : chars)
            count[c]++;
        for(char c : chars){
            if(count[c] == 1)
                return c;
        }
        return ' ';
    }
}

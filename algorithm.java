package 算法基础;

import java.util.Arrays;
import java.util.HashMap;

public class algorithm {
    //二分查找
    public int search(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;
        while(left<=right) {
            int mid=left+(right-left)/2;
            if(nums[mid]<target) {
                left=mid+1;
            } else if(nums[mid]>target) {
                right=mid-1;
            } else{
                return mid;
            }
        }
        return -1;
    }
    //搜素插入位置
    public int searchInsert(int[] nums, int target) {
        for(int i=0;i<nums.length;i++) {
            if(nums[i]>=target) {
                return i;
            }
        }
        return nums.length;
    }
    /*二分找分界点 第一个错误版本；
    public int firstBadVersion(int n) {
        int left=1;
        int right=n;
        while(left<right) {
            int mid=left+(right-left)/2;
            if(isBadVersion(mid)) {
                right=mid;
            } else {
                left=mid+1;
            }
        }
        return left;
    }*/
    //有序数组的平方 暴力求解
    public int[] sortedSquares(int[] nums) {
        for (int i = 0; i <nums.length ; i++) {
            nums[i]=nums[i]*nums[i];
        }
        Arrays.sort(nums);

        return nums;

    }
    //有序数组的平方 双指针
    public int[] sortedSquares1(int[] nums) {
        int le=0;
        int ri=nums.length-1;
        int [] arr=new int[nums.length];
        int j=nums.length-1;
        while (le<=ri) {
            if(nums[le]*nums[le]<nums[ri]*nums[ri]) {
                arr[j]=nums[ri]*nums[ri];
                j--;
                ri--;
            } else {
                arr[j]=nums[le]*nums[le];
                j--;
                le++;
            }
        }
        return arr;
    }
    //旋转数组  前一部分旋转 后一部分旋转 整体旋转
    public void swap(int[] a,int be,int ed) {
        while (be<ed) {
            int tmp=a[be];
            a[be]=a[ed];
            a[ed]=tmp;
            be++;
            ed--;
        }
    }
    public void rotate(int[] nums, int k) {
            k=k%nums.length;
            swap(nums,0,nums.length-1);
            swap(nums,0,k-1);
            swap(nums,k,nums.length-1);
    }
    //移动零
    public void moveZeroes(int[] nums) {
        int index=0;
        for (int i = 0; i <nums.length ; i++) {
            if(nums[i]!=0) {
                nums[index]=nums[i];
                index++;
            }
        }
        for (int i = index; i <nums.length ; i++) {
            nums[i]=0;
        }
    }
    //两数之和
    public int[] twoSum(int[] numbers, int target) {
        int left=0;
        int right=numbers.length-1;
        while (left<right) {
            if(numbers[left]+numbers[right]==target) {
                return new int[] {left+1,right+1};
            } else if(numbers[left]+numbers[right]<target) {
                left++;
            } else {
                right--;
            }
        }
        return null;
    }
    //反转字符串
    public void reverseString(char[] s) {
        int left=0;
        int right=s.length-1;
        while (left<right) {
            char tmp=s[left];
            s[left]=s[right];
            s[right]=tmp;
            left++;
            right--;
        }
    }
    //反转字符串中单词
    public String reverseWords(String s) {
        String[] str=s.split(" ");
        StringBuffer stringBuffer=new StringBuffer();
        for (String s1:str) {
            char[] chars=s1.toCharArray();
            reverseString(chars);
            stringBuffer.append(String.valueOf(chars)+" ");
        }
        String s1=stringBuffer.toString();
        return s1.substring(0,s1.length()-1);
    }
    //无重复字符串的最长字串
    public int lengthOfLongestSubstring(String s) {
        int slow=0;
        int max=0;
        HashMap<Character,Integer> map=new HashMap<>();
        for (int i = 0; i <s.length() ; i++) {
            if(map.containsKey(s.charAt(i))) {
                slow=Math.max(slow,map.get(s.charAt(i))+1);

            }
            map.put(s.charAt(i),i);
            max=Math.max(max,i-slow+1);
        }
        return max;
    }
    //字符串的排列
    public boolean checkInclusion(String s1, String s2) {

    }
}

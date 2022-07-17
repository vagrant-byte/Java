package 数组;

import java.util.Arrays;

public class 扑克牌中的顺子 {
    public static boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int zero=0;
        int diff=0;
        for (int i = 0; i <nums.length-1 ; i++) {
            if(nums[i]==0) {
                zero++;
            }else {
                if(nums[i]==nums[i+1]) {
                    return false;
                }
                if(nums[i]+1!=nums[i+1]) {
                    diff+=nums[i+1]-nums[i]-1;
                }
            }
        }
        return zero-diff>=0;
    }
    public static boolean isStraight1(int[] nums) {
        Arrays.sort(nums);
        if(nums[2]==0) {
            return false;
        }
        if(nums[1]==0) {
            int[] tmp=new int[14];
            for (int i = 2; i <5 ; i++) {
                tmp[nums[i]]++;
            }
            int count=0;
            for (int i = nums[2]; i <=nums[4] ; i++) {
                if(tmp[i]>1) {
                    return false;
                }
                if(tmp[i]==0) {
                    count++;
                }
            }
            if(count==2) {
                return true;
            }else {
                return false;
            }
        }else if(nums[0]==0) {
            int[] tmp=new int[14];
            for (int i =1; i <5 ; i++) {
                tmp[nums[i]]++;
            }
            int count=0;
            for (int i = nums[1]; i <=nums[4] ; i++) {
                if(tmp[i]>1) {
                    return false;
                }
                if(tmp[i]==0) {
                    count++;
                }
            }
            if(count==1) {
                return true;
            }else {
                return false;
            }
        }else {
            for (int i = 1; i <nums.length ; i++) {
                if(nums[i]-1!=nums[i-1]) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        int[] a={4,7,5,9,2};
        boolean f=isStraight(a);
        System.out.println(f);
    }
}

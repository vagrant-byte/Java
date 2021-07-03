package 数组;

import java.util.Arrays;

//打印从1到最大的n位数
class Solution1 {
    public int[] printNumbers(int n) {
        int[] arr=new int[(int)Math.pow(10,n)-1];
        for(int i=0;i<arr.length;i++) {
            arr[i]=i+1;
        }
        return arr;

    }
}
//删除有序数组重复项
class S {
    public int removeDuplicates(int[] nums) {
        int slow=0;
        int fast=1;
        while (fast<nums.length){
            if(nums[slow]!=nums[fast]) {
                nums[++slow]=nums[fast];
            }
            fast++;
        }
        return slow+1;
    }
}

//找最大值
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int i=0;
        for(i=0;i<arr.length;i++) {
            if(arr[i]>arr[i+1]) {
                break;
            }
        }
        return i;
    }
}
//调整数组顺序使奇数位于偶数前面
//while for 循环   if 判断
class Solution6 {
    public int[] exchange(int[] nums) {
        int left=0;
        int right=nums.length-1;
        while (left<right) {
            while (left<right&&nums[left]%2!=0) {
                left++;
            }
            while (left<right&&nums[right]%2==0) {
                right--;
            }
            if(left<right) {
                int tmp=nums[left];
                nums[left]=nums[right];
                nums[right]=tmp;
                left++;
                right--;
            }
        }
        return nums;
    }
}
//重复数字
class Solution8 {
    public int findRepeatNumber(int[] nums) {
        //创建一个新数组存放数据
        int[] arr=new int[nums.length];
        for (int i = 0; i <nums.length ; i++) {
            //这个数字多次出现就++
            int num=arr[nums[i]]++;
            if(num>1) {
                return nums[i];
            }
        }
        return -1;
    }
}
//最小的k个数
class Solution7 {
    public int[] getLeastNumbers(int[] arr, int k) {
        for (int i = 0; i <arr.length-1 ; i++) {
            for (int j = 0; j<arr.length-1-i  ; j++) {
                if(arr[j]>arr[j+1]) {
                    int tmp=arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=tmp;
                }
            }
        }
        int[] a=new int[k];
        for (int i = 0; i <k ; i++) {
            a[i]=arr[i];
        }
        return a;
    }
}
//缺失的数字
class Solution10 {
    public int missingNumber(int[] nums) {
        int num=nums.length;
        for (int i = 0; i <nums.length ; i++) {
            if(nums[i]!=i) {
                return i;
            }
        }
        return num;
    }
}
//数组中超过一半的数字
class Solution14 {
    public int majorityElement(int[] nums) {
        int count=0;
        int card=0;//出现次数最多的数字
        for (int i = 0; i <nums.length ; i++) {
            if(count==0) {
                card=nums[i];
            }
            if(card==nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return card;

    }
}
//旋转数组最小数
class Solution13 {
    //二分查找
    public int minArray(int[] numbers) {
        int low=0;
        int high=numbers.length-1;
        while (low<high) {
            int mid=low+(high-low)/2;
            if(numbers[mid]>numbers[high]) {
                low=mid+1;
            }else if(numbers[mid]<numbers[high]) {
                high=mid;
            } else {
                high-=1;
            }
        }
        return numbers[low];
    }
        /*for (int i = 0; i <numbers.length-1 ; i++) {
            for (int j = 0; j <numbers.length-1-i ; j++) {
                if(numbers[j]>numbers[j+1]) {
                    int tmp=numbers[j];
                    numbers[j]=numbers[j+1];
                    numbers[j+1]=tmp;
                }
            }
        }
        return numbers[0];
    }*/

}
class Solution11 {
    public int search(int[] nums, int target) {
        //二分查找
        int left=0;
        int right=nums.length-1;
        int count=0;
        while (left<right) {
            int mid=left+(right-left)/2;
            if(nums[mid]<=target) {
                left=mid;
              } else if(nums[mid]>target) {
                right=mid-1;
            }
        }
        while (left<nums.length&&nums[left++]==target) {
            count++;
        }
        return count;
    }
        /* count=0;
        for (int i = 0; i <nums.length ; i++) {
            if(nums[i]==target) {
                count++;
            }
        }
        return count;
    }*/
}
//提莫攻击
class Solution3 {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int sum=0;
        for(int i=1;i<timeSeries.length;i++) {
            if(timeSeries[i]-timeSeries[i-1]>=duration) {
                sum=sum+duration;
            } else {
                sum+=timeSeries[i]-timeSeries[i-1];
            }
        }
        return sum+duration;
    }
}
//插入位置搜素
class Solution4 {
    public int searchInsert(int[] nums, int target) {
        for(int i=0;i<nums.length;i++) {
            if(nums[i]>=target) {
                return i;
            }
        }
        return nums.length;

    }
}
//加一
class Solution5 {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] = digits[i] + 1;
                break;
            }
        }
        int[] ret = new int[digits.length + 1];
        if (digits[0] == 0) {
            ret[0] = 1;
        } else return digits;
        return ret;
    }
}
//合并两个有序数组
class Solution12 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k=m+n-1;
        int i=m-1;
        int j=n-1;
        while (i>=0&&j>=0) {
            nums1[k--]=nums1[i]>nums2[j]?nums1[i--]:nums2[j--];
        }
        while (j>=0) {
            nums1[k--]=nums2[j--];
        }

    }
}
public class test13 {
    //存在重复元素
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i=0;i<nums.length-1;i++) {
            if(nums[i]==nums[i+1]) {
                return true;
            }
        }
        return false;

    }
    //最大子序和
    public int maxSubArray(int[] nums) {
        int res=nums[0];
        int sum=0;
        for (int i = 0; i <nums.length ; i++) {
            if(sum>0) {
                sum+=nums[i];
            }
            else {
                sum=nums[i];
            }
            res=sum>res?sum:res;
        }
        return res;
    }
    //只出现一次的数字 其他数字出现两次
    public static int singleNumber(int[] nums) {
        int tmp=0;
        for (int i = 0; i <nums.length ; i++) {
            tmp=tmp^nums[i];
        }
        return tmp;
    }
    //只出现一次的数字  其他数字出现3次 x & ~x = 0;
    //x & ~0 = x;
    public static int singleNumber1(int[] nums) {
        int a=0;
        int b=0;
        for (int i = 0; i <nums.length ; i++) {
            a=(a^nums[i])&~b;
            b=(b^nums[i])&~a;
        }
        return a;

    }

    public static void main(String[] args) {
        int[] num={1,1,4,1};
        System.out.println(singleNumber1(num));
    }
}


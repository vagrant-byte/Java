package 算法基础;

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
}

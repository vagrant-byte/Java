import java.util.Arrays;

public class test {
    //存在重复元素
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);//先排序
        for(int i=0;i<nums.length-1;i++) {
            //比较
            if(nums[i]==nums[i+1]) {
                return true;
            }
        }
        return false;

    }
    //最大子序和
    public int maxSubArray(int[] nums) {
        int ret=nums[0];
        int sum=0;
        for(int i=0;i<nums.length;i++) {
            if(sum>0) {
                sum+=nums[i];
            } else {
                sum=nums[i];
            }
            ret=ret>sum?ret:sum;
        }
        return ret;

    }
    //两数之和
    public int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length;i++) {
            for(int j=i+1;j<nums.length;j++) {
                if(nums[i]+nums[j]==target) {
                    return new int[] {i,j};
                }
            }
        }
        return new int[] {};

    }
    //合并两个有序数组
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p=m+n-1;
        int i=m-1;
        int j=n-1;
        while(i>=0&&j>=0) {
            //由后向前比较num1和num2中的元素将较大的一个放入num1数组的最后边
            //三目运算符巧用   如果num1的最后一个比较大则将num1的最后一个放入数组最后，num1倒数第二个元素继续和num2的最后一个元素比较
            nums1[p--]=nums1[i]>nums2[j]?nums1[i--]:nums2[j--];
        }
        while(j>=0) {
            //num1中元素比较完，将num2的其余元素放入num1后
            nums1[p--]=nums2[j--];
        }
    }
}

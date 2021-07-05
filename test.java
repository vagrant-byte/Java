import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    //两个数组的交集
    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list=new ArrayList<>();
        int i=0;
        int j=0;
        while (i<nums1.length&&j<nums2.length) {
            if(nums1[i]>nums2[j]) {
                j++;
            }else if(nums1[i]<nums2[j]) {
                i++;
            }else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] arr=new int[list.size()];
        for (int k = 0; k < list.size() ; k++) {
            arr[k]=list.get(k);
        }
        return arr;
    }
    //买股票的最佳时间
    //输入：[7,1,5,3,6,4]
    //输出：5
    //解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5
    public static int maxProfit(int[] prices) {
        /*int min=prices[0];
        int count=0;
        for (int i = 0; i <prices.length ; i++) {
            if(prices[i]<min) {
                min=prices[i];
                count=i;
            }
        }
        if(count==prices.length-1) {
            return 0;
        }
        int max=prices[count];
        for (int i = count; i <prices.length ; i++) {
            if(prices[i]>max) {
                max=prices[i];
            }
        }
        int num=max-min;
        return num;*/
        int min=prices[0];
        int max=0;
        for (int i = 0; i <prices.length ; i++) {
            if(prices[i]<min) {
                min=prices[i];
            }
            if(prices[i]-min>max) {
                max=prices[i]-min;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[] arr1=new int[] {7,4,5,3,6,1};
        System.out.println(maxProfit(arr1));


    }
}

package 数组;

public class 调整数组顺序使奇数位于偶数前面 {
    public static int[] exchange(int[] nums) {
        int n=nums.length;
        int left=0;
        int right=n-1;
        while (left<right) {
            while (left<right&&nums[left]%2==1) {
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

    public static void main(String[] args) {
        int[] nums={1,2,3,4};
        int[] a=exchange(nums);
        for (int i = 0; i <a.length ; i++) {
            System.out.println(a[i]);
        }
    }
}

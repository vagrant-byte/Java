package 排序;

public class 寻找峰值 {
    public static int findPeakElement (int[] nums) {
        if(nums.length==1) {
            return 0;
        }
        if (nums.length==2) {
            if(nums[0]>nums[1]) {
                return 0;
            }else {
                return 1;
            }
        }

        // write code here
        int index=0;
        for (int i = 1; i <nums.length-1 ; i++) {
            int a=nums[i-1];
            int b=nums[i];
            int c=nums[i+1];
            if(a<b&&b>c) {
                return i;
            }
            if(a<b&&b<c) {
                index++;
            }
        }
        if(index==0) {
            return 0;
        }
        if(index==nums.length-2) {
            return nums.length-1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a={5,4,3,2,1};
        System.out.println(findPeakElement(a));
    }
}

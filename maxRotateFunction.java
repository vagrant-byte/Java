public class maxRotateFunction {
    //旋转函数
    //向右旋转一次，就相当于把当前结果加上整个数组的和，再减去数组大小乘以当前最后一位。想到这个就很简单了
    public static int maxRotateFunction(int[] nums) {
        int sum=0;
        int n=nums.length;
        for (int i = 0; i <n ; i++) {
            sum+=nums[i];
        }
        int max=0;
        for (int i = 0; i <n ; i++) {
            max+=nums[i]*i;
        }
        int tmp=max;
        for (int i = 1; i <n ; i++) {
            int j=n-i;
            tmp=tmp+sum-nums[j]*(n-1)-nums[j];
            max=Math.max(max,tmp);

        }
        return max;

    }
    public static int maxRotateFunction1(int[] nums) {
        int n=nums.length;
        int max=Integer.MIN_VALUE;
        for (int i = 0; i <n ; i++) {
            int num=arrK(i,nums);
            if(max<num) {
                max=num;
            }
        }
        return max;

    }

    private static int arrK(int i, int[] nums) {
        int sum=0;
        int n=nums.length;
        for (int j = 0; j <n ; j++) {
            sum+=nums[i++]*j;
            if(i>=n) {
                i=0;
            }
        }
        return sum;
    }


}

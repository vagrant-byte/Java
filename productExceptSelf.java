public class productExceptSelf {
    //除自身以外数组的乘积
    public static int[] productExceptSelf(int[] nums) {
        int n=nums.length;
        int[] res=new int[n];
        int left=1;
        for (int i = 0; i <n ; i++) {
            res[i]=left;
            left*=nums[i];
        }
        int right=1;
        for (int i = n-1; i >=0 ; i--) {
            res[i]*=right;
            right*=nums[i];
        }
        return res;
//        int n=nums.length;
//        int[] res=new int[n];
//        for (int i = 0; i <nums.length ; i++) {
//            int sum=1;
//            for (int j = 0; j <i ; j++) {
//                sum*=nums[j];
//            }
//            for (int j = i+1; j <nums.length ; j++) {
//                sum*=nums[j];
//            }
//            res[i]=sum;
//        }
//        return res;
    }

    public static void main(String[] args) {
        int[] nums={1,2,3,4};
        int[] r=productExceptSelf(nums);
        for (int i = 0; i <r.length ; i++) {
            System.out.println(r[i]);
        }
    }
}

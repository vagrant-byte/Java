public class threeSumClosest {
    //最接近的三数之和
    public static int threeSumClosest(int[] nums, int target) {
        int min=Integer.MAX_VALUE;
        int n=nums.length;
        int res=0;
        for (int i = 0; i <n-2 ; i++) {
            for (int j = i+1; j <n-1 ; j++) {
                for (int k = j+1; k <n ; k++) {
                    int sum=nums[i]+nums[j]+nums[k];
                    if(Math.abs(sum-target)<min) {
                        min=Math.abs(sum-target);
                        res=sum;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums={0,0,0};
        int target=1;
        System.out.println(threeSumClosest(nums, target));
    }
}
   

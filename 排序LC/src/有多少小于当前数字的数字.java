public class 有多少小于当前数字的数字 {
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int max=nums[0];
        int min=nums[0];
        for (int i = 1; i <nums.length ; i++) {
            if(nums[i]<min) {
                min=nums[i];
            }
            if(nums[i]>max) {
                max=nums[i];
            }
        }
        int[] arr=new int[max-min+1];
        for (int i = 0; i <nums.length ; i++) {
            arr[nums[i]-min]++;
        }
        int[] res=new int[nums.length];
        int index=0;
        for (int i = 0; i <nums.length ; i++) {
            int j=nums[i]-min;
            int count=0;
            for (int k = 0; k <j ; k++) {
                count+=arr[k];
            }
            res[index++]=count;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums={6,5,4,8};
        int[] r=smallerNumbersThanCurrent(nums);
        for (int i = 0; i <r.length ; i++) {
            System.out.print(r[i]);
        }
    }
}

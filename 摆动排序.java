import java.util.Arrays;

public class 摆动排序 {
    public static void wiggleSort(int[] nums) {
        int n=nums.length;
        Arrays.sort(nums);
        int[] arr=nums.clone();
        int x=(n+1)/2;
        for (int i = 0,j=x-1,k=n-1; i <n ; i+=2,j--,k--) {
            nums[i]=arr[j];
            if(i+1<n) {
                nums[i+1]=arr[k];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums={1,3,2,2,3,1};
        wiggleSort(nums);
    }
}

import java.util.ArrayList;
import java.util.List;

//区域和检索-数组可修改
public class NumArray {
    private int size;
    private int[] nums;
    private int[] res;
    public NumArray(int[] nums) {
        this.nums=nums;
        int n=nums.length;
        size= (int) Math.sqrt(n);
        res=new int[(n+size-1)/size];
        for (int i = 0; i <n ; i++) {
            res[i/size]+=nums[i];
        }
    }

    public void update(int index, int val) {
        res[index/size]+=val-nums[index];
        nums[index]=val;
    }

    public int sumRange(int left, int right) {
        int b1=left/size;
        int b2=right/size;
        int i1=left%size;
        int i2=right%size;
        if(b1==b2) {
            int sum=0;
            for (int i =i1 ; i <=i2 ; i++) {
                sum+=nums[b1*size+i];
            }
            return sum;
        }
        int sum1=0;
        for (int i = i1; i <size ; i++) {
            sum1+=nums[b1*size+i];
        }
        int sum2=0;
        for (int i = 0; i <=i2 ; i++) {
            sum2+=nums[b2*size+i];
        }
        int sum3=0;
        for (int i = b1+1; i <b2 ; i++) {
            sum3+=res[i];
        }
        return sum1+sum2+sum3;
    }
//    private int[] nums;
//    public NumArray(int[] nums) {
//        this.nums=nums;
//    }
//    public void update(int index, int val) {
//        for (int i = 0; i <nums.length ; i++) {
//            if(i==index) {
//                nums[i]=val;
//            }
//        }
//    }
//
//    public int sumRange(int left, int right) {
//        int sum=0;
//        for (int i = left; i <=right ; i++) {
//            sum+=nums[i];
//        }
//        return sum;
//    }
}

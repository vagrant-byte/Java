import java.util.ArrayList;
import java.util.List;

//区域和检索-数组可修改
public class NumArray {
    private int[] nums;
    public NumArray(int[] nums) {
        this.nums=nums;
    }
    public void update(int index, int val) {
        for (int i = 0; i <nums.length ; i++) {
            if(i==index) {
                nums[i]=val;
            }
        }
    }

    public int sumRange(int left, int right) {
        int sum=0;
        for (int i = left; i <=right ; i++) {
            sum+=nums[i];
        }
        return sum;
    }
}

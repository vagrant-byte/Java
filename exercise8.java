import java.util.ArrayList;
import java.util.List;

public class exercise8 {
    //交换数字
    public int[] swapNumbers(int[] numbers) {
        int[] res=new int[numbers.length];
        int count=0;
        for (int i = numbers.length-1; i >=0 ; i--) {
            res[count]=numbers[i];
            count++;
        }
        return res;
    }
    //幂集
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        res.add(new ArrayList<>());
        for (int i = 0; i < nums.length ; i++) {
            int size=res.size();
            for (int j = 0; j <size ; j++) {
                List<Integer> list1=new ArrayList<>(res.get(j));
                list1.add(nums[i]);
                res.add(list1);
            }
        }
        return res;


    }
}

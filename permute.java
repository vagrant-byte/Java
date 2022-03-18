import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//全排列
public class permute {
    public  List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists=new ArrayList<>();
        List<Integer> list1=new ArrayList<>();
        array(lists,list1,nums);
        return lists;

    }

    public void array(List<List<Integer>> lists, List<Integer> list1, int[] nums) {
        //终止条件
        if(list1.size()==nums.length) {
            lists.add(new ArrayList<>(list1));
            return;
        }
        for (int i = 0; i <nums.length ; i++) {
            if(!list1.contains(nums[i])) {
                list1.add(nums[i]);
                array(lists,list1,nums);
                list1.remove(list1.size()-1);
            }
        }
    }

}

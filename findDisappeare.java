import java.util.ArrayList;
import java.util.List;
//所有不存在的数字
public class findDisappeare {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n=nums.length;
        int[] tmp=new int[n+1];
        for (int i = 0; i <nums.length ; i++) {
            tmp[nums[i]]=1;
        }
        List<Integer> list1=new ArrayList<>();
        for (int i = 1; i <tmp.length ; i++) {
            if(tmp[i]==0) {
                list1.add(i);
            }
        }
        return list1;
    }
}

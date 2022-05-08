import java.util.*;

public class findDuplicates {
    //数组中重复的数据
    public List<Integer> findDuplicates(int[] nums) {

        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i <nums.length ; i++) {
            if(map.containsKey(nums[i])) {
                int n=map.get(nums[i]);
                map.put(nums[i],n+1);
            } else {
                map.put(nums[i],1);
            }
        }
        List<Integer> list=new ArrayList<>();
        for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
            if(entry.getValue()!=1) {
                list.add(entry.getKey());
            }
        }
        return list;

    }
}

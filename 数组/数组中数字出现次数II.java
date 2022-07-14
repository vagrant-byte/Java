package 数组;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class 数组中数字出现次数II {
    public int singleNumber(int[] nums) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i <nums.length ; i++) {
            if(map.containsKey(nums[i])) {
                map.put(nums[i],map.get(nums[i])+1);
            }else {
                map.put(nums[i],1);
            }
        }
        for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
            if(entry.getValue()==1) {
                return entry.getKey();
            }
        }
        return 0;
    }
}

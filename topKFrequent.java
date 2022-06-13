package TopK;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class topKFrequent {
    //前K个高频元素
    public int[] topKFrequent(int[] nums, int k) {
        int[] res=new int[k];
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i <nums.length ; i++) {
            if(map.containsKey(nums[i])) {
                map.put(nums[i],map.get(nums[i])+1);
            } else {
                map.put(nums[i],1);
            }
        }
        PriorityQueue<Map.Entry<Integer,Integer>> queue=new PriorityQueue<>((o1,o2)->o1.getValue()-o2.getValue());
        for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
            queue.offer(entry);
            if(queue.size()>k) {
                queue.poll();
            }
        }
        for (int i = k-1; i >=0 ; i--) {
            res[i]=queue.poll().getKey();
        }
        return res;
    }
}

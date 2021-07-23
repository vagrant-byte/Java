import java.util.*;

public class helpTest {
    //最后一块石头的重量
    public int lastStoneWeight(int[] stones) {
        //大堆
        PriorityQueue<Integer> p = new PriorityQueue<>((i1, i2) -> i2 - i1);
        for (int i = 0; i <stones.length ; i++) {
            p.offer(stones[i]);
        }
        while (p.size()>=2) {
            int x=p.poll();
            int y=p.poll();
            if(x>y) {
                p.offer(x-y);
            }
        }
        return p.size()==1?p.peek():0;
    }
    //TopK问题
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        //创建了一个大堆 比较o2-o1
        PriorityQueue<List<Integer>> priorityQueue=new PriorityQueue<>(k, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return (o2.get(0)+o2.get(1))-(o1.get(0)+o1.get(1));
            }
        });

        for (int i = 0; i <nums1.length ; i++) {
            for (int j = 0; j <nums2.length ; j++) {
                if(priorityQueue.size()<k) {
                    List<Integer> ret=new ArrayList<>();
                    ret.add(nums1[i]);
                    ret.add(nums2[j]);
                    priorityQueue.offer(ret);
                } else {
                    List<Integer> tmp=priorityQueue.peek();
                    if((tmp.get(0)+tmp.get(1))>(nums1[i]+nums2[j])) {
                        priorityQueue.poll();
                        List<Integer> ret=new ArrayList<>();
                        ret.add(nums1[i]);
                        ret.add(nums2[j]);
                        priorityQueue.offer(ret);
                    }
                }
            }
        }
        List<List<Integer>> ret=new ArrayList<>();
        for (int i = 0; i <k&&!priorityQueue.isEmpty() ; i++) {
            ret.add(priorityQueue.poll());
        }
        return ret;
    }
}

package TopK;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//和最小的k个数对
public class kSmallestPairs {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        //建立一个大根堆
        PriorityQueue<List<Integer>> queue=new PriorityQueue<>(k, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return (o2.get(0)+o2.get(1))-(o1.get(0)+o1.get(1));
            }
        });
        for (int i = 0; i <Math.min(nums1.length,k) ; i++) {
            for (int j = 0; j <Math.min(nums2.length,k) ; j++) {
                List<Integer> list1=new ArrayList<>();
                list1.add(nums1[i]);
                list1.add(nums2[j]);
                if(queue.size()<k) {
                    queue.offer(list1);
                } else {
                    int sum=queue.peek().get(0)+queue.peek().get(1);
                    if(sum>(nums1[i]+nums2[j])) {
                        queue.poll();
                        queue.offer(list1);
                    }
                }
            }
        }
        List<List<Integer>> list=new ArrayList<>();
        for (int i =0; i <k&&!queue.isEmpty() ; i--) {
            list.add(queue.poll());
        }
        return list;

    }
}

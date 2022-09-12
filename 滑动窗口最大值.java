import java.util.LinkedList;

public class 滑动窗口最大值 {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if(nums==null||k<1||nums.length<k) {
            return null;
        }
        LinkedList<Integer> linkedList=new LinkedList<>();
        int[] res=new int[nums.length-k+1];
        int index=0;
        for (int i = 0; i <nums.length ; i++) {
            while (!linkedList.isEmpty()&&nums[linkedList.peekLast()]<=nums[i]) {
                linkedList.pollLast();
            }
            linkedList.addLast(i);
            if(linkedList.peekFirst()==i-k) {
                //i-w过期的下标
                linkedList.pollFirst();
            }
            if(i>=k-1) {
                //窗口形成
                res[index++]=nums[linkedList.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums={1,3,-1,-3,5,3,6,7};
        int[] r=maxSlidingWindow(nums,3);
        for (int i = 0; i <r.length ; i++) {
            System.out.println(r[i]);
        }
    }
}

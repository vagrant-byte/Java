package 滑动窗口;

import java.util.ArrayList;

//和为s的连续正数序列
public class findContinuousSequence {
    public int[][] findContinuousSequence(int target) {
        int low=1;
        int high=2;
        ArrayList<int[]> arrayList=new ArrayList<>();
        while (low<high) {
            int total=(low+high)*(high-low+1)/2;
            if(target==total) {
                int[] a=new int[high-low+1];
                int i=0;
                while (low<=high) {
                    a[i++]=low++;
                }
                arrayList.add(a);
            } else if(total<target) {
                high++;
            } else {
                low++;
            }
        }
        int size=arrayList.size();
        int[][] result=new int[size][];
        int count = 0;
        for (int[] item:arrayList) {
            result[count++]=item;
        }
        return result;
    }
}

package 数组;

import java.util.ArrayList;

public class 和为s的连续正数序列 {
    public static int[][] findContinuousSequence(int target) {
        ArrayList<int[]> list=new ArrayList<>();
        for (int left=1,right=1,sum=0;right<target  ; right++) {
            sum+=right;
            while (sum>target) {
                sum-=left;
                left++;
            }
            if(sum==target) {
                int[] tmp=new int[right-left+1];
                int index=0;
                for (int i = left; i <=right ; i++) {
                    tmp[index++]=i;
                }
                list.add(tmp);
            }
        }
        int[][] res=new int[list.size()][];
        int index=0;
        for (int[] ints:list) {
            res[index++]=ints;
        }
        return res;
    }

    public static void main(String[] args) {
        int t=9;
        int[][] r=findContinuousSequence(t);
        for (int i = 0; i <r.length ; i++) {
            for (int j = 0; j <r[i].length ; j++) {
                System.out.println(r[i][j]);
            }
        }
    }
}

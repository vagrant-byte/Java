package 动态规划;

import java.util.ArrayList;
import java.util.List;

public class DynamicProgramming {
    //最大子序和
    public int maxSubArray(int[] nums) {
        int sum=0;
        int rst=nums[0];
        for(int i=0;i<nums.length;i++) {
            if(sum>0) {
                sum+=nums[i];
            } else {
                sum=nums[i];
            }
           rst=Math.max(sum,rst);
        }
        return rst;

    }
    //爬楼梯
    public int climbStairs(int n) {
        if(n==1) {
            return 1;
        }
        if(n==2) {
            return 2;
        }
        int i1=1;
        int i2=2;
        for(int i=3;i<=n;i++) {
            int tmp=i1+i2;
            i1=i2;
            i2=tmp;
        }
        return i2;
    }
    //杨辉三角
    public List<List<Integer>> generate(int numRows) {
        if(numRows==0) {
            return null;
        }
        List<List<Integer>> list=new ArrayList<>();
        //处理第一行
        List<Integer> list1=new ArrayList<>();
        list1.add(1);
        list.add(list1);
        for (int i = 1; i <numRows ; i++) {
            //上一行
            List<Integer> prev=list.get(i-1);
            //处理每一行的第一个元素
            List<Integer> cur=new ArrayList<>();
            cur.add(1);
            for (int j = 1; j <i ; j++) {
                int c=prev.get(j-1)+prev.get(j);
                cur.add(c);
            }
            //处理每一行的最后一个元素
            cur.add(1);
            list.add(cur);
        }
        return list;
    }
    //杨辉三角II
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> list=new ArrayList<>();
        for (int i = 0; i <=rowIndex ; i++) {
            List<Integer> row=new ArrayList<>();
            for (int j = 0; j <=i ; j++) {
                if(j==0||j==i) {
                    row.add(1);
                } else {
                    row.add(list.get(i-1).get(j-1)+list.get(i-1).get(j));
                }
            }
            list.add(row);
        }
        return list.get(rowIndex);
    }

}

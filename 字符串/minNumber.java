package 字符串;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

//把数组排成最小数
public class minNumber {
    public String minNumber(int[] nums) {
        if(nums==null) {
            return new String();
        }
        ArrayList<Integer> list=new ArrayList<>();
        for (int i = 0; i <nums.length ; i++) {
            list.add(nums[i]);
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String s1=o1+""+o2;
                String s2=o2+""+o1;
                return s1.compareTo(s2);
            }
        });
        String result=new String();
        for (int i = 0; i <list.size() ; i++) {
            result+=list.get(i);
        }
        return result;
    }
}

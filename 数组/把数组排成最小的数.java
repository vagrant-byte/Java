package 数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 把数组排成最小的数 {
    public static String minNumber(int[] nums) {
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i <nums.length ; i++) {
           list.add(nums[i]);
        }
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String s1=o1+""+o2;
                String s2=o2+""+o1;
                return s1.compareTo(s2);
            }
        });
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i <list.size(); i++) {
            stringBuilder.append(list.get(i));
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        int[] a={1,20};
        String s=minNumber(a);
        System.out.println(s);
    }
}

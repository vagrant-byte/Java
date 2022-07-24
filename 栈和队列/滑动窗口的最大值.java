package 栈和队列;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class 滑动窗口的最大值 {
    public static ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        if (size <= num.length && size != 0) {
            //双端队列
            ArrayDeque<Integer> dp = new ArrayDeque<>();
            //维护一个队列
            for (int i = 0; i < size; i++) {
                //去掉比自己小的值
                while (!dp.isEmpty() && num[dp.peekLast()] < num[i]) {
                    dp.pollLast();
                }
                dp.add(i);
            }
            for (int i = size; i < num.length; i++) {
                list.add(num[dp.peekFirst()]);
                while (!dp.isEmpty() && dp.peekFirst() < (i - size + 1)) {
                    //弹出窗口移动后的值
                    dp.pollFirst();
                }
                while (!dp.isEmpty() && num[dp.peekLast()] < num[i]) {
                    dp.pollLast();
                }
                dp.add(i);
            }
            //最后一个窗口的最大值
            list.add(num[dp.pollFirst()]);
        }
        return list;
    }
//        int left=0;
//        int right=size-1;
//        ArrayList<Integer> list=new ArrayList<>();
//        while (right<num.length) {
//            int max=num[left];
//            for (int i = left; i <=right ; i++) {
//                if(max<num[i]) {
//                    max=num[i];
//                }
//            }
//            list.add(max);
//            left++;
//            right++;
//        }
//        return list;
//    }

    public static void main(String[] args) {
        int[] a={9,10,9,-7,-3,8,2,-6};
        int size=5;
        ArrayList<Integer> list=maxInWindows(a,size);
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }
    }
}

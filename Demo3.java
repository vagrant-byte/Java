package 算法第一天;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Demo3 {
    //数组中超过一半的数字
    public int MoreThanHalfNum_Solution(int [] array) {
        if(array==null) {
            return 0;
        }
        Arrays.sort(array);
        int num=array[array.length/2];
        int count=0;
        for (int i = 0; i <array.length ; i++) {
            if(array[i]==num) {
                count++;
            }
        }
        if(count>array.length/2) {
            return num;
        }
        return 0;
    }

    //最小的K个数
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list=new ArrayList<>();
        if(input==null||k<=0||input.length<k) {
            return list;
        }
        PriorityQueue<Integer> p=new PriorityQueue<>(k, Collections.reverseOrder());
        for (int i = 0; i <input.length ; i++) {
            if(i<k) {
                p.offer(input[i]);
            } else {
                if(input[i]<p.peek()) {
                    p.poll();
                    p.offer(input[i]);
                }
            }
        }
        for (int i = 0; i <k ; i++) {
            list.add(p.poll());
        }
        return list;
    }

    //连续子数组的最大和
    public int FindGreatestSumOfSubArray(int[] array) {
        int[] arr=new int[array.length];
        arr[0]=array[0];
        int max=array[0];
        for (int j = 1; j <array.length ; j++) {
            arr[j]=Math.max(arr[j-1]+array[j],array[j]);
            if(max<arr[j]) {
                max=arr[j];
            }
        }
        return max;

    }
}

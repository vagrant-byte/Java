package 寒假作业;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class HomeWork {
    //在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个
    //函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。时间复杂度小于O(n) ,空间复杂度O(1).
    public static boolean find(int[][] array, int n) {
        int row=array.length;//行
        int col=array[0].length;//列
        if(row==0||col==0) {
            return false;
        }
        if(n<array[0][0]||n>array[row-1][col-1]) {
            return false;
        }
        int i=0;
        int j=col-1;
        while (i<row&&j>=0) {
            if(n<array[i][j]) {
                j--;
            } else if(n>array[i][j]) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }
    //调整数组中元素的位置，使得奇数位于偶数之前.
    public static int[] swap(int[] array) {
        int left=0;
        int right=array.length-1;
        while (left<right) {
            if(array[left]%2!=0) {
                left++;
            }else if(array[right]%2==0) {
                right--;
            } else {
                int tmp=array[left];
                array[left]=array[right];
                array[right]=tmp;
                left++;
                right--;
            }
        }
        return array;
    }
    public static int count1(int n) {
        int count=0;
        int i=0;
        for ( i = 0; i < 32; i++) {
            if( ((n>>i)&1) == 1) {
                count++;
            }
        }
        return count;
    }
    public static int count(int n) {
        int count=0;
        while (n!=0) {
            count++;
            n=n&(n-1);
        }
        return count;
    }
    //求一个有序数组中，两个值相加为key的数字，返回这两个数字的下标
    public static int[] sum(int[] array,int key) {
        int left=0;
        int right=array.length-1;
        while (left<right) {
            if (array[left]+array[right]==key) {
                return new int[] {left,right};
            } else if (array[left]+array[right]>key) {
                right--;
            } else {
                left++;
            }
        }
        return null;
    }
    public static void main2(String[] args) {
        int[] array=new int[] {2,4,3,5,7,8};
        int[] a=swap(array);
        for (int i = 0; i <a.length ; i++) {
            System.out.println(a[i]+" ");
        }
    }
    //求一个数组中前k个最小的数字
    public static int[] minKArray(int[] array,int k) {
        if(k==0||array.length==0) {
            return new int[0];
        }
        Queue<Integer> queue=new PriorityQueue<>((v1,v2)->v2-v1);
        for (int num:array) {
            if(queue.size()<k) {
                queue.offer(num);
            } else if(queue.peek()>num) {
                queue.poll();
                queue.offer(num);
            }
        }
        int[] res=new int[queue.size()];
        int index=0;
        for (int num:queue) {
            res[index++]=num;
        }
        return res;
    }
    //找出无序数组当中，出现次数超过数组长度一半的数字
    public static int selectHalfNum(int[] array) {
        Arrays.sort(array);
        int index=array.length/2;
        return array[index];
    }
    public static void main1(String[] args) {
        int[][] array={{1,2,3,4},{5,6,7,8},{9,10,12,11},{13,14,15,16}};
        int n=19;
        System.out.println(find(array, n));
    }

    public static void main(String[] args) {
        int[] array={1,2,3,2,2};
        System.out.println(selectHalfNum(array));

    }
    public static boolean foo(char c) {
        System.out.print(c);
        return true;
    }
        public static void main3( String[] args ) {
            int i = 0;
            for ( foo('A'); foo('B') && (i < 2); foo('C')) {
                i++ ;
                foo('D');
            }
        }
    }


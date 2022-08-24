package 排序.归并排序.堆排;

import java.util.Stack;

public class 堆排 {
    public static int[] sortArray(int[] nums) {
        if(nums==null||nums.length<2) {
            return nums;
        }
        for(int i=0;i<nums.length;i++) {
            heapInsert(nums,i);
        }
        int heapSize=nums.length;
        swap(nums,0,--heapSize);
        while(heapSize>0) {
            heapify(nums,0,heapSize);
            swap(nums,0,--heapSize);
        }
        return nums;
    }
    public static void heapInsert(int[] arr,int index) {
            while (arr[index]>arr[(index-1)/2]) {
                swap(arr,index,(index-1)/2);
                index=(index-1)/2;
            }
        
    }
    public static void heapify(int[] arr,int index,int heapSize) {
        int left=index*2+1;
        while(left<heapSize) {
            int targest=left+1<heapSize&&arr[left]<arr[left+1]?left+1:left;
            targest=arr[targest]>arr[index]?targest:index;
            if(targest==index) {
                break;
            }
            swap(arr,index,targest);
            index=targest;
            left=index*2+1;
        }
    }
    public static void swap(int[] arr,int i,int j) {
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }

    public static void main(String[] args) {
        int[] a={-4,0,7,4,9,-5,-1,0,-7,-1};
        int[] t=sortArray(a);
        for (int i = 0; i <t.length ; i++) {
            System.out.println(t[i]);
        }
    }
}

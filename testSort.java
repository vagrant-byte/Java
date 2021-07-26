import java.util.Arrays;
import java.util.Stack;

public class testSort {
    //直接插入排序
    //时间复杂度  最好O(N）  最坏O(N^2)
    //空间复杂度  O(1)
    //稳定性     稳定
    public static void insertSort(int[] array) {
        for (int i = 1; i <array.length ; i++) {
            int tmp=array[i];
            int j = i-1;
            for (; j >=0 ; j--) {
                if(array[j]>tmp) {
                    array[j+1]=array[j];
                    array[j]=tmp;
                } else {
                    break;
                }
            }
            array[j+1]=tmp;
        }

    }
    //希尔排序
    //时间复杂度  最好O(n) 最坏O(n^2)
    //空间复杂度  O(1)
    //稳定性     不稳定
    public static void shellSort(int[] array) {
        //先进行分组
        int gap=array.length;
        while (gap>1) {
            //每组分别进行直接插入排序
            insertSortGap(array,gap);
            gap=gap/3+1;//保证最后一个是1
        }
        insertSortGap(array,1);
    }
    public static void insertSortGap(int[] array,int gap) {
        for (int i = 1; i <array.length ; i++) {
            int tmp=array[i];
            int j=i-gap;
            for (; j >=0 ; j-=gap) {
                if(array[j]>tmp) {
                    array[j+gap]=array[j];
                    array[j]=tmp;
                } else {
                    break;
                }
            }
            array[j+gap]=tmp;
        }
    }
    //选择排序  时间复杂度  最好O(n)  最坏O(n)
    //空间复杂度 O(1)
    //稳定性    不稳定
    public void selectSort(int[] array) {
        for (int i = 0; i <array.length ; i++) {
            for (int j = i+1; j<array.length ; j++) {
                if(array[i]>array[j]) {
                    int tmp=array[j];
                    array[j]=array[i];
                    array[i]=tmp;
                }
            }
        }
    }
    //冒泡排序
    //时间复杂度  最好O(n)   最坏O(n^2)
    //空间复杂度  O(1)
    //稳定性     稳定
    public static void bubbleSort(int[] array) {
        //趟数
        for (int i = 0; i <array.length-1 ; i++) {
            boolean flag=true;
            for (int j = 0; j <array.length-i-1 ; j++) {
                if(array[j]>array[j+1]) {
                    int tmp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=tmp;
                    flag=false;
                }
            }
            if(flag) {
                break;
            }
        }
    }
    //堆排序
    //时间复杂度  最好 最坏都是O(n*logn)
    //空间复杂度  O(1)
    //稳定性     不稳定
    public static void createSort(int[] array) {
        for (int i = (array.length-1-1)/2; i >=0 ; i--) {
            shiftDown(array,i,array.length);
        }
    }
    public static void shiftDown(int[]array,int root, int len) {
        int parent=root;
        int child=parent*2+1;
        while (child<len) {
            //确保childe指向最大的元素
            if(child+1<len&&array[child+1]>array[child]) {
                child++;
            }
            if(array[child]>array[parent]) {
                int tmp=array[child];
                array[child]=array[parent];
                array[parent]=tmp;
                parent=child;
                child=parent*2+1;
            } else {
                break;
            }
        }
    }
    public static void heapSort(int[] array) {
        createSort(array);//建大堆
        int end=array.length-1;
        while (end>0) {
            //将下标为0的元素与最后一个元素进行交换
            int tmp=array[0];
            array[0]=array[end];
            array[end]=tmp;
            shiftDown(array,0,end);
            end--;
        }
    }
    //快速排序
    //时间复杂度  最好O(n*logn) 最坏O(n^2)
    //空间复杂度  最好 logn  最坏 n
    //稳定性     不稳定
    public static int partition(int[] array,int low,int high) {
        //将标志位存放在tmp中
        int tmp=array[low];
        while (low<high) {
            //从最右边找比标志位小的数
            while (low<high&&array[high]>=tmp) {
                high--;
            }
            array[low]=array[high];
            //从标志位起找比标志位大的数
            while (low<high&&array[low]<=tmp) {
                low++;
            }
            array[high]=array[low];
        }
        array[low]=tmp;
        //返回下一个标志位
        return low;
    }
    public static void selectPivotMedianOfThree(int[] array,int start,int end,int mid) {
       //array[mid] <= array[start] <= array[end]
        if(array[mid]>array[start]) {
            int tmp=array[mid];
            array[mid]=array[start];
            array[start]=tmp;
        }
        if(array[start]>array[end]) {
            int tmp=array[start];
            array[start]=array[end];
            array[end]=tmp;
        }
    }
    public static void insertSort1(int[] array,int start,int end) {
        for (int i = start+1; i <=end ; i++) {
            int tmp=array[i];
            int j = i-1;
            for (; j >=start ; j--) {
                if(array[j]>tmp) {
                    array[j+1]=array[j];
                    array[j]=tmp;
                } else {
                    break;
                }
            }
            array[j+1]=tmp;
        }
    }
    //递归实现快排
    public static void quick(int[] array,int start,int end) {
        if(start>=end) {
            return;
        }
        //优化 当数组长度小于等于100时进行直接插入排序
        if(end-start+1<=100) {
            insertSort1(array,start,end);
            return;
        }
        //优化 三数取中法
        int mid=(start+end)/2;
        selectPivotMedianOfThree(array,start,end,mid);
        //确定标志位
        int pivot=partition(array,start,end);
        //左边递归
        quick(array,start,pivot-1);
        //右边递归
        quick(array,pivot+1,end);
    }
    public static void quickSort(int[] array) {
        quick(array,0,array.length-1);
    }
    //非递归实现快排
    public static void quickSort1(int[] array) {
        Stack<Integer> stack=new Stack<>();
        int start=0;
        int end=array.length-1;
        int pivot=partition(array,start,end);
        //确保左边至少有两个元素
        if(pivot>start+1) {
            stack.push(0);
            stack.push(pivot-1);
        }
        if(pivot<end-1) {
            stack.push(pivot+1);
            stack.push(end);
        }
        while (!stack.empty()) {
            end=stack.pop();
            start=stack.pop();
            partition(array,start,end);
            if(pivot>start+1) {
                stack.push(0);
                stack.push(pivot-1);
            }
            if(pivot<end-1) {
                stack.push(pivot+1);
                stack.push(end);
            }
        }
    }
    //归并排序
    //时间复杂度 O(n*logn)
    //空间复杂度 O(N)
    //稳定性    稳定
    public static void merge(int[] array,int start,int mid,int end) {
        int s1=start;
        int e1=mid;
        int s2=mid+1;
        int e2=end;
        int[] tmp=new int[end-start+1];//用来存放新序列
        int k=0;//记录tmp下标
        while (s1<=e1&&s2<=e2) {
            if(array[s1]<=array[s2]) {
                tmp[k++]=array[s1++];
            } else {
                tmp[k++]=array[s2++];
            }
        }
        if(s1<=e1) {
            tmp[k++]=array[s1++];
        }
        if(s2<=e2) {
            tmp[k++]=array[s2++];
        }
        for (int i = 0; i <tmp.length ; i++) {
            array[i+start]=tmp[i];
        }
    }
    public static void mergeSortInternal(int[] array,int start,int end) {
        if(start>=end) {
            return;
        }
        int mid=(start+end)/2;
        mergeSortInternal(array,start,mid);
        mergeSortInternal(array,mid+1,end);
        //合并
        merge(array,start,mid,end);

    }
    public static void mergeSort1(int[] array) {
        mergeSortInternal(array, 0,array.length-1);
    }

    public static void main(String[] args) {
        int[] a=new int[10000];
        for (int i = 0; i <10000 ; i++) {
            a[i]=i;
        }
        long start=System.currentTimeMillis();
        mergeSort1(a);
        long end=System.currentTimeMillis();
        System.out.println(end-start);

       // System.out.println(Arrays.toString(a));
    }
}

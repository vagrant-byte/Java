package 排序;

import java.util.Stack;

public class sort {
    //非递归实现快排
    public static void quickSortByLoop(int[] array) {
        //stack 用来存放数组下标，通过下标来表示接下来要处理的区间
        Stack<Integer> stack=new Stack<>();
        //初始情况下，先把右侧边界下标入栈，再把左侧边界下标入栈，左右边界仍然构成前闭后闭区间
        stack.push(array.length-1);
        stack.push(0);
        while (!stack.empty()) {
            //取元素顺序要和push顺序相反
            int left=stack.pop();
            int right=stack.pop();
            if(left>=right) {
                //区间只有0个或1个元素。不需整理
                continue;
            }
            //通过partition把区间整理成以基准值为中心，左侧小于等于基准值，右侧大于等于基准值
            int index=partition(array,left,right);
            //基准值右侧区间
            stack.push(right);
            stack.push(index+1);
            //基准值左侧区间
            stack.push(index-1);
            stack.push(left);
        }
    }
    private static int partition(int[] array, int left, int right) {
        int i=left;
        int j=right;
        int base=array[right];//取最右侧为基准值
        while (i<j) {
            //从左往右找比基准值大的值
            while (i<j&&array[i]<=base) {
                i++;
            }
            //上边循环结束，i要么与j重合，或者i指向的值大于base
            //从右往左找比基准值小的值
            while (i<j&&array[j]>=base) {
                j--;
            }
            //上边循环结束，i要么与j重合，或者j指向的值小于base
            //交互i 与 j 的值
            swap(array,i,j);
        }
        //当i与j重合时候，最后一步，要把重合位置的元素与基准值进行交换
        swap(array,i,right);
        return i;
    }
    private static void swap(int[] array, int i, int j) {
        int tmp=array[i];
        array[i]=array[j];
        array[j]=tmp;
    }

}

package 排序.归并排序.快排;

public class 快排 {
    public static int[] sortArray(int[] nums) {
        if(nums==null||nums.length<2) {
            return nums;
        }
        return quickSort(nums,0,nums.length-1);
    }
    public static int[] quickSort(int[] nums,int L,int R) {
        if(L<R) {
            swap(nums,L+(int)(Math.random()*(R-L+1)),R);
            int[] p=partition(nums,L,R);
            quickSort(nums,L,p[0]-1);
            quickSort(nums,p[1]+1,R);
        }
        return nums;
    }
    public static int[] partition(int[] arr,int L,int R) {
        int less=L-1;
        int more=R;
        while(less<more) {
            if(arr[L]<arr[R]) {
                swap(arr,++less,L++);
            }else if(arr[L]>arr[R]) {
                swap(arr,--more,L);
            }else{
                less++;
            }
        }
        swap(arr,more,R);
        return new int[] {less+1,more};
    }
    public static void swap(int[] arr,int i,int j) {
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }

    public static void main(String[] args) {
        int[] arr={2,1,5,3,4,7,9,0};
        int[] tmp=sortArray(arr);
        for (int i = 0; i <tmp.length ; i++) {
            System.out.println(tmp[i]);
        }
    }
}

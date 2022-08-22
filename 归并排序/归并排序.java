package 排序.归并排序;

public class 归并排序 {
    public static void mergeSort(int[] arr) {
        if(arr==null||arr.length<2) {
            return;
        }
        process(arr,0,arr.length-1);
    }

    private static void process(int[] arr, int l, int r) {
        if(l==r) {
            return;
        }
        int mid=l+((r-l)>>1);
        process(arr,l,mid);
        process(arr,mid+1,r);
        merge(arr,l,mid,r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int p1=l;
        int p2=mid+1;
        int[] help=new int[r-l+1];
        int i=0;
        while (p1<=mid&&p2<=r) {
            help[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        while (p1<=mid) {
            help[i++]=arr[p1++];
        }
        while (p2<=r) {
            help[i++]=arr[r++];
        }
        for (int j = 0; j <help.length ; j++) {
            arr[l+j]=help[j];
        }
    }
}

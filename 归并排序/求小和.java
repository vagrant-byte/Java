package 排序.归并排序;

public class 求小和 {
    public static Integer minSum(int[] arr) {
        return process(arr,0,arr.length-1);
    }

    private static Integer process(int[] arr, int l, int r) {
        if(l==r) {
            return 0;
        }
        int mid=l+((r-l)>>1);
        return process(arr,l,mid)+process(arr,mid+1,r)+merge(arr,l,mid,r);
    }

    private static Integer merge(int[] arr, int l, int mid, int r) {
        int p1=l;
        int p2=mid+1;
        int[] help=new int[r-l+1];
        int i=0;
        int res=0;
        while (p1<=mid&&p2<=r) {
            res+=arr[p1]<arr[p2]?(r-p2+1)*arr[p1]:0;
            help[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        while (p1<=mid) {
            help[i++]=arr[p1++];
        }
        while (p2<=r) {
            help[i++]=arr[p2++];
        }
        for (int j = 0; j <help.length ; j++) {
            arr[j]=help[j+l];
        }
        return res;
    }
}

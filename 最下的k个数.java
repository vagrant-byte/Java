package 数组;

public class 最下的k个数 {
    public static int[] getLeastNumbers(int[] arr, int k) {
        for (int i = 0; i <arr.length-1 ; i++) {
            for (int j = 0; j <arr.length-1-i ; j++) {
                if(arr[j]>arr[j+1]) {
                    int tmp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=tmp;
                }
            }
        }
        int[] res=new int[k];
        for (int i = 0; i <k ; i++) {
            res[i]=arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a={3,2,1};
        int k=2;
        int[] r=getLeastNumbers(a,k);
        for (int i:r) {
            System.out.println(i);
        }
    }
}

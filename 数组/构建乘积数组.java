package 数组;

public class 构建乘积数组 {
    public static int[] constructArr(int[] a) {
        int len=a.length;
        int[] res=new int[len];
        for (int i = 0,sum=1; i <len ; i++) {
            res[i]=sum;
            sum*=a[i];
        }
        for (int i = len-1,sum=1; i >=0 ; i--) {
            res[i]*=sum;
            sum*=a[i];
        }
        return res;
    }
//    public static int[] constructArr(int[] a) {
//        int len=a.length;
//        int[] res=new int[len];
//        int tmp=0;
//        while (tmp<len) {
//            int sum=1;
//            for (int i = 0; i <tmp; i++) {
//                sum*=a[i];
//            }
//            for (int i = tmp+1; i <len ; i++) {
//                sum*=a[i];
//            }
//            res[tmp++]=sum;
//        }
//        return res;
//    }

    public static void main(String[] args) {
        int[] a={1,2,3,4,5};
        int[] r=constructArr(a);
        for (int i = 0; i <r.length ; i++) {
            System.out.println(r[i]);
        }
    }
}

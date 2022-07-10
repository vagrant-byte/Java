package 数组;

public class 打印从1到最大的n位数 {
    public static int[] printNumbers(int n) {
        int max=1;
        while (n>0) {
            max*=10;
            n--;
        }
        int[] res=new int[max-1];
        for (int i = 0; i <res.length ; i++) {
            res[i]=i+1;
        }
        return res;
    }

    public static void main(String[] args) {
        int n=3;
        printNumbers(n);
    }
}

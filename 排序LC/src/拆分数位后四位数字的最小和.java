import java.util.Arrays;

public class 拆分数位后四位数字的最小和 {
    public static int minimumSum(int num) {
        int[] arr=new int[4];
        int i=0;
        while (num>0) {
            arr[i++]=num%10;
            num/=10;
        }
        Arrays.sort(arr);
        int num1=arr[0]*10+arr[2];
        int num2=arr[1]*10+arr[3];
        return num1+num2;
    }

    public static void main(String[] args) {
        int num=4009;
        System.out.println(minimumSum(num));
    }
}

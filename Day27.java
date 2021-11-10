import java.util.Scanner;

public class Day27 {
    //三角形
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            int a=scanner.nextInt();
            int b=scanner.nextInt();
            int c=scanner.nextInt();
            if(a==0||b==0||c==0) {
                System.out.println("No");
            }
            if(a+b>c&&a+c>b&&b+c>a) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }

        }
    }
    //不使用+ — * / 实现加法
    public static int Add(int num1,int num2) {
        while (num2!=0) {
            int tmp=num1^num2;
            num2=(num1&num2)<<1;
            num1=tmp;
        }
        return num1;
    }
}

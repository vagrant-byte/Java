import java.util.Scanner;

public class Day33 {
    //客似云来  存储斐波那契需要用long
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        long[] fib=new long[81];
        fib[0]=1;
        fib[1]=1;
        for (int i = 2; i <fib.length ; i++) {
            fib[i]=fib[i-1]+fib[i-2];
        }
        while (scanner.hasNext()) {
            int from=scanner.nextInt();
            int to=scanner.nextInt();
            long sum=0;
            for (int i = from; i <=to ; i++) {
                sum+=fib[i-1];
            }
            System.out.println(sum);
        }

    }
    //剪花布条
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            String s=scanner.next();
            String t=scanner.next();
            int count=0;
            while (s.contains(t)) {
                count++;
                //replaceFirst将s字符串中第一次出现t的字符串替换为空格
                s=s.replaceFirst(t,"");
            }
            System.out.println(count);
        }
    }

}

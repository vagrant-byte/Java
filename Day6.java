import java.util.Scanner;

public class Day6 {
    //将字符串转换为数字
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.nextLine();
        long num=0;
        int flag=1;
        if(s.charAt(0)=='+') {
            flag=1;
        } else if(s.charAt(0)=='-') {
            flag=-1;
        } else if (s.charAt(0)>='0'&&s.charAt(0)<='9') {
            num=s.charAt(0)-'0';
        } else {
            System.out.println(0);
            return;
        }
        for (int i = 1; i <s.length() ; i++) {
            if (s.charAt(i)>='0'&&s.charAt(i)<='9') {
                num=num*10+s.charAt(i)-'0';
            } else {
                System.out.println(0);
                return;
            }
        }
        if(flag>=0&&num<=Integer.MAX_VALUE) {
            System.out.println(num);
            return;
        }
        if(flag<0) {
            num*=flag;
            if(num>=Integer.MIN_VALUE) {
                System.out.println(num);
                return;
            }
        }
        System.out.println(0);
    }
    //不要二
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int W=scanner.nextInt();
        int H=scanner.nextInt();
        int count=0;
        if(W%4==0||H%4==0) {
            count=W*H/2;
        } else {
            count=W*H/2+1;
        }
        System.out.println(count);
    }
}

import java.util.Arrays;
import java.util.Scanner;

public class one1 {
    //有3*n个比赛选手，每个选手水平值a_i，分队，每组三个人分n组，每个组的水平值等于组中队员第二高水平值
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] a_i=new int[3*n];
        for (int i = 0; i <3*n ; i++) {
            a_i[i]=scanner.nextInt();
        }
        Arrays.sort(a_i);
        int sum=0;
        for (int i = n; i <3*n ; i+=2) {
            sum+=a_i[i];
        }
        System.out.println(sum);
    }

    //输入两个字符串从第一个字符串中删除第二个字符串中所有的字母
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s1=scanner.nextLine();
        String s2=scanner.nextLine();
        String[] strings=s1.split("");
        StringBuffer stringBuffer=new StringBuffer();
        for (int i = 0; i <strings.length ; i++) {
            if(!s2.contains(strings[i])) {
                stringBuffer.append(strings[i]);
            }
        }
        System.out.println(stringBuffer.toString());


    }

}

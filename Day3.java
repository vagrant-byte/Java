import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day3 {
    //FizzBuzz
    public List<String> fizzBuzz(int n) {
        List< String> list=new ArrayList<>();
        for(int i=1;i<=n;i++) {
            if(i%3!=0&&i%5!=0) {
                list.add(i+"");
            } else if(i%3==0&&i%5==0) {
                list.add("FizzBuzz");
            } else if(i%3==0) {
                list.add("Fizz");
            } else {
                list.add("Buzz");
            }
        }
        return list;

    }
    //输出数组中长度超过数组一半的数子
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] arr=new int[n];
        for (int i = 0; i <n ; i++) {
            arr[i]=scanner.nextInt();
        }
        Arrays.sort(arr);
        int num=arr[arr.length/2];
        int count=0;
        for (int i = 0; i <n ; i++) {
            if(num==arr[i]) {
                count++;
            }
        }
        if(count>arr.length/2) {
            System.out.println(num);
        }

    }
    //输出字符串中最长的数字字符串
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String string=scanner.nextLine();
        String res="";
        String cur="";
        int i=0;
        for ( i = 0; i <string.length() ; i++) {
            char ch=string.charAt(i);
            if(ch>='0'&&ch<='9') {
                cur=cur+ch+"";
            } else {
                if (cur.length()>res.length()) {
                    res=cur;
                    cur="";
                } else {
                    cur="";
                }
            }
        }
        if(i==string.length()&&cur.length()>res.length()) {
            res=cur;
        }
        System.out.println(res);
    }
}

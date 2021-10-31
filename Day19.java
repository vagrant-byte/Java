import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day19 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            String s1=scanner.next();
            String s2=scanner.next();
            String res=maxString(s1,s2);
            System.out.println(res);
        }
    }
    public static String maxString(String s1,String s2) {
        //s1存放短的字符串
        s1=s1.length()>s2.length()?s2:s1;
        s2=s1.length()>s2.length()?s1:s2;
        String str="";
        for (int i = s1.length(); i >0 ; i--) {
            for (int be=0,end=s1.length()-i;end<=s1.length();be++,end++) {
                str=s1.substring(be,end);
                if(s2.contains(str)) {
                    return str;
                }
            }
        }
        return "";
    }

    public static void main2(String[] args) {
            Scanner scanner=new Scanner(System.in);
            while (scanner.hasNext()) {
                String s1=scanner.next();
                String s2=scanner.next();
                List<String> list=new ArrayList<>();
                int count=0;
                if(s1.contains(s2)) {
                    System.out.println(s2);
                }
                if(s2.contains(s1)) {
                    System.out.println(s1);
                }
                //s1存放短的字符串
                s1=s1.length()>s2.length()?s2:s1;
                s2=s1.length()>s2.length()?s1:s2;
                for (int i = s1.length(); i >0 ; i--) {
                    for (int j = 0; j <=s1.length()-i ; j++) {
                        if(s2.contains(s1.substring(j,i+j))) {
                            list.add(s1.substring(j,i+j));
                            count++;
                        }
                    }
                }
                String[] res=new String[count];
                count=0;
                for (String s:list) {
                    res[count] = s;
                    count++;
                }
                String maxRes=res[0];
                for (int i = 1; i <res.length ; i++) {
                    if(maxRes.length()<res[i].length()) {
                        maxRes=res[i];
                    }
                }
                System.out.println(maxRes);
            }
    }
    //喝汽水
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            int n=scanner.nextInt();
            int x=0;
            if(n==0) {
                return;
            } else {
                x=0;
                while (n>2) {
                    int a=n/3;
                    int b=n%3;
                    n=a+b;
                    x+=a;
                }
                if(n==2) {
                    x=x+1;
                }
            }
            System.out.println(x);
        }
    }
}

import java.util.Scanner;

public class Day18 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            String str1=scanner.nextLine();
            String str2=scanner.nextLine();

            boolean result=comp(str1,str2);
            System.out.println(result);
        }
    }

    private static boolean comp(String str1, String str2) {
        if(str1.length()>str2.length()) {
            return false;
        }
        int i=0;
        int j=0;
        while (i<str1.length()&&j<str2.length()) {
            if(str1.charAt(i)!='?'&&str1.charAt(i)!='*') {
                if(str1.charAt(i)!=str2.charAt(j)) {
                    return false;
                } else {
                    i++;
                    j++;
                }
            } else if(str1.charAt(i)=='*'&&i<str1.length()-1) {
                if(str1.length()==str2.length()) {
                    i++;
                    j++;
                } else {
                    i++;
                    while (j<str2.length()) {
                        if(str1.charAt(i)==str2.charAt(j)) {
                            break;
                        } else {
                            j++;
                        }
                    }
                }
            } else if(str1.charAt(i)=='?') {
                i++;
                j++;
            } else if(str1.charAt(i)=='*'&&i==str1.length()-1) {
                return true;
            }
        }
        if(i==str1.length()) {
           return true;
        }
        return false;
    }

    //统计每个月兔子总数
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            int n=scanner.nextInt();
            int sum=num(n);
            System.out.println(sum);
        }
    }

    private static int num(int n) {
        if(n<3){
            return 1;
        }
        return num(n-1)+num(n-2);
    }


}

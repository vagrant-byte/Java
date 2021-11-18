import java.util.ArrayList;
import java.util.Scanner;

public class Day34 {
    //养兔子
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        long[] num=new long[91];
        num[1]=1;
        num[2]=2;
        for (int i = 3; i <num.length ; i++) {
            num[i]=num[i-1]+num[i-2];
        }
        while (scanner.hasNext()) {
            int n=scanner.nextInt();
            System.out.println(num[n]);

        }
    }

    //收件人列表
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            int n=scanner.nextInt();
            ArrayList<String> list=new ArrayList<>();
            for (int i = 0; i <=n ; i++) {
                String s=scanner.nextLine();
                if(s.contains(",")||s.contains(" ")) {
                    list.add("\""+s+"\"");
                } else {
                    list.add(s);
                }
            }
            for (int i = 1; i <list.size()-1 ; i++) {
                System.out.print(list.get(i)+", ");
            }
            System.out.println(list.get(list.size()-1));

        }
    }
}

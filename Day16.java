import java.util.Scanner;

class X{
    Y y=new Y();
    public X() {
        System.out.println("x");
    }
}
class Y {
    public Y() {
        System.out.println("Y");
    }
}
class Z extends X{
    Y y=new Y();
    public Z() {
        System.out.println("Z");
    }

    public static void main(String[] args) {
        new Z();
    }
}
public class Day16 {
    static boolean p;
   public String name="abc";

    public static void main(String[] args) {
        System.out.println(p);
    }

    public static void main3(String[] args) {
        Day16 test=new Day16();
        Day16 test1=new Day16();
        System.out.println(test.equals(test1)+" "+test.name.equals(test1.name));
    }
    //扑克牌大小
    public static void main2(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String str=scanner.nextLine();
        String[] pok=str.split("-");
        String[] str1=pok[0].split(" ");
        String[] str2=pok[1].split(" ");
        String tab="345678910JQKA2";
        String str3="joker JOKER";
        if(pok[0].equals(str3)||pok[1].equals(str3)) {
            System.out.println(str3);
        }else if(str1.length==str2.length) {
            if(tab.indexOf(str1[0].substring(0,1))>tab.indexOf(str2[0].substring(0,1))) {
                System.out.println(pok[0]);
            } else {
                System.out.println(pok[1]);
            }
        } else if(str1.length==4) {
            System.out.println(pok[0]);
        } else if(str2.length==4) {
            System.out.println(pok[1]);
        } else {
            System.out.println("ERROR");
        }
    }

    //完全数计算
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            int n=scanner.nextInt();
            int count=0;
            if(n==1) {
                System.out.println(1);
            }
            for (int i = 2; i <=n ; i++) {
                if(complete(i)) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }

    private static boolean complete(int n) {
        int sum=0;
        for (int i = 2; i <=n/2 ; i++) {
            if(n%i==0) {
                sum+=i;
            }
        }
        if((sum+1)==n) {
            return true;
        }
        return false;
    }
}

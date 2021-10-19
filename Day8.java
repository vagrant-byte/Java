import java.util.Scanner;

public class Day8 {
    //最大公约数
    public static int getGcd(int a, int b) {
        while (b>0) {
            int tmp=a%b;
            a=b;
            b=tmp;
        }
        return a;
    }
   //最小公倍数
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int A=scanner.nextInt();
        int B=scanner.nextInt();
        int m=getGcd(A,B);
        System.out.println(A*B/m);
    }

    //两种排序方法
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        String[] strings=new String[n+1];
        for (int i = 0; i <strings.length ; i++) {
            strings[i]=scanner.nextLine();
        }
        if(isLex(strings)&&isLengths(strings)) {
            System.out.println("both");
        } else if(isLex(strings)) {
            System.out.println("lexicographically");
        } else if(isLengths(strings)) {
            System.out.println("lengths");
        } else {
            System.out.println("none");
        }
    }
    public static boolean isLex(String[] strings) {
        for (int i = 0; i <strings.length-1 ; i++) {
            if(strings[i].compareTo(strings[i+1])>0) {
                return false;
            }
        }
        return true;
    }
    public static boolean isLengths(String[] strings) {
        for (int i = 0; i <strings.length-1 ; i++) {
            if(strings[i].length()>strings[i+1].length()) {
                return false;
            }
        }
        return true;
    }


//    public static void main1(String[] args) {
//
//        System.out.println(new B().getValue());
//    }
//    static class A {
//        protected int value;
//        public A(int v) {
//            setValue(v);
//        }
//        public void setValue(int value) {
//            this.value=value;
//        }
//        public int getValue() {
//            try {
//                value++;
//                return value;
//            } catch (Exception e) {
//                System.out.println(e.toString());
//            } finally {
//                this.setValue(value);
//                System.out.println(value);
//            }
//            return value;
//        }
//    }
//    static class B extends A{
//        public B() {
//            super(5);
//            setValue(getValue()-3);
//        }
//        public void setValue(int value) {
//            super.setValue(2*value);
//        }
//    }
}

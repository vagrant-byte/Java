import java.util.HashMap;
import java.util.Scanner;

public class Day22 {
    //字符串中第一次出现一次的字符
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            String str=scanner.next();
            char[] chars=str.toCharArray();
            HashMap<Character,Integer> map=new HashMap<>();
            for (int i = 0; i <chars.length ; i++) {
                if(map.containsKey(chars[i])) {
                    int tmp=map.get(chars[i]);
                    map.put(chars[i],tmp+1);
                } else {
                    map.put(chars[i],1);
                }
            }
            int i=0;
            while (i<chars.length) {
                if(map.get(chars[i])==1) {
                    System.out.println(chars[i]);
                    break;
                }
                i++;
            }
            if(i==chars.length) {
                System.out.println(-1);
            }
        }
    }
    //小易的升级之路
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            int n=scanner.nextInt();//怪物数量
            int a=scanner.nextInt();//初始能力值
            int[] b=new int[n];
            for (int i = 0; i <n ; i++) {
                b[i]=scanner.nextInt();
            }
            for (int i = 0; i <n ; i++) {
                if(a>=b[i]) {
                    a=a+b[i];
                } else {
                    a=a+common(a,b[i]);
                }
            }
            System.out.println(a);
        }

    }

    private static int common(int a, int b) {
        int min=a>b?b:a;
        for (int i = min; i >0 ; i--) {
            if(a%i==0&&b%i==0) {
                return i;
            }
        }
        return 0;
    }
}

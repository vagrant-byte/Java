import java.util.Arrays;

public class exercise5 {
    //快乐数
    public static boolean isHappy(int n) {
        for (int i = 0; i <100 ; i++) {
            int tmp=0;
            while (n>0) {
                tmp+=(n%10)*(n%10);
                n/=10;
            }
            n=tmp;
            if(n==1) {
                return true;
            }
        }
        return false;
    }
    //有效的字母异位词
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()) {
            return false;
        }
        char[] chars=s.toCharArray();
        Arrays.sort(chars);
        char[] chars1=t.toCharArray();
        Arrays.sort(chars1);
        for (int i = 0; i <chars.length ; i++) {
            if(chars[i]!=chars1[i]) {
                return false;
            }
        }
        return true;
    }
    //3的幂
    public static boolean isPowerOfThree(int n) {
        if(n==1) {
            return true;
        }
        if(n<3) {
            return false;
        }
        while (n>3) {
            if(n%3!=0) {
                return false;
            }
            n/=3;
        }
        if(n==3) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int n=81;
        boolean f=isPowerOfThree(n);
        System.out.println(f);
    }
}
